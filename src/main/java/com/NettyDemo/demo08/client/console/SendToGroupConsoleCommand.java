package com.NettyDemo.demo08.client.console;

import com.NettyDemo.demo08.command.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 16:27
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入群号以及消息");
        String groupId = scanner.next();
        String message = scanner.next();

        GroupMessageRequestPacket groupMessageRequestPacket = new GroupMessageRequestPacket();
        groupMessageRequestPacket.setGroupId(groupId);
        groupMessageRequestPacket.setMessage(message);

        channel.writeAndFlush(groupMessageRequestPacket);
    }
}
