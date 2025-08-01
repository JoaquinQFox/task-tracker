package com.unsa.cli;

// import java.util.ArrayList;
// import java.util.List;

// import com.unsa.objects.Task;

public class TaskManager {
    // List<Task> tasks = new ArrayList<>();
    // String filePath = "jsonTest/tasks.json";

    public static void main(String[] args) {
        readAction(args);
    }

    public static void readAction(String[] input) {
        String action = input[0];

        if (action.startsWith("mark")) {
            System.out.println("Task marked succesfully (ID:1)");
        }

        switch (action) {
            case "add" -> System.out.println("Task added succesfully (ID:1)");
            case "update" -> System.out.println("Task updated succesfully (ID:1)");
            case "delete" -> System.out.println("Task deleted succesfully");
            case "list" -> System.out.println("Tasks listed");
            default -> System.out.println("Action not recognized");
        }
    }

    public static void readTasks() {

    }
}
