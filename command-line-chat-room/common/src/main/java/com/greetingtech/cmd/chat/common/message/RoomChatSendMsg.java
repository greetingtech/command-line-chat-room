package com.greetingtech.cmd.chat.common.message;

import com.greetingtech.cmd.chat.common.constant.MsgTypeEnum;

/**
 * @author greetingtech
 * @date 2019-10-08
 */
public class RoomChatSendMsg implements Msg {

    @Override
    public MsgTypeEnum type() {
        return MsgTypeEnum.ROOM_CHAT_SEND;
    }

}
