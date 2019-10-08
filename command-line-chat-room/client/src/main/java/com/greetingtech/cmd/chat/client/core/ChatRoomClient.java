package com.greetingtech.cmd.chat.client.core;

import com.greetingtech.cmd.chat.client.core.handler.MsgInHandler;
import com.greetingtech.cmd.chat.client.core.handler.MsgOutHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author greetingtech
 * @date 2019-09-02
 */
public class ChatRoomClient {

    private final Object lock = new Object();

    private String host;

    private int port;

    public ChatRoomClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new MsgInHandler());
                    pipeline.addLast(new MsgOutHandler());
                }
            });
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

}
