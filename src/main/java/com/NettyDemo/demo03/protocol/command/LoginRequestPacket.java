package com.NettyDemo.demo03.protocol.command;


import lombok.Data;

/**
 * @Author: ZiJie.Yip
 * @Description: 登录请求数据包
 * @date: 2018/10/9 16:50
 */
@Data
public class LoginRequestPacket extends Packet{
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
