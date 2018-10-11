package com.NettyDemo.demo08.client.handler;

import com.NettyDemo.demo08.command.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 16:35
 */
@ChannelHandler.Sharable
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {

    public static final GroupMessageResponseHandler INSTANCE = new GroupMessageResponseHandler();

    protected GroupMessageResponseHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageResponsePacket groupMessageResponsePacket) throws Exception {
        String fromUserName = groupMessageResponsePacket.getFromUserName();
        String groupId = groupMessageResponsePacket.getGroupId();
        String message = groupMessageResponsePacket.getMessage();

        System.out.println("群号[" + groupId + "] " + fromUserName + " : " + message);
    }
}
