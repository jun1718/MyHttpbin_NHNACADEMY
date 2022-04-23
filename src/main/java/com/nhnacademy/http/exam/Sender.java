package com.nhnacademy.http.exam;

import static java.lang.System.lineSeparator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

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

    public void sendResponse(RequestVO requestData)  {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            if (requestData.getPath().equals("/ip")) {
                String ip = requestData.getOrigin().split("\n")[0];
                json = "{\n" + "\t\"origin\" : " + ip + "\n}" + lineSeparator();
            } else if (requestData.getPath().contains("/get")) {
                json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestData) + lineSeparator();
            } else if (requestData.getPath().contains("/post")) {
                json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestData) + lineSeparator();

                System.out.println(requestData);
                System.out.println(json);
            } else {
                json = "";
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int len = json.getBytes().length;

        try {
            responseData.append("HTTP/1.1 200 OK" + lineSeparator())
                    .append(getDate() + lineSeparator())
                        .append("Content-Type: application/json" + lineSeparator())
                            .append("Content-Length: " + len + lineSeparator())
                                .append("Connection: keep-alive" + lineSeparator())
                                    .append("Server: 11Jo" + lineSeparator())
                                        .append("Access-Control-Allow-Origin: *" + lineSeparator())
                                            .append("Access-Control-Allow-Credentials: true" + lineSeparator() + lineSeparator())
                                                .append(json);
            out.writeBytes(responseData.toString());
        } catch (IOException e) {
            e = new IOException("상대방에게 메시지를 보내지 못했습니다. : sendData()");
            e.printStackTrace();

            responseData.setLength(0);
            responseData.append("HTTP/1.1 404 NOTFOUND");
            try {
                out.writeBytes(responseData.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String getDate() {
        Date currentTime = new Date();
        SimpleDateFormat dateFormat =
            new SimpleDateFormat("EEE, d MMM yyyy hh:mm:ss z", Locale.ENGLISH);

        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        return dateFormat.format(currentTime);
    }
}
