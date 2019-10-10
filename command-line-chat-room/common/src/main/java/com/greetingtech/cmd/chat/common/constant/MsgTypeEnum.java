package com.greetingtech.cmd.chat.common.constant;

import com.greetingtech.cmd.chat.common.message.*;

/**
 * @author greetingtech
 * @date 2019-10-08
 */
public enum MsgTypeEnum {

    /**
     * msg type code and related msgClass
     */
    HEART_BEAT(0, HeartBeatMsg.class),
    LOGIN(1, LoginMsg.class),
    LOGOUT(2, LogoutMsg.class),
    ROOM_CHAT_SEND(3, RoomChatSendMsg.class),
    ROOM_CHAT_RECEIVE(4, RoomChatReceiveMsg.class),
    RESPONSE(5, ResponseMsg.class),
    ;

    private int code;

    private Class<? extends Msg> msgClass;

    MsgTypeEnum(int code, Class<? extends Msg> msgClass) {
        this.code = code;
        this.msgClass = msgClass;
    }

    public int getCode() {
        return code;
    }

    public Class<? extends Msg> getMsgClass() {
        return msgClass;
    }

    public static MsgTypeEnum fromCode(int code) {
        for (MsgTypeEnum value : values()) {
            if (value.code == code) {
                return value;
            }
        }
        return null;
    }

}