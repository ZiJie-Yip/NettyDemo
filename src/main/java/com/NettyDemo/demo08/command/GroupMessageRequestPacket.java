package com.NettyDemo.demo08.command;

import lombok.Data;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 16:22
 */
@Data
public class GroupMessageRequestPacket extends Packet {

    private String groupId;

    private String message;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MESSAGE_REQUEST;
    }
}
