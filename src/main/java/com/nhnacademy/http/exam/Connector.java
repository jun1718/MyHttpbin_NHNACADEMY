package com.nhnacademy.http.exam;

import java.net.Socket;

public class Connector implements Runnable {
    private final Socket socket;

    public Connector(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        RequestVO requestData = new RequestVO();

        Receiver receiver = new Receiver(socket, requestData);
        Sender sender = new Sender(socket);

        receiver.receiveRequest();
        sender.sendResponse();
    }

}
