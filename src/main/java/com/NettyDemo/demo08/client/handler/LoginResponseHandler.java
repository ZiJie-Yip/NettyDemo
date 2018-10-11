package com.NettyDemo.demo08.client.handler;

import com.NettyDemo.demo08.command.LoginRequestPacket;
import com.NettyDemo.demo08.command.LoginResponsePacket;
import com.NettyDemo.demo08.session.Session;
import com.NettyDemo.demo08.utils.LoginUtil;
import com.NettyDemo.demo08.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: ZiJie.Yip
 * @Description:登录返回处理
 * @date: 2018/10/10 14:55
 */
@ChannelHandler.Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    public static final LoginResponseHandler INSTANCE = new LoginResponseHandler();

    protected LoginResponseHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUserName();

        if(loginResponsePacket.isSuccess()){
            System.out.println("[" + userName + "]登录成功，userId 为: " + loginResponsePacket.getUserId());
            SessionUtil.bindSession(new Session(userId,userName),channelHandlerContext.channel());
        }else{
            System.out.println(new Date() + ": 客户端登陆失败,失败原因: " + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接被关闭!");
    }
}
