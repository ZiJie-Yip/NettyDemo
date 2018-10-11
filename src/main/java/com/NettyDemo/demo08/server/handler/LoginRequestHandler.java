package com.NettyDemo.demo08.server.handler;

import com.NettyDemo.demo05.utils.LoginUtil;
import com.NettyDemo.demo08.command.LoginRequestPacket;
import com.NettyDemo.demo08.command.LoginResponsePacket;
import com.NettyDemo.demo08.session.Session;
import com.NettyDemo.demo08.utils.IDUtil;
import com.NettyDemo.demo08.utils.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: ZiJie.Yip
 * @Description:登陆请求处理
 * @date: 2018/10/10 14:10
 */
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    //构造单例
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    protected LoginRequestHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println(new Date() + ": 收到客户端登录请求...");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if(valid(loginRequestPacket)){
            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setUserId(IDUtil.randomUserId());
            loginResponsePacket.setUserName(loginRequestPacket.getUsername());
            System.out.println("[" + loginRequestPacket.getUsername() + "]登录成功");
            SessionUtil.bindSession(new Session(loginResponsePacket.getUserId(),loginRequestPacket.getUsername()),channelHandlerContext.channel());
        }else{
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("账户密码校验失败");
            System.out.println(new Date() + ": 登录失败!");
        }

        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket){
        return true;
    }



    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
