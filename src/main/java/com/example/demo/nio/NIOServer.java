package com.example.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/22 上午11:27
 * @Version: 1.0
 */
public class NIOServer {

    //通道管理器
    private Selector selector;

    public void initServer(int port) throws IOException {
        //获得socket通道
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);

        //获得通道管理器
        this.selector = Selector.open();

        channel.socket().bind(new InetSocketAddress(port));
        //注册绑定通道
        channel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件
     */
    public void listen() throws IOException {
        System.out.println("server端启动成功");
        while(true){
            //当注册事件到达，方法返回，否则一直阻塞
            selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                //删除已选的key，防止重复处理
                iterator.remove();
                //客户端连接请求事件
                if(key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel)key.channel();
                    //获得和客户端连接的通道
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    //channel.write(ByteBuffer.wrap("连接成功".getBytes()));
                    System.out.println("accept a client " + channel.socket().getInetAddress().getHostName());
                    //和客户端连接成功后，为了可以接收到客户端的消息，需要给通道设置读的权限
                    channel.register(this.selector, SelectionKey.OP_READ);
                }
                //获取了可读的事件
                else if(key.isReadable()){
                    SocketChannel channel = (SocketChannel) key.channel();
                    //创建读取的缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);
                    String msg = new String(buffer.array()).trim();
                    System.out.println("收到客户端"+ channel.socket().getInetAddress().getHostName() + "的数据:\t" + msg);
                    //将消息回送给客户端
                    ByteBuffer byteBuffer = ByteBuffer.wrap("发送成功".getBytes());
                    channel.write(byteBuffer);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        NIOServer nioServer = new NIOServer();
        nioServer.initServer(5421);
        nioServer.listen();
    }

}
