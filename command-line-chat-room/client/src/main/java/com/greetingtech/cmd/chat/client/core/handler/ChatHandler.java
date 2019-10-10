package com.greetingtech.cmd.chat.client.core.handler;

import com.greetingtech.cmd.chat.common.message.RoomChatReceiveMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author guoyiting
 * @date 2019-10-09
 */
public class ChatHandler extends SimpleChannelInboundHandler<RoomChatReceiveMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RoomChatReceiveMsg roomChatReceiveMsg) throws Exception {
        print(roomChatReceiveMsg);
    }

    private void print(RoomChatReceiveMsg roomChatReceiveMsg) {
        String format = String.format("%s: %s", roomChatReceiveMsg.getNickname(), roomChatReceiveMsg.getContent());
        System.out.println(format);
    }

}
