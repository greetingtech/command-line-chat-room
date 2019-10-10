package com.greetingtech.cmd.chat.server.core.handler;

import com.greetingtech.cmd.chat.common.message.LoginMsg;
import com.greetingtech.cmd.chat.server.core.constant.AttrKeys;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Attribute;

/**
 * @author greetingtech
 * @date 2019-10-08
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Attribute<String> attr = ctx.channel().attr(AttrKeys.NICKNAME_KEY);
        String nickname = attr.get();
        if (nickname != null || (msg instanceof LoginMsg)) {
            super.channelRead(ctx, msg);
        } else {
            ctx.channel().close();
        }
    }

}
