package com.nhnacademy.http.exam;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.Socket;

public class Connector implements Runnable {
    private final Socket socket;
    private RequestVO requestData;

    public Connector(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Receiver receiver = new Receiver(socket);
        Sender sender = new Sender(socket);

        receiver.receiveRequest();
        if (receiver.getMehod().equals("GET")) {
            requestData = new RequestGetVO();
        } else if (receiver.getMehod().equals("POST")) {
            requestData = new RequestPostVO();
        }

        requestData.setOrigin(socket.getInetAddress().getHostAddress());

        Parser parser = new Parser(requestData, receiver.getRequestData());
        parser.parse();

        sender.sendResponse(requestData);
    }
}
