package com.unsa.cli;

import java.util.ArrayList;
import java.util.List;

import com.unsa.objects.Task;

public class TaskManager {
    static List<Task> tasks = new ArrayList<>();
    // String filePath = "jsonTest/tasks.json";

    public static void main(String[] args) {
        System.out.println(args[0]);
        System.out.println(args[1]);
        readAction(args);
        System.out.println("List: " + tasks);
    }

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

    public static void addTask(String[] input) {
        if (checkAddCommand(input)) {
            String description = input[1];
            Task task = new Task(description);

            tasks.add(task);
        }
    }

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

    public static void saveTasks() {

    }
}
