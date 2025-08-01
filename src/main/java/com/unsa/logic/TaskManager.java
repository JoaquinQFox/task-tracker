package com.unsa.logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.unsa.objects.Task;

public class TaskManager {
    static List<Task> tasks = new ArrayList<>();
    static String folderPath = "jsonTest";
    static String filePath = "jsonTest/tasks.json";

    public static void main(String[] args) {
        System.out.println(args[0]);
        System.out.println(args[1]);
        readAction(args);
        System.out.println("List: " + tasks);
        tasks.add(new Task("comida"));
        tasks.add(new Task("comida"));
        tasks.add(new Task("comida"));
        saveTasks(tasks);
    }

    // Method to read the first argument and action of the command
    public static void readAction(String[] input) {
        String action = input[0];

        if (action.startsWith("mark")) {
            System.out.println("Task marked succesfully (ID:1)");
        }

        switch (action) {
            case "add" -> addTask(input);
            case "update" -> System.out.println("Task updated succesfully (ID:1)");
            case "delete" -> System.out.println("Task deleted succesfully");
            case "list" -> System.out.println("Tasks listed");
            default -> System.out.println("Action not recognized");
        }
    }


    // Method to add a new Task to the list of tasks
    public static void addTask(String[] input) {
        if (checkAddCommand(input)) {
            String description = input[1];
            Task task = new Task(description);

            tasks.add(task);
        }
    }

    // Method to check the correct usage of the add command
    public static boolean checkAddCommand(String[] input) {
        if (input.length > 2) {
            System.out.println("There must only be two arguments");
            System.out.println("Example: add \"Your description\"");
            return false;
        }

        if (input.length < 2) {
            System.out.println("You gotta send the command with a description");
            System.out.println("Description muste be encolsed with quotation marks if there are more than one word");
            System.out.println("Example: add \"Your description\"");
            return false;
        }

        return true;
    }

    public static void readTasks() {

    }

    // Method to save tasks into a json file
    public static void saveTasks(List<Task> tasks) {
        try {
            verifyFolder();
            try (Writer fileWriter = new FileWriter(filePath)) {
                fileWriter.write("[\n");

                for (int i = 0; i < tasks.size(); i++) {
                    if (i < tasks.size() - 1)
                        fileWriter.write("\t" + tasks.get(i).toJson() + ",\n");
                    else
                        fileWriter.write("\t" + tasks.get(i).toJson() + "\n");
                }

                fileWriter.write("]\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method that creates the folder of json file if it doesn't exists already
    public static void verifyFolder() throws IOException {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
