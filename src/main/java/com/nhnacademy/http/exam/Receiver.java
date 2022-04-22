package com.nhnacademy.http.exam;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver {
    private final Socket socket;
    private final StringBuilder requestData = new StringBuilder();
    private DataInputStream in;

    public Receiver(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e = new IOException("DataInputStream 만들기 실패했습니다. : Receiver 생성자");
            e.printStackTrace();
        }
    }

    public void receiveRequest() {
        setRequestData();
    }

    private void setRequestData() {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;

        try {
            while ((line = br.readLine()).equals("")) {
                requestData.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
