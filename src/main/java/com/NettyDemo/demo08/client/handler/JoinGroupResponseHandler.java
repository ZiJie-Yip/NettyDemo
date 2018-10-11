package com.NettyDemo.demo08.client.handler;

import com.NettyDemo.demo08.command.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 14:57
 */
@ChannelHandler.Sharable
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    public static final JoinGroupResponseHandler INSTANCE = new JoinGroupResponseHandler();

    protected JoinGroupResponseHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        if(joinGroupResponsePacket.isSuccess()){
            System.out.println("加入群组成功,群id为 :[" + joinGroupResponsePacket.getGroupId() + "]");
        }else{
            System.out.println("加入群组失败,原因为: " + joinGroupResponsePacket.getReason());
        }
    }
}
