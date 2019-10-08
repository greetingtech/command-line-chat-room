package com.greetingtech.cmd.chat.common.message;

import com.greetingtech.cmd.chat.common.constant.MsgTypeEnum;
import lombok.Data;

/**
 * @author greetingtech
 * @date 2019-10-08
 */
@Data
public class LoginMsg implements Msg {

    @Override
    public MsgTypeEnum type() {
        return MsgTypeEnum.LOGIN;
    }

    private String username;

    private String password;

}
