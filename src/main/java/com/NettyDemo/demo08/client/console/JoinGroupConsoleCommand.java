package com.NettyDemo.demo08.client.console;

import com.NettyDemo.demo08.command.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 14:34
 */
public class JoinGroupConsoleCommand implements ConsoleCommand{

    @Override
    public void exec(Scanner scanner, Channel channel) {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        System.out.println("输入 groupId,加入群聊: ");
        String groupId = scanner.next();

        joinGroupRequestPacket.setGroupId(groupId);

        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
