package com.NettyDemo.demo08.client.handler;

import com.NettyDemo.demo08.command.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: ZiJie.Yip
 * @Description:退群返回处理
 * @date: 2018/10/11 15:17
 */
@ChannelHandler.Sharable
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    public static final QuitGroupResponseHandler INSTANCE = new QuitGroupResponseHandler();

    protected QuitGroupResponseHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        if(quitGroupResponsePacket.isSuccess()){
            System.out.println("已退出群号为[" + quitGroupResponsePacket.getGroupId() + "]的群");
        }else{
            System.out.println("退群失败,原因为: " + quitGroupResponsePacket.getReason());
        }
    }
}
