package com.greetingtech.cmd.chat.client.core.handler;

import com.greetingtech.cmd.chat.common.message.HeartBeatMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author guoyiting
 * @date 2019-10-09
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);
        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                HeartBeatMsg heartBeatMsg = new HeartBeatMsg();
                heartBeatMsg.setTime(System.currentTimeMillis());
                ctx.channel().writeAndFlush(heartBeatMsg);
                scheduleSendHeartBeat(ctx);
            }
        }, 10, TimeUnit.SECONDS);
    }

}
