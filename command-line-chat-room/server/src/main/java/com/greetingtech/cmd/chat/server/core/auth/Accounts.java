package com.greetingtech.cmd.chat.server.core.auth;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author guoyiting
 * @date 2019-10-09
 */
public class Accounts {

    @Data
    public static class Account {
        private String password;
        private String nickname;

        public Account(String password, String nickname) {
            this.password = password;
            this.nickname = nickname;
        }
    }

    private static final ChannelGroup ONLINE_CHANNEL = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private static final Map<String, Account> USERNAME_2_ACCOUNT;

    static {
        USERNAME_2_ACCOUNT = new HashMap<>();
        USERNAME_2_ACCOUNT.put("test0", new Account("test0", "test0"));
        USERNAME_2_ACCOUNT.put("test1", new Account("test1", "test1"));
    }

    public static boolean isValid(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        Account account = USERNAME_2_ACCOUNT.get(username);
        if (account == null) {
            return false;
        }
        return account.getPassword().equals(password);
    }

    public static Account getAccount(String username) {
        return USERNAME_2_ACCOUNT.get(username);
    }

    public static ChannelGroup getOnlineChannel() {
        return ONLINE_CHANNEL;
    }

}
