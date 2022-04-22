package com.nhnacademy.http.exam;

public class MainShell {
    public static void main(String args[]) {
        MainShell main = new MainShell();
        main.run();
    }

    private void run() {
        MyServer server = new MyServer();
        server.start();
    }
}
