package com.greetingtech.cmd.chat.common.serializer;

import com.greetingtech.cmd.chat.common.message.Msg;
import io.netty.buffer.ByteBuf;

/**
 * @author greetingtech
 * @date 2019-10-08
 */
public interface Serializer {

    ByteBuf serialize(ByteBuf byteBuf, Msg obj);

    Msg deserialize(ByteBuf byteBuf);

}
