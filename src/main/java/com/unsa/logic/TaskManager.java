package com.unsa.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

import com.unsa.objects.Task;

public class TaskManager {
    private final String folderPath = "jsonTest";
    private final String filePath = "jsonTest/tasks.json";
    private final List<Task> tasksList;

    public TaskManager() {
        tasksList = new LinkedList<>();
    }

    // Method to read the first argument and action of the command
    public void readCommand(String[] input) {
        String action = input[0];

        if (action.startsWith("mark")) {
            System.out.println("Task marked succesfully (ID:1)");
        }

        switch (action) {
            case "add" -> addTask(input);
            case "update" -> System.out.println("Task updated succesfully (ID:1)");
            case "delete" -> deleteTask(input);
            case "list" -> System.out.println("Tasks listed");
            default -> System.out.println("Action not recognized");
        }
    }


    // Method to add a new Task to the list of tasks
    public void addTask(String[] input) {
        if (checkAddCommand(input)) {
            String description = input[1];
            Task task = new Task(description);

            this.tasksList.add(task);
        }
    }

    // Method to check the correct usage of the add command
    public boolean checkAddCommand(String[] input) {
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

    // Method to delete a task with the id entered
    public void deleteTask(String[] input) {
        if (checkDeleteCommand(input)) {
            int id = Integer.parseInt(input[1]);
            if (tasksList.remove(new Task(id, null, null, null, null))) {
                System.out.println("ID deleted succesfully");
            }
            else {
                System.out.println("Task with Id:" + id + " not found");
            }
        }
    }

    // Method to check the correct usage of delte command
    public boolean checkDeleteCommand(String[] input) {
        if (input.length > 2) {
            System.out.println("There must only be two arguments");
            System.out.println("Example: delete [id]");
            return false;
        }

        if (input.length < 2) {
            System.out.println("You gotta specify the id of the task you want to delete");
            System.out.println("Example: delete [id]");
            return false;
        }

        return true;
    }

    // Method to read tasks from json file if it exists
    public void readTasks() {
        try {
            if (!verifyFolder()) {
                return;
            }

            try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {
                String jsonText = "";
                String line;

                while ((line = fileReader.readLine()) != null) {
                    jsonText +=  line.trim();
                }

                jsonText = jsonText.substring(1, jsonText.length() - 1);

                String[] tasksInJson = jsonText.split("(?<=\\}),(?=\\{)");

                for (String str : tasksInJson) {
                    tasksList.add(Task.taskFromJson(str));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to save tasks into a json file
    public void saveTasks() {
        try {
            verifyFolder();
            try (Writer fileWriter = new FileWriter(filePath)) {
                fileWriter.write("[\n");

                for (int i = 0; i < this.tasksList.size(); i++) {
                    if (i < this.tasksList.size() - 1)
                        fileWriter.write("\t" + this.tasksList.get(i).toJson() + ",\n");
                    else
                        fileWriter.write("\t" + this.tasksList.get(i).toJson() + "\n");
                }

                fileWriter.write("]\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method that creates the folder of json file if it doesn't exists already and return if it existed
    public boolean verifyFolder() throws IOException {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
            return false;
        }
        return true;
    }
}
