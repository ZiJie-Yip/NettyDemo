package com.NettyDemo.demo08.client.console;

import com.NettyDemo.demo08.command.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 17:04
 */
public class MessageConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入用户Id以及消息");

        String userId = scanner.next();

        String message = scanner.next();

        MessageRequestPacket messageRequestPacket = new MessageRequestPacket();

        messageRequestPacket.setToUserId(userId);
        messageRequestPacket.setMessage(message);

        channel.writeAndFlush(messageRequestPacket);
    }
}
