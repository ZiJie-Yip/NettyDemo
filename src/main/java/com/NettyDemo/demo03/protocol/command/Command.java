package com.NettyDemo.demo03.protocol.command;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/9 16:46
 */
public interface Command {
    /**
     * 登录请求
     */
    Byte LOGIN_REQUEST = 1;
    /**
     * 登录返回
     */
    Byte LOGIN_RESPONSE = 2;

}
