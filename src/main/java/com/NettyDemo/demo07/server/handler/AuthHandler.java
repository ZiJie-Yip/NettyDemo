package com.NettyDemo.demo07.server.handler;

import com.NettyDemo.demo07.utils.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/10 16:04
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(!LoginUtil.hasLogin(ctx.channel())){
            ctx.channel().close();
        }else{
            //实现逻辑删除
            ctx.pipeline().remove(this);
            super.channelRead(ctx,msg);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(LoginUtil.hasLogin(ctx.channel())){
            System.out.println("当前连接验证完毕，无需再次验证，AuthHandler 被移除");
        }else{
            System.out.println("无登录验证，强制关闭连接!");
        }
    }
}
