package com.greetingtech.cmd.chat.common.message;

import com.greetingtech.cmd.chat.common.constant.MsgTypeEnum;
import lombok.Data;

/**
 * @author guoyiting
 * @date 2019-10-09
 */
@Data
public class ResponseMsg implements Msg {

    @Override
    public MsgTypeEnum type() {
        return MsgTypeEnum.RESPONSE;
    }

    private int code;

    private String message;

    public static ResponseMsg success() {
        ResponseMsg msg = new ResponseMsg();
        msg.setCode(0);
        return msg;
    }

    public static ResponseMsg error(String message) {
        ResponseMsg msg = new ResponseMsg();
        msg.setCode(1);
        msg.setMessage(message);
        return msg;
    }

}
