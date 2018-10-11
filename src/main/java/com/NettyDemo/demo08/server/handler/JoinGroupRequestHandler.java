package com.NettyDemo.demo08.server.handler;

import com.NettyDemo.demo08.command.JoinGroupRequestPacket;
import com.NettyDemo.demo08.command.JoinGroupResponsePacket;
import com.NettyDemo.demo08.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Author: ZiJie.Yip
 * @Description:加群逻辑处理
 * @date: 2018/10/11 14:44
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    protected JoinGroupRequestHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        //1. 获取groupId
        String groupId = joinGroupRequestPacket.getGroupId();
        //2. 获取对应的组channel
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        if(channelGroup != null){
            channelGroup.add(channelHandlerContext.channel());
            joinGroupResponsePacket.setSuccess(true);
            joinGroupResponsePacket.setGroupId(groupId);
        }else{
            joinGroupResponsePacket.setSuccess(false);
            joinGroupResponsePacket.setReason("不存在该群组");
        }
        channelHandlerContext.channel().writeAndFlush(joinGroupResponsePacket);

    }
}
