package com.NettyDemo.demo08.server.handler;

import com.NettyDemo.demo08.command.GroupMessageRequestPacket;
import com.NettyDemo.demo08.command.GroupMessageResponsePacket;
import com.NettyDemo.demo08.session.Session;
import com.NettyDemo.demo08.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 16:30
 */
@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    protected GroupMessageRequestHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception {
        String groupId = groupMessageRequestPacket.getGroupId();
        String message = groupMessageRequestPacket.getMessage();
        Session session = SessionUtil.getSession(channelHandlerContext.channel());
        String fromUserName = session.getUserName();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setFromUserName(fromUserName);
        groupMessageResponsePacket.setGroupId(groupId);
        groupMessageResponsePacket.setMessage(message);

        channelGroup.writeAndFlush(groupMessageResponsePacket);
    }
}
