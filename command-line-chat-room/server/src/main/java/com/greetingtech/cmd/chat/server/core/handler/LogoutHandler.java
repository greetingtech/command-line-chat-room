package com.greetingtech.cmd.chat.server.core.handler;

import com.greetingtech.cmd.chat.common.message.LogoutMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutHandler extends SimpleChannelInboundHandler<LogoutMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutMsg logoutMsg) throws Exception {

    }

}
