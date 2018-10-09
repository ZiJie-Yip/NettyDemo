package com.NettyDemo.demo01.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @Author: ZiJie.Yip
 * @Description:启动一个简单的NettyServer端程序
 * @date: 2018/10/8 16:04
 */
public class NettyServer {

    //服务器默认绑定端口
    private final static int SERVER_DEFAULT_PORT =  8082;

    //服务器实际绑定端口
    private static int SERVER_BIND_PORT;

    public static void main(String[] args) {
        //bossGroup用于监听端口 accept新的连接请求
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup用于处理每一条连接的数据读取
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        //创建引导类，引导服务端启动工作
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                //绑定两大线程组
                .group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {

                    }
                });
        bind(serverBootstrap,SERVER_DEFAULT_PORT);
    }

    //自动绑定可用端口
    public static void bind(final ServerBootstrap serverBootstrap,final int port){
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if(future.isSuccess()){
                    SERVER_BIND_PORT = port;
                    System.out.println("端口[" + SERVER_BIND_PORT + "]绑定成功!");
                }else{
                    System.out.println("端口[" + port + "]绑定失败");
                    //尝试绑定下一个端口
                    bind(serverBootstrap,port+1);
                }
            }
        });
    }
}
