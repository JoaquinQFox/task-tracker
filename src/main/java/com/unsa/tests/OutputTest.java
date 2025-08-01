package com.unsa.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.unsa.objects.Task;

public class OutputTest {
    public static void main(String[] args) {
        String filePath = "jsonTest";
        Task task = new Task("Programar comandos");

        try {
            File folder = new File(filePath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            try (Writer fileWriter = new FileWriter("jsonTest/tasks.json")) {
                fileWriter.write(task.toJson() + "\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}