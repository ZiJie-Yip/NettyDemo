package com.NettyDemo.demo08.command;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZiJie.Yip
 * @Description:消息请求数据包
 * @date: 2018/10/10 8:39
 */
@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String toUserId;

    private String message;

    public MessageRequestPacket(String toUserId,String message){
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
