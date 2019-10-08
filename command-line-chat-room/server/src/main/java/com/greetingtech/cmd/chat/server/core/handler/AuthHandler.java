package com.greetingtech.cmd.chat.server.core.handler;

import com.greetingtech.cmd.chat.common.message.LoginMsg;
import com.greetingtech.cmd.chat.server.core.constant.AttrKeys;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;

public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Attribute<Boolean> attr = ctx.channel().attr(AttrKeys.LOGIN_KEY);
        Boolean isLogin = attr.get();
        if (isLogin || (msg instanceof LoginMsg)) {
            super.channelRead(ctx, msg);
        } else {
            ctx.channel().close();
        }
    }

}
