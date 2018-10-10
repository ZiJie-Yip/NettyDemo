package com.NettyDemo.demo04.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: ZiJie.Yip
 * @Description:InBoundHandlerC
 * @date: 2018/10/10 11:45
 */
public class InBoundHandlerC extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InBoundHandlerC: " + msg);
        ctx.channel().writeAndFlush(ctx.alloc().buffer());
        super.channelRead(ctx, msg);
    }

}
