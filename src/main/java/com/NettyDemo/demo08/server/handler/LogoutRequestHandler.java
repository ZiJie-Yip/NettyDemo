package com.NettyDemo.demo08.server.handler;

import com.NettyDemo.demo08.command.LogoutRequestPacket;
import com.NettyDemo.demo08.command.LogoutResponsePacket;
import com.NettyDemo.demo08.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 16:05
 */
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    protected LogoutRequestHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutRequestPacket logoutRequestPacket) throws Exception {
        System.out.println("退出登录");
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();

        SessionUtil.unBindSession(channelHandlerContext.channel());

        logoutResponsePacket.setSuccess(true);
        channelHandlerContext.channel().writeAndFlush(logoutResponsePacket);
    }
}
