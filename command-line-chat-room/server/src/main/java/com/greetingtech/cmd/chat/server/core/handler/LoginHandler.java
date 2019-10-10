package com.greetingtech.cmd.chat.server.core.handler;

import com.greetingtech.cmd.chat.common.message.LoginMsg;
import com.greetingtech.cmd.chat.common.message.ResponseMsg;
import com.greetingtech.cmd.chat.server.core.auth.Accounts;
import com.greetingtech.cmd.chat.server.core.constant.AttrKeys;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author greetingtech
 * @date 2019-10-08
 */
public class LoginHandler extends SimpleChannelInboundHandler<LoginMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginMsg loginMsg) throws Exception {
        String username = loginMsg.getUsername();
        String password = loginMsg.getPassword();
        boolean valid = Accounts.isValid(username, password);
        if (valid) {
            Accounts.Account account = Accounts.getAccount(username);
            channelHandlerContext.channel().attr(AttrKeys.NICKNAME_KEY).set(account.getNickname());
            Accounts.getOnlineChannel().add(channelHandlerContext.channel());
            channelHandlerContext.channel().writeAndFlush(ResponseMsg.success());
            return;
        }
        channelHandlerContext.channel().writeAndFlush(ResponseMsg.error("invalid username or password"));
    }

}
