package com.greetingtech.cmd.chat.client.core.handler;

import com.greetingtech.cmd.chat.common.message.ResponseMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author guoyiting
 * @date 2019-10-09
 */
public class ResponseHandler extends SimpleChannelInboundHandler<ResponseMsg> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ResponseMsg responseMsg) throws Exception {
        if (responseMsg.getCode() != 0) {
            System.out.println("发生错误");
            System.out.println(responseMsg.getMessage());
            channelHandlerContext.channel().close();
        }
    }

}
