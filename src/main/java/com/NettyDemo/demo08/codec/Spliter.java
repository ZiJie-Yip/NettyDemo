package com.NettyDemo.demo08.codec;

import com.NettyDemo.demo08.command.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @Author: ZiJie.Yip
 * @Description: 通过魔数识别是否为本协议连接
 * @date: 2018/10/11 8:57
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    private static final int LENGTH_FIELD_OFFSET = 7;
    private static final int LENGTH_FIELD_LENGTH = 4;

    public Spliter(){
        super(Integer.MAX_VALUE,LENGTH_FIELD_OFFSET,LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        //屏蔽非本协议连接
        if(in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER){
            System.out.println("非本协议连接");
            ctx.channel().close();
            return null;
        }
        return super.decode(ctx, in);
    }
}
