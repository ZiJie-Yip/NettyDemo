package com.NettyDemo.demo5.command;

import lombok.Data;

/**
 * @Author: ZiJie.Yip
 * @Description:消息返回数据包
 * @date: 2018/10/10 8:40
 */
@Data
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
