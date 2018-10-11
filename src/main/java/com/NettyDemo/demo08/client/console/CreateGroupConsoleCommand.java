package com.NettyDemo.demo08.client.console;

import com.NettyDemo.demo08.command.CreateGroupRequestPacket;
import io.netty.channel.Channel;


import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 9:51
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        System.out.println("[拉人群聊]输入 userId 列表,userId之间英文逗号隔开:");

        String userIds = scanner.next();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
