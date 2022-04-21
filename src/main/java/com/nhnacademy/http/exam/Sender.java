package com.nhnacademy.http.exam;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {
    private DataOutputStream out;
    private StringBuilder responseSb = new StringBuilder();

    public Sender(Socket socket) throws IOException {
        try {
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new IOException("DataOutputStream이 생성되지 않았습니다.");
        }
    }

    @Override
    public void run() {
        sendMessage();
    }

    private void sendMessage() {
        try (Scanner scan = new Scanner(System.in)) {
            while (isSendable()) {
                out.writeBytes(responseSb.toString());
            }
        } catch (IOException e) {
            e = new IOException("상대방에게 메시지를 보내지 못했습니다. : sendMessage()");
            e.printStackTrace();
            System.out.println("sender를 종료합니다. 앞으로 메세지를 보낼 수 없습니다.");
        }
    }

    private boolean isSendable() {
        return out != null;
    }
}
