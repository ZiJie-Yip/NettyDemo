package com.NettyDemo.demo5.server.handler;

import com.NettyDemo.demo5.command.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @Author: ZiJie.Yip
 * @Description:服务端逻辑处理器
 * @date: 2018/10/9 17:33
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        //解码
        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
        if(packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket)packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginRequestPacket.setVersion(packet.getVersion());
            //登录校验
            if(valid(loginRequestPacket)){
                loginResponsePacket.setSuccess(true);
            }else{
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("账号密码校验失败");
            }

            //编码
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc().buffer(),loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }else if(packet instanceof MessageRequestPacket){
            //处理消息
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket)packet;
            System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());

            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage("服务端回复[ " + messageRequestPacket.getMessage() + " ]");
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc().buffer(),messageResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket){
        return true;
    }
}
