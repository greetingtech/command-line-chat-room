package com.greetingtech.cmd.chat.client.core;

import com.greetingtech.cmd.chat.client.core.handler.MsgInHandler;
import com.greetingtech.cmd.chat.client.core.handler.MsgOutHandler;
import com.greetingtech.cmd.chat.common.handler.MsgDecoder;
import com.greetingtech.cmd.chat.common.handler.MsgEncoder;
import com.greetingtech.cmd.chat.common.message.ChatMsg;
import com.greetingtech.cmd.chat.common.message.LoginMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

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
                    pipeline.addLast(new MsgDecoder());
                    pipeline.addLast(new MsgEncoder());
                    pipeline.addLast(new MsgInHandler());
                    pipeline.addLast(new MsgOutHandler());
                }
            });
            ChannelFuture f = b.connect(host, port).sync();
            Scanner scanner = new Scanner(System.in);
            System.out.println("input your nickname");
            String nickname = scanner.nextLine();
            LoginMsg loginMsg = new LoginMsg();
            loginMsg.setNickname(nickname);
            ChannelFuture future = f.channel().writeAndFlush(loginMsg);

            for (; ; ) {
                System.out.print("says: ");
                String content = scanner.nextLine();
                if ("#quit".equals(content)) {
                    break;
                }
                ChatMsg chatMsg = new ChatMsg();
                chatMsg.setToken("");
                chatMsg.setWhisper(false);
                chatMsg.setToNickname(nickname);
                chatMsg.setContent("hello");
            }

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

}
