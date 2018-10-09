package com.NettyDemo.demo02.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @Author: ZiJie.Yip
 * @Description:服务器端处理器
 * @date: 2018/10/8 18:10
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf byteBuf = (ByteBuf)msg;

        System.out.println("服务端读到信息->"+byteBuf.toString(Charset.forName("UTF-8")));

        //获取发给客户端的ByteBuf
        ByteBuf resultByteBuf = getByteBuf(ctx);

        //发送给客户端
        ctx.channel().writeAndFlush(resultByteBuf);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        byte[] bytes = "服务端已收到信息".getBytes(Charset.forName("UTF-8"));

        ByteBuf byteBuf = ctx.alloc().buffer();

        byteBuf.writeBytes(bytes);

        return byteBuf;

    }

}
