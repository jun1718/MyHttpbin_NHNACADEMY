package com.nhnacademy.http.exam;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileMake {
    public FileMake(String name, String body) {
        File file = new File(name);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
