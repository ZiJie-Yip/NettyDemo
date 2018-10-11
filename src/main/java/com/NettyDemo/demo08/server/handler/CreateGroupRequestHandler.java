package com.NettyDemo.demo08.server.handler;

import com.NettyDemo.demo08.command.CreateGroupRequestPacket;
import com.NettyDemo.demo08.command.CreateGroupResponsePacket;
import com.NettyDemo.demo08.utils.IDUtil;
import com.NettyDemo.demo08.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 14:00
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    protected CreateGroupRequestHandler(){

    }



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        System.out.println("CreateGroupRequestHandler");
        List<String> userIdList = createGroupRequestPacket.getUserIdList();

        List<String> userNameList = new ArrayList<>();

        //1. 创建一个channel分组
        ChannelGroup channelGroup = new DefaultChannelGroup(channelHandlerContext.executor());

        //2. 筛选加入小组的用户
        for(String userId : userIdList){
            Channel channel = SessionUtil.getChannel(userId);
            if(channel != null){
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        //3. 创建群聊响应结果
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(IDUtil.randomUserId());
        createGroupResponsePacket.setUserNameList(userNameList);

        SessionUtil.bindChannelGroup(createGroupResponsePacket.getGroupId(),channelGroup);

        //4. 给每个客户端发送拉群通知
        channelGroup.writeAndFlush(createGroupResponsePacket);


        System.out.println("群创建成功,id为[" + createGroupResponsePacket.getGroupId() + "], ");
        System.out.println("群里面有: " + createGroupResponsePacket.getUserNameList());
    }
}
