package com.example.demo.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description:
 * @Author: wukunlin
 * @CreateDate: 2019/8/23 下午1:28
 * @Version: 1.0
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        //打印服务端的发送数据
        System.out.println(s);
    }
}
