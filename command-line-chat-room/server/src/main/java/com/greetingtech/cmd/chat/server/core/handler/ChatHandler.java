package com.greetingtech.cmd.chat.server.core.handler;

import com.greetingtech.cmd.chat.common.message.RoomChatReceiveMsg;
import com.greetingtech.cmd.chat.common.message.RoomChatSendMsg;
import com.greetingtech.cmd.chat.server.core.auth.Accounts;
import com.greetingtech.cmd.chat.server.core.constant.AttrKeys;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author greetingtech
 * @date 2019-10-08
 */
public class ChatHandler extends SimpleChannelInboundHandler<RoomChatSendMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RoomChatSendMsg roomChatSendMsg) throws Exception {
        if (roomChatSendMsg.getContent() == null) {
            return;
        }
        RoomChatReceiveMsg receiveMsg = new RoomChatReceiveMsg();
        receiveMsg.setContent(roomChatSendMsg.getContent());
        receiveMsg.setNickname(channelHandlerContext.channel().attr(AttrKeys.NICKNAME_KEY).get());
        for (Channel channel : Accounts.getOnlineChannel()) {
            channel.writeAndFlush(receiveMsg);
        }
    }

}
