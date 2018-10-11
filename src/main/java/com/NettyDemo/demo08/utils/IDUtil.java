package com.NettyDemo.demo08.utils;

import java.util.UUID;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 14:12
 */
public class IDUtil {
    public static String randomUserId(){
        return UUID.randomUUID().toString().split("-")[0];
    }
}
