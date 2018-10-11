package com.NettyDemo.demo08.command;

import lombok.Data;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 14:45
 */
@Data
public class JoinGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
