package com.nhnacademy.http.exam;

import java.io.IOException;
import java.net.Socket;

public class Connector {
    public void connect(Socket socket) {
        try {
            new Sender(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            new Receiver(socket).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
