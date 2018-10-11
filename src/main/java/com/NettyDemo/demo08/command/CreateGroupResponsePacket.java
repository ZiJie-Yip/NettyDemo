package com.NettyDemo.demo08.command;

import lombok.Data;

import java.util.List;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 14:09
 */
@Data
public class CreateGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
