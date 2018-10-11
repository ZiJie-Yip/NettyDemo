package com.NettyDemo.demo08.command;

import com.NettyDemo.demo08.session.Session;
import lombok.Data;

import java.util.List;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 15:24
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
