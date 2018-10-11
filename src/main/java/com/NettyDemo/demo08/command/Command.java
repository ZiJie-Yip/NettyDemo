package com.NettyDemo.demo08.command;

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
    /**
     * 消息请求
     */
    Byte MESSAGE_REQUEST = 3;
    /**
     * 消息返回
     */
    Byte MESSAGE_RESPONSE = 4;
    /**
     * 创建群请求
     */
    Byte CREATE_GROUP_REQUEST = 5;
    /**
     * 创建群返回
     */
    Byte CREATE_GROUP_RESPONSE = 6;
    /**
     * 加群请求
     */
    Byte JOIN_GROUP_REQUEST = 7;
    /**
     * 加群返回
     */
    Byte JOIN_GROUP_RESPONSE = 8;
    /**
     * 退群请求
     */
    Byte QUIT_GROUP_REQUEST = 9;
    /**
     * 退群返回
     */
    Byte QUIT_GROUP_RESPONSE = 10;
    /**
     * 获取成员列表请求
     */
    Byte LIST_GROUP_MEMBERS_REQUEST = 11;
    /**
     * 获取成员列表返回
     */
    Byte LIST_GROUP_MEMBERS_RESPONSE = 12;
    /**
     * 退出请求
     */
    Byte LOGOUT_REQUEST = 13;
    /**
     * 退出返回
     */
    Byte LOGOUT_RESPONSE = 14;
    /**
     * 群聊消息发送请求
     */
    Byte GROUP_MESSAGE_REQUEST = 15;
    /**
     * 群聊消息接收请求
     */
    Byte GROUP_MESSAGE_RESPONSE = 16;
}
