package com.NettyDemo.demo06.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Author: ZiJie.Yip
 * @Description:客户端处理器
 * @date: 2018/10/8 18:04
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    //连接建立成功后调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for(int i = 0; i < 1000 ; i++){

            //1.创建一个用于发给服务器信息的ByteBuf
            ByteBuf buffer = getByteBuf(ctx);

            ctx.channel().writeAndFlush(buffer);
        }


    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        ByteBuf byteBuf = ctx.alloc().buffer();

        byte[] bytes = "你好,Yip!".getBytes(Charset.forName("UTF-8"));

        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    /**
     * 读取服务端返回信息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf byteBuf = (ByteBuf)msg;

        System.out.println(new Date() +  "客户端收到信息->" + byteBuf.toString(Charset.forName("UTF-8")));
    }
}
