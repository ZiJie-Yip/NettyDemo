package com.NettyDemo.demo07.server.handler;

import com.NettyDemo.demo05.utils.LoginUtil;
import com.NettyDemo.demo07.command.LoginRequestPacket;
import com.NettyDemo.demo07.command.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Author: ZiJie.Yip
 * @Description:登陆请求处理
 * @date: 2018/10/10 14:10
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println(new Date() + ": 收到客户端登录请求...");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());

        if(valid(loginRequestPacket)){
            loginResponsePacket.setSuccess(true);
            LoginUtil.markAsLogin(channelHandlerContext.channel());
        }else{
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账户密码校验失败");
            System.out.println(new Date() + ": 登录失败!");
        }

        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket){
        return false;
    }
}
