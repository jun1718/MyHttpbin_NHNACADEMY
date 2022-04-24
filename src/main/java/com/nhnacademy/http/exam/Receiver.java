package com.nhnacademy.http.exam;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver {
    private final Socket socket;
    private String requestData;
    private final byte[] bytes = new byte[2048];

    public Receiver(Socket socket) {
        this.socket = socket;
    }

    public void receiveRequest() {
        setRequestData();
    }

    private void setRequestData() {
        try {
            socket.getInputStream().read(bytes);
            int index = 0;
            for (; index <= bytes.length; index++) if (bytes[index] == 0) break;
            this.requestData = new String(bytes, 0, index, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getRequestData() {
        return requestData;
    }

    public String getMehod() {
        return requestData.split(" ")[0];
    }
}
