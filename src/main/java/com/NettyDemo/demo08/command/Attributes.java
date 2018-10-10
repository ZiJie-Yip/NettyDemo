package com.NettyDemo.demo08.command;

import com.NettyDemo.demo08.session.Session;
import io.netty.util.AttributeKey;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/10 8:48
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
