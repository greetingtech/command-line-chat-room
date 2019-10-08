package com.greetingtech.cmd.chat.server.core.handler;

import com.greetingtech.cmd.chat.common.message.HeartBeatMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class HeartBeatHandler extends SimpleChannelInboundHandler<HeartBeatMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartBeatMsg heartBeatMsg) throws Exception {
        // do nothing
    }

}
