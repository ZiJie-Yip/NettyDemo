package com.NettyDemo.demo08.command;

import lombok.Data;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 15:03
 */
@Data
public class QuitGroupResponsePacket extends Packet {

    private boolean success;

    private String reason;

    private String groupId;

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}
