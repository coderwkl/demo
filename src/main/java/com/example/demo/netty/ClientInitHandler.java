package com.example.demo.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @Description: 客户端初始化处理
 * @Author: wukunlin
 * @CreateDate: 2019/8/23 下午1:24
 * @Version: 1.0
 */
public class ClientInitHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        //注册管道
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("http", new HttpClientCodec());
        pipeline.addLast("chat", new ClientHandler());

    }
}
