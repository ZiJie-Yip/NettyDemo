package com.NettyDemo.demo08.client.console;

import com.NettyDemo.demo08.command.LogoutRequestPacket;
import com.NettyDemo.demo08.command.LogoutResponsePacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 16:01
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("正在退出登录...");
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();

        channel.writeAndFlush(logoutRequestPacket);
    }
}
