package com.greetingtech.cmd.chat.client.core;

import com.greetingtech.cmd.chat.client.core.handler.ChatHandler;
import com.greetingtech.cmd.chat.client.core.handler.HeartBeatHandler;
import com.greetingtech.cmd.chat.client.core.handler.LoginHandler;
import com.greetingtech.cmd.chat.client.core.handler.ResponseHandler;
import com.greetingtech.cmd.chat.common.handler.MsgCodeC;
import com.greetingtech.cmd.chat.common.message.LogoutMsg;
import com.greetingtech.cmd.chat.common.message.RoomChatSendMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.Scanner;

/**
 * @author greetingtech
 * @date 2019-09-02
 */
public class ChatRoomClient {

    private String host;

    private int port;

    public ChatRoomClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("input username");
        String username = scanner.nextLine();
        System.out.println("input password");
        String password = scanner.nextLine();
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
                    pipeline.addLast(new LoginHandler(username, password));
                    pipeline.addLast(new HeartBeatHandler());
                    pipeline.addLast(new LengthFieldBasedFrameDecoder(4 * 1024 * 1024, 12, 4));
                    pipeline.addLast(new MsgCodeC());
                    pipeline.addLast(new ResponseHandler());
                    pipeline.addLast(new ChatHandler());
                }
            });
            ChannelFuture connect = b.connect(host, port);
            Channel channel = connect.channel();
            for (; ; ) {
                String content = scanner.nextLine();
                if (!channel.isActive()) {
                    System.out.println("lost connection");
                    break;
                }
                if ("$q".equals(content)) {
                    LogoutMsg logoutMsg = new LogoutMsg();
                    logoutMsg.setUsername(username);
                    channel.writeAndFlush(logoutMsg).sync();
                    System.out.println("exit success");
                    break;
                }
                RoomChatSendMsg sendMsg = new RoomChatSendMsg();
                sendMsg.setContent(content);
                channel.writeAndFlush(sendMsg);
            }
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

}
