package com.example.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/23 下午1:18
 * @Version: 1.0
 */
public class NettyClient {

    private String ip;
    private int port;

    public NettyClient(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    private boolean stop = false;

    public void run() throws Exception{
        //设置一个多线程循环器
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //启动辅助类
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        //指定所使用的nio传输channel
        bootstrap.channel(NioSocketChannel.class);
        //指定客户端初始化处理
        bootstrap.handler(new ClientInitHandler());

        try{
            //连接服务
            Channel channel = bootstrap.connect(ip, port).sync().channel();
            while(true){
                //向服务端发送内容
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String content = reader.readLine();
                if(!StringUtils.isEmpty(content)){
                    if(content.equals("q")){
                        System.exit(1);
                    }
                    channel.writeAndFlush(content);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new NettyClient("localhost", 8899).run();
    }
}
