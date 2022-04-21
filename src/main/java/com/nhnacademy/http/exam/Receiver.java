package com.nhnacademy.http.exam;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread {
    private DataInputStream in;
    private byte[] bytes = new byte[2048];
    private StringBuilder requestSb = new StringBuilder();

    public Receiver(Socket socket) throws IOException {
        try {
            socket.getInputStream().read(bytes);
            this.in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            this.in = null;
            throw new IOException("DataInputStream이 생성되지 않았습니다.");
        }
    }

    @Override
    public void run() {
        while (isReaceivable()) {
            try {
                receiveMessage();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Receiver를 종료합니다. 앞으로 메세지를 받을 수 없습니다.");
                break;
            }
        }
    }

    private boolean isReaceivable() {
        return this.in != null;
    }

    private void receiveMessage() throws IOException {
        try {
            System.out.println(in.readUTF());
        } catch (IOException e) {
            throw new IOException("상대 소켓으로부터 데이터를 받아오는 것을 실패했습니다 : in.readUTF()");
        }
    }
}
