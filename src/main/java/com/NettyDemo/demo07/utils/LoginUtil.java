package com.NettyDemo.demo07.utils;

import com.NettyDemo.demo07.command.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @Author: ZiJie.Yip
 * @Description: 登录工具类
 * @date: 2018/10/10 8:50
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);
        return loginAttr.get() != null;
    }
}
