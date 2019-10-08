package com.greetingtech.cmd.chat.common.handler;

import com.greetingtech.cmd.chat.common.message.Msg;
import com.greetingtech.cmd.chat.common.serializer.JsonSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @author greetingtech
 * @date 2019-10-08
 */
public class MsgCodeC extends MessageToMessageCodec<ByteBuf, Msg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Msg msg, List<Object> out) throws Exception {
        ByteBuf buffer = ctx.alloc().buffer();
        ByteBuf serialize = JsonSerializer.INSTANCE.serialize(buffer, msg);
        out.add(serialize);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        Msg deserialize = JsonSerializer.INSTANCE.deserialize(msg);
        out.add(deserialize);
    }

}
