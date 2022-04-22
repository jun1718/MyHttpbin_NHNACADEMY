package com.nhnacademy.http.exam;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Sender {
    private DataOutputStream out;
    private StringBuilder responseData = new StringBuilder();

    public Sender(Socket socket) {
        try {
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e = new IOException("DataOutputStream을 생성하지 못했습니다. Sender 생성자오류");
            e.printStackTrace();
        }
    }

    public void sendResponse()  {
        try {
            String str = "{\n" +
                "  \"args\": {},\n" +
                "  \"headers\": {\n" +
                "    \"Accept\": \"*/*\",\n" +
                "    \"Host\": \"httpbin.org\",\n" +
                "    \"User-Agent\": \"curl/7.79.1\",\n" +
                "    \"X-Amzn-Trace-Id\": \"Root=1-62622c9a-3158e02112f81c8261f7aa7e\"\n" +
                "  },\n" +
                "  \"origin\": \"112.216.11.34\",\n" +
                "  \"url\": \"http://httpbin.org/get\"\n" +
                "}" + System.lineSeparator();


            responseData.append("HTTP/1.1 200 OK" + System.lineSeparator())
                    .append("Date: Fri, 22 Apr 2022 04:18:34 GMT" + System.lineSeparator())
                        .append("Content-Type: application/json" + System.lineSeparator())
                            .append("Content-Length: " + str.getBytes().length + System.lineSeparator())
                                .append("Connection: keep-alive" + System.lineSeparator())
                                    .append("Server: gunicorn/19.9.0" + System.lineSeparator())
                                        .append("Access-Control-Allow-Origin: *" + System.lineSeparator())
                                            .append("Access-Control-Allow-Credentials: true" + System.lineSeparator() + System.lineSeparator())
                                                .append(str);
            out.writeBytes(responseData.toString());
        } catch (IOException e) {
            e = new IOException("상대방에게 메시지를 보내지 못했습니다. : sendData()");
            e.printStackTrace();
        }
    }
}
