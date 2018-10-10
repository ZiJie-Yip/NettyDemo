package com.NettyDemo.demo06.server;

import com.NettyDemo.demo06.server.handler.FirstServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @Author: ZiJie.Yip
 * @Description:服务器与客户端双向通信-服务端
 * @date: 2018/10/8 18:03
 */
public class NettyServer {

    //服务器默认绑定端口
    private final static int SERVER_DEFAULT_PORT =  8090;

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
                        nioSocketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7 , 4));
                    }
                });
        com.NettyDemo.demo01.server.NettyServer.bind(serverBootstrap,SERVER_DEFAULT_PORT);
    }

}
