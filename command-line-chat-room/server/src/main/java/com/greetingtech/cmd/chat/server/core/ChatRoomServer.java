package com.greetingtech.cmd.chat.server.core;

import com.greetingtech.cmd.chat.common.handler.MsgCodeC;
import com.greetingtech.cmd.chat.server.core.handler.AuthHandler;
import com.greetingtech.cmd.chat.server.core.handler.Dispatcher;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author greetingtech
 * @date 2019-08-30
 */
public class ChatRoomServer {

    private int port;

    public ChatRoomServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
            bootstrap.option(ChannelOption.SO_BACKLOG, 256);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new IdleStateHandler(1 ,0, 0, TimeUnit.MINUTES));
                    pipeline.addLast(new LengthFieldBasedFrameDecoder(4 * 1024 * 1024, 12, 4));
                    pipeline.addLast(new MsgCodeC());
                    pipeline.addLast(new AuthHandler());
                    pipeline.addLast(new Dispatcher());
                }
            });
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

}

