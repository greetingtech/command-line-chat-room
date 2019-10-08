package com.greetingtech.cmd.chat.common.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.greetingtech.cmd.chat.common.constant.MsgTypeEnum;
import com.greetingtech.cmd.chat.common.message.Msg;
import io.netty.buffer.ByteBuf;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author greetingtech
 * @date 2019-10-08
 */
public class JsonSerializer implements Serializer {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static final JsonSerializer INSTANCE = new JsonSerializer();

    private JsonSerializer() {
        // empty
    }

    @Override
    public ByteBuf serialize(ByteBuf buffer, Msg msg) {
        buffer.writeInt(Msg.MAGIC);
        buffer.writeInt(Msg.VERSION);
        buffer.writeInt(msg.type().getCode());
        try {
            String strValue = mapper.writeValueAsString(msg);
            byte[] bytes = strValue.getBytes(StandardCharsets.UTF_8);
            buffer.writeInt(bytes.length);
            buffer.writeBytes(bytes);
            return buffer;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Msg deserialize(ByteBuf byteBuf) {
        int magic = byteBuf.readInt();
        if (magic != Msg.MAGIC) {
            throw new RuntimeException("magic invalid");
        }
        byteBuf.skipBytes(4);
        int typeCode = byteBuf.readInt();
        MsgTypeEnum msgTypeEnum = MsgTypeEnum.fromCode(typeCode);
        assert msgTypeEnum != null;
        Class<? extends Msg> msgClass = msgTypeEnum.getMsgClass();
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        String strValue = new String(bytes, StandardCharsets.UTF_8);
        try {
            Msg msg = mapper.readValue(strValue, msgClass);
            return msg;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
