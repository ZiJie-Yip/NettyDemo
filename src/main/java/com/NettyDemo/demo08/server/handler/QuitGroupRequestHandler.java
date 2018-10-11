package com.NettyDemo.demo08.server.handler;

import com.NettyDemo.demo08.command.QuitGroupRequestPacket;
import com.NettyDemo.demo08.command.QuitGroupResponsePacket;
import com.NettyDemo.demo08.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Author: ZiJie.Yip
 * @Description:退群请求处理
 * @date: 2018/10/11 15:09
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    protected QuitGroupRequestHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        String quitGroupId = quitGroupRequestPacket.getGroupId();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(quitGroupId);

        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        quitGroupResponsePacket.setGroupId(quitGroupId);

        if(channelGroup != null){
            if(channelGroup.contains(channelHandlerContext.channel())){
                quitGroupResponsePacket.setSuccess(true);
                channelGroup.remove(channelHandlerContext.channel());
            }else{
                quitGroupResponsePacket.setReason("你未加入该群，退出失败");
            }
        }else{
            quitGroupResponsePacket.setReason("不存在该群,退出失败");
        }

        channelHandlerContext.channel().writeAndFlush(quitGroupResponsePacket);
    }
}
