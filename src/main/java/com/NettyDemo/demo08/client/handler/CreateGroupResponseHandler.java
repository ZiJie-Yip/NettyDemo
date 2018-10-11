package com.NettyDemo.demo08.client.handler;

import com.NettyDemo.demo08.command.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 14:16
 */
@ChannelHandler.Sharable
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    public static final CreateGroupResponseHandler INSTANCE = new CreateGroupResponseHandler();

    protected CreateGroupResponseHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        System.out.println("群创建成功,id 为[" + createGroupResponsePacket.getGroupId() + "], ");
        System.out.println("群里面有: " + createGroupResponsePacket.getUserNameList());
    }
}
