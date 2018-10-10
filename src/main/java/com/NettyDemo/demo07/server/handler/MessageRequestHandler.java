package com.NettyDemo.demo07.server.handler;

import com.NettyDemo.demo07.command.MessageRequestPacket;
import com.NettyDemo.demo07.command.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Author: ZiJie.Yip
 * @Description:消息请求处理
 * @date: 2018/10/10 14:12
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
        messageResponsePacket.setMessage("服务端回复[ " + messageRequestPacket.getMessage() + " ]");

        channelHandlerContext.channel().writeAndFlush(messageResponsePacket);
    }
}
