package com.NettyDemo.demo08.client.console;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: ZiJie.Yip
 * @Description:管理控制台命令执行器
 * @date: 2018/10/11 9:36
 */
public class ConsoleCommandManager implements ConsoleCommand{
    private Map<String,ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager(){
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("createGroup",new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup",new JoinGroupConsoleCommand());
        consoleCommandMap.put("quitGroup",new QuitGroupConsoleCommand());
        consoleCommandMap.put("listGroupMembers",new ListGroupMembersConsoleCommand());
        consoleCommandMap.put("logout",new LogoutConsoleCommand());
        consoleCommandMap.put("sendToGroup",new SendToGroupConsoleCommand());
        consoleCommandMap.put("sendToUser",new MessageConsoleCommand());
    }


    @Override
    public void exec(Scanner scanner, Channel channel) {
        //获取第一个指令
        String command = scanner.next();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if(consoleCommand != null){
            consoleCommand.exec(scanner,channel);
        }else{
            System.err.println("无法识别[" + command + "]指令，请重新输入");
        }
    }

}
