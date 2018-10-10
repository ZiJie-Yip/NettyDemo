package com.NettyDemo.demo08.command;

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

    private String userId;

    private String userName;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
