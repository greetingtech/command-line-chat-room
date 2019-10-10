package com.greetingtech.cmd.chat.server.core.handler;

import com.greetingtech.cmd.chat.common.message.LogoutMsg;
import com.greetingtech.cmd.chat.common.message.ResponseMsg;
import com.greetingtech.cmd.chat.server.core.auth.Accounts;
import com.greetingtech.cmd.chat.server.core.constant.AttrKeys;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author greetingtech
 * @date 2019-10-08
 */
public class LogoutHandler extends SimpleChannelInboundHandler<LogoutMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutMsg logoutMsg) throws Exception {
        Accounts.getOnlineChannel().remove(channelHandlerContext.channel());
        ResponseMsg success = ResponseMsg.success();
        ChannelFuture future = channelHandlerContext.channel().writeAndFlush(success);
        future.addListener((ChannelFutureListener) channelFuture -> channelFuture.channel().close());
    }

}
