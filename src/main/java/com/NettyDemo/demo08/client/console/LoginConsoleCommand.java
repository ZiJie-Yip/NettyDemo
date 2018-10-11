package com.NettyDemo.demo08.client.console;

import com.NettyDemo.demo08.command.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: ZiJie.Yip
 * @Description:
 * @date: 2018/10/11 13:54
 */
public class LoginConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        System.out.println("输入用户名登录: ");
        loginRequestPacket.setUsername(scanner.nextLine());
        loginRequestPacket.setPassword("pwd");

        //发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }

    private static void waitForLoginResponse(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }
    }
}
