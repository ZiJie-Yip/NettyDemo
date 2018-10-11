package com.NettyDemo.demo08.client.console;

import com.NettyDemo.demo08.command.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 15:20
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入群号:");

        String groupId = scanner.next();
        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();
        listGroupMembersRequestPacket.setGroupId(groupId);

        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}
