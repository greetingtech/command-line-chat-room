package com.greetingtech.cmd.chat.server.core.handler;

import com.greetingtech.cmd.chat.server.core.auth.Accounts;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author greetingtech
 * @date 2019-09-02
 */
public class IdleCloser extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            Accounts.getOnlineChannel().remove(ctx.channel());
            IdleStateEvent idleEvt = (IdleStateEvent) evt;
            ctx.close();
            return;
        }
        super.userEventTriggered(ctx, evt);
    }

}
