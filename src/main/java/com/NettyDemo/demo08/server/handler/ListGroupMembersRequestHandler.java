package com.NettyDemo.demo08.server.handler;

import com.NettyDemo.demo08.command.ListGroupMembersRequestPacket;
import com.NettyDemo.demo08.command.ListGroupMembersResponsePacket;
import com.NettyDemo.demo08.session.Session;
import com.NettyDemo.demo08.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 15:34
 */
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    protected ListGroupMembersRequestHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
        String groupId = listGroupMembersRequestPacket.getGroupId();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);


        List<Session> sessionList = new ArrayList<>();

        if(channelGroup != null){
            for(Channel channel : channelGroup){
                sessionList.add(SessionUtil.getSession(channel));
            }
        }

        ListGroupMembersResponsePacket listGroupMembersResponsePacket = new ListGroupMembersResponsePacket();
        listGroupMembersResponsePacket.setGroupId(groupId);
        listGroupMembersResponsePacket.setSessionList(sessionList);

        channelHandlerContext.channel().writeAndFlush(listGroupMembersResponsePacket);

    }
}
