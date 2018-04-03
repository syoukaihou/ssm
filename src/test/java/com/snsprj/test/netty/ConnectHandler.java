package com.snsprj.test.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * netty -- 回调
 */
public class ConnectHandler extends ChannelInboundHandlerAdapter{

    // 当一个新的连接已经被建立时 channelActive(ChannelHandler Context) 将会被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("Client" + ctx.channel().remoteAddress() + " connected");
    }
}
