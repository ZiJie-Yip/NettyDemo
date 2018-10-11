package com.NettyDemo.demo08.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: ZiJie.Yip
 * @Description:控制台命令执行器接口
 * @date: 2018/10/11 9:35
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
