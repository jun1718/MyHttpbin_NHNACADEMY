package com.nhnacademy.http.exam;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Receiver {
    private DataInputStream in;
    private final StringBuilder requestData = new StringBuilder();
    private final RequestVO request;

    public Receiver(Socket socket, RequestVO request) {
        this.request = request;
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

        parser(br);

        System.out.println(requestData);
    }

    private void parser(BufferedReader br) {
        int sequence = 0;
        String line;
        try {
//            while (!(line = br.readLine()).equals(System.lineSeparator()) || !line.equals("")) {
            while (!(line = br.readLine()).equals("")) {
                requestData.append(line + System.lineSeparator());
                if (sequence++ == 0) {
                    String[] metaData = line.split(" ");
                    request.setMethod(metaData[0]);
                    request.setPath(metaData[1]);
                    request.setScheme(metaData[2]);
                    continue;
                }
            }


            System.out.println(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
