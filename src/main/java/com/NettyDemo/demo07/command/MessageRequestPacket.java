package com.NettyDemo.demo07.command;

import lombok.Data;

/**
 * @Author: ZiJie.Yip
 * @Description:消息请求数据包
 * @date: 2018/10/10 8:39
 */
@Data
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
