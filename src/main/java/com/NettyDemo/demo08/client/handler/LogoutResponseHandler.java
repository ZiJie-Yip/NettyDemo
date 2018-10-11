package com.NettyDemo.demo08.client.handler;

import com.NettyDemo.demo08.command.LogoutResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 16:08
 */
@ChannelHandler.Sharable
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

    public static final LogoutResponseHandler INSTANCE = new LogoutResponseHandler();

    protected LogoutResponseHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutResponsePacket logoutResponsePacket) throws Exception {
        if(logoutResponsePacket.isSuccess()){
            System.out.println("退出成功");
        }else{
            System.out.println("退出失败,原因为: " + logoutResponsePacket.getReason());
        }
    }
}
