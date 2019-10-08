package com.greetingtech.cmd.chat.client;

import com.greetingtech.cmd.chat.client.core.ChatRoomClient;

/**
 * @author greetingtech
 * @date 2019-08-30
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ChatRoomClient client = new ChatRoomClient("127.0.0.1", 10001);
        client.start();
    }

}
