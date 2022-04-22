package com.nhnacademy.http.exam;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    public void start() {
        try(ServerSocket serverSocket = new ServerSocket(80)) {
            while (true) {
                this.connect(serverSocket.accept());
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("서버소켓 오류로 서버를 종료합니다.");
        }
    }

    private void connect(Socket socket) {
        Thread thread = new Thread(new Connector(socket));
        thread.start();
    }
}
