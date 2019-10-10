package com.greetingtech.cmd.chat.client.core.handler;

import com.greetingtech.cmd.chat.common.message.LoginMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author guoyiting
 * @date 2019-10-09
 */
public class LoginHandler extends ChannelInboundHandlerAdapter {

    private String username;

    private String password;

    public LoginHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginMsg msg = new LoginMsg();
        msg.setUsername(username);
        msg.setPassword(password);
        ctx.channel().writeAndFlush(msg);
        super.channelActive(ctx);
    }

}
