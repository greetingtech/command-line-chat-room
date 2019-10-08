package com.greetingtech.cmd.chat.server.core.handler;

import com.greetingtech.cmd.chat.common.message.RoomChatSendMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatHandler extends SimpleChannelInboundHandler<RoomChatSendMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RoomChatSendMsg roomChatSendMsg) throws Exception {

    }

}
