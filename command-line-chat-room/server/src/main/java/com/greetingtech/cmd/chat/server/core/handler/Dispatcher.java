package com.greetingtech.cmd.chat.server.core.handler;

import com.greetingtech.cmd.chat.common.constant.MsgTypeEnum;
import com.greetingtech.cmd.chat.common.message.Msg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author greetingtech
 * @date 2019-10-08
 */
public class Dispatcher extends SimpleChannelInboundHandler<Msg> {

    private static final Map<Integer, SimpleChannelInboundHandler> handlerMap;

    static {
        handlerMap = new HashMap<>();
        handlerMap.put(MsgTypeEnum.HEART_BEAT.getCode(), new HeartBeatHandler());
        handlerMap.put(MsgTypeEnum.LOGIN.getCode(), new LoginHandler());
        handlerMap.put(MsgTypeEnum.LOGOUT.getCode(), new LogoutHandler());
        handlerMap.put(MsgTypeEnum.ROOM_CHAT_SEND.getCode(), new ChatHandler());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg msg) throws Exception {
        handlerMap.get(msg.type().getCode()).channelRead(channelHandlerContext, msg);
    }

}
