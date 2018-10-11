package com.NettyDemo.demo08.command;

import lombok.Data;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 16:02
 */
@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
