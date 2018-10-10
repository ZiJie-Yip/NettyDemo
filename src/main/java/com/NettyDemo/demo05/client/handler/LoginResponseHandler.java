package com.NettyDemo.demo05.client.handler;

import com.NettyDemo.demo05.command.LoginRequestPacket;
import com.NettyDemo.demo05.command.LoginResponsePacket;
import com.NettyDemo.demo05.utils.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: ZiJie.Yip
 * @Description:登录返回处理
 * @date: 2018/10/10 14:55
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ":客户端开始登录");

        //创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("ZiJie.Yip");
        loginRequestPacket.setPassword("123");

        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        if(loginResponsePacket.isSuccess()){
            System.out.println(new Date() + ": 客户端登录成功");
            LoginUtil.markAsLogin(channelHandlerContext.channel());
        }else{
            System.out.println(new Date() + ": 客户端登陆失败,失败原因: " + loginResponsePacket.getReason());
        }
    }
}
