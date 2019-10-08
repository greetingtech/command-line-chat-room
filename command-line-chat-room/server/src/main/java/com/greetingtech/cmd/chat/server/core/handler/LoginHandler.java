package com.greetingtech.cmd.chat.server.core.handler;

import com.greetingtech.cmd.chat.common.message.LoginMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginHandler extends SimpleChannelInboundHandler<LoginMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginMsg loginMsg) throws Exception {

    }

}
