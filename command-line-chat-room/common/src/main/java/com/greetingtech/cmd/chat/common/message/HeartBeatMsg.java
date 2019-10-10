package com.greetingtech.cmd.chat.common.message;

import com.greetingtech.cmd.chat.common.constant.MsgTypeEnum;
import lombok.Data;

/**
 * @author greetingtech
 * @date 2019-10-08
 */
@Data
public class HeartBeatMsg implements Msg {

    @Override
    public MsgTypeEnum type() {
        return MsgTypeEnum.HEART_BEAT;
    }

    private long time;

}
