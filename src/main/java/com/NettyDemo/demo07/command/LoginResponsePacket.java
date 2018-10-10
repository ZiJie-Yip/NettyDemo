package com.NettyDemo.demo07.command;

import lombok.Data;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/9 17:45
 */
@Data
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
