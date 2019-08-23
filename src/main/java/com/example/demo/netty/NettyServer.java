package com.example.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/23 上午10:33
 * @Version: 1.0
 */
public class NettyServer {

    private int port;
    public NettyServer(int port){
        this.port = port;
    }

    public void run(){
        //EventLoopGroup是用来处理IO操作的多线程时间循环器
        //负责接收客户端连接线程
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //负责处理客户端IO事件，task任务，监听任务组
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //Nio服务的辅助启动类
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        //配置channel
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ServerInitHandler());
        //BACKLOG用于构造服务端serversocket对象
        //标识当服务器请求处理线程全满时，用于临时存放已完成三次握手请求的队列的最大长度
        bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
        //是否启用心跳保活机制
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

        try{
            Channel channel = bootstrap.bind(port).sync().channel();
            System.out.println("server run in port " + port);
            channel.closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new NettyServer(8899).run();
    }
}
