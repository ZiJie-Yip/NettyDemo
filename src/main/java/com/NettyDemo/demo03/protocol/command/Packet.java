package com.NettyDemo.demo03.protocol.command;

import lombok.Data;

/**
 * @Author: ZiJie.Yip
 * @Description: 通信实体抽象类
 * @date: 2018/10/9 16:48
 */
@Data
public abstract class Packet {

    private Byte version = 1;

    /**
     * 指令
     */
    public abstract Byte getCommand();

}
