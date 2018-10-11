package com.NettyDemo.demo08.client.console;

import com.NettyDemo.demo08.command.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 15:02
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入要退出的群号: ");

        String quitGroupId = scanner.next();

        //封装退群请求数据包
        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        quitGroupRequestPacket.setGroupId(quitGroupId);

        channel.writeAndFlush(quitGroupRequestPacket);

    }

}
