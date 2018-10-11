package com.NettyDemo.demo08.client;

import com.NettyDemo.demo08.client.console.ConsoleCommandManager;
import com.NettyDemo.demo08.client.console.LoginConsoleCommand;
import com.NettyDemo.demo08.client.handler.*;
import com.NettyDemo.demo08.codec.PacketCodecHandler;
import com.NettyDemo.demo08.codec.PacketDecoder;
import com.NettyDemo.demo08.codec.PacketEncoder;
import com.NettyDemo.demo08.codec.Spliter;
import com.NettyDemo.demo08.command.LoginRequestPacket;
import com.NettyDemo.demo08.command.MessageRequestPacket;
import com.NettyDemo.demo08.utils.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ZiJie.Yip
 * @Description:服务器与客户端双向通信-客户端
 * @date: 2018/10/8 18:03
 */
public class NettyClient {

    private final static String SERVER_DEFAULT_HOST = "127.0.0.1";

    private final static int SERVER_DEFAULT_PORT = 8090;

    //网络重连最大
    private final static Integer MAX_RETRY = 3;

    public static void main(String[] args) {
        //workerGroup用于处理连接的数据读取
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                //指定线程模型
                .group(workerGroup)
                //指定IO类型为NIO
                .channel(NioSocketChannel.class)
                //IO处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new Spliter());
                        socketChannel.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        socketChannel.pipeline().addLast(LoginResponseHandler.INSTANCE);
                        socketChannel.pipeline().addLast(MessageResponseHandler.INSTANCE);
                        socketChannel.pipeline().addLast(CreateGroupResponseHandler.INSTANCE);
                        socketChannel.pipeline().addLast(JoinGroupResponseHandler.INSTANCE);
                        socketChannel.pipeline().addLast(QuitGroupResponseHandler.INSTANCE);
                        socketChannel.pipeline().addLast(ListGroupMembersResponseHandler.INSTANCE);
                        socketChannel.pipeline().addLast(GroupMessageResponseHandler.INSTANCE);
                        socketChannel.pipeline().addLast(LogoutResponseHandler.INSTANCE);
                    }
                });
        connect(bootstrap,SERVER_DEFAULT_HOST,SERVER_DEFAULT_PORT,MAX_RETRY);
    }

    //失败指数级退避重连
    public static void connect(Bootstrap bootstrap,String host,int port,int retry){
        bootstrap.connect(host,port).addListener(future ->  {
            if(future.isSuccess()){
                System.out.println("连接成功");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            }else if(retry == 0){
                System.out.println("重试次数已用完，放弃连接");
            }else{
                //第几次重连
                int order = MAX_RETRY - retry + 1;
                //指数级退避连接
                int delay = 1 <<order;
                System.out.println(new Date() + ": 连接失败，第" + order + "次重连...");
                bootstrap.config().group().schedule(()->{
                    connect(bootstrap,host,port,retry - 1);
                },delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel){
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();

        Scanner scanner = new Scanner(System.in);
        new Thread(() -> {
            while(!Thread.interrupted()){
                if(!SessionUtil.hasLogin(channel)){
                    loginConsoleCommand.exec(scanner,channel);
                }else{
                    consoleCommandManager.exec(scanner,channel);
                }
            }
        }).start();
    }





}
