package com.unsa.tests;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import com.unsa.objects.Task;

public class OutputTest {
    public static void main(String[] args) {
        Task task = new Task("Programar comandos");

        try (PrintWriter writer = new PrintWriter("tasks.json", "UTF-8")){
            writer.println(task.toJson() + "\n");
        }
        catch(FileNotFoundException | UnsupportedEncodingException e) {
            e.getMessage();
        }
    }
}