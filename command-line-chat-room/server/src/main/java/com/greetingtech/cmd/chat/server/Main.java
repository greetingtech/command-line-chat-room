package com.greetingtech.cmd.chat.server;

import com.greetingtech.cmd.chat.server.core.ChatRoomServer;

/**
 * @author greetingtech
 * @date 2019-08-30
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ChatRoomServer server = new ChatRoomServer(9292);
        server.start();
    }

}
