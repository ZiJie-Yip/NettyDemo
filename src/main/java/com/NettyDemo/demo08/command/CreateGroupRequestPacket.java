package com.NettyDemo.demo08.command;

import lombok.Data;

import java.util.List;

/**
 * @Author: ZiJie.Yip
 * @Description:创建群请求数据包
 * @date: 2018/10/11 13:42
 */
@Data
public class CreateGroupRequestPacket extends Packet{

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
