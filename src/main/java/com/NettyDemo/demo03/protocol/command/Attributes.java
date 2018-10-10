package com.NettyDemo.demo03.protocol.command;

import io.netty.util.AttributeKey;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/10 8:48
 */
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
