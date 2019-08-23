package com.example.demo.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/22 上午11:27
 * @Version: 1.0
 */
public class NIOClient {

    private Selector selector;

    public void initClient(String ip, int port) throws Exception{

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        this.selector = Selector.open();

        socketChannel.connect(new InetSocketAddress(ip, port));
        socketChannel.register(selector, SelectionKey.OP_CONNECT);

    }

    /**
     * 采用轮询的方式查看seletor上是否有需要处理的事件
     */
    public void listen() throws Exception{
        while (true){
            selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                //连接事件发生
                if(key.isConnectable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    //正在连接，则完成连接
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    //给服务端发送消息
                    System.out.println("请输入:");
                    Scanner scanner = new Scanner(System.in);
                    channel.write(ByteBuffer.wrap(scanner.nextLine().getBytes()));
                    //channel.write(ByteBuffer.wrap(("Hello this is " + Thread.currentThread().getName()).getBytes()));
                    channel.register(this.selector, SelectionKey.OP_READ);
                }
                //获得了可读事件
                else if(key.isReadable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    //创建读取的缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);
                    String msg = new String(buffer.array()).trim();
                    System.out.println("收到服务端的数据:\t"+msg);
                    System.out.println("请输入:");
                    Scanner scanner = new Scanner(System.in);
                    //将消息回送给服务端
                    ByteBuffer byteBuffer = ByteBuffer.wrap(scanner.nextLine().getBytes());
                    channel.write(byteBuffer);
                }
            }

        }
    }

    public static void main(String[] args) throws Exception{
        NIOClient nioClient = new NIOClient();
        nioClient.initClient("localhost", 5421);
        nioClient.listen();
    }
}
