package com.greetingtech.cmd.chat.common.message;

import com.greetingtech.cmd.chat.common.constant.MsgTypeEnum;

/**
 * @author greetingtech
 * @date 2019-10-08
 */
public interface Msg {

    int MAGIC = 0xACE666;

    short VERSION = 0;

    MsgTypeEnum type();

}
