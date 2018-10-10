package com.NettyDemo.demo06.client;

import com.NettyDemo.demo06.client.handler.FirstClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

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
                        socketChannel.pipeline().addLast(new FirstClientHandler());

                    }
                });
        com.NettyDemo.demo01.client.NettyClient.connect(bootstrap,SERVER_DEFAULT_HOST,SERVER_DEFAULT_PORT,MAX_RETRY);
    }



}
