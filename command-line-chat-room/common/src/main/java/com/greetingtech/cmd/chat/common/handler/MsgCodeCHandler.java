package com.greetingtech.cmd.chat.common.handler;

import com.greetingtech.cmd.chat.common.message.Msg;
import com.greetingtech.cmd.chat.common.serializer.JsonSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 * @author greetingtech
 * @date 2019-10-08
 */
public class MsgCodeCHandler extends ByteToMessageCodec<Msg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf out) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();
        JsonSerializer.INSTANCE.serialize(buffer, msg);
        
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {

    }

}
