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
import com.unsa.objects.TaskStatus;

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
            markTask(input);
            return;
        }

        switch (action) {
            case "add" -> addTask(input);
            case "update" -> updateTask(input);
            case "delete" -> deleteTask(input);
            case "list" -> listTasks(input);
            default -> System.out.println("Action not recognized");
        }
    }


    // Method to add a new Task to the list of tasks
    private void addTask(String[] input) {
        if (checkAddCommand(input)) {
            String description = input[1];
            Task task = new Task(description);

            this.tasksList.add(task);
            System.out.println("Task added succesfully (ID: " + task.getId() + ")");
        }
    }

    // Method to check the correct usage of the add command
    private boolean checkAddCommand(String[] input) {
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
    private void deleteTask(String[] input) {
        if (checkDeleteCommand(input)) {
            int id = Integer.parseInt(input[1]);
            if (tasksList.remove(new Task(id, null, null, null, null))) {
                System.out.println("Task deleted succesfully");
            }
            else {
                System.out.println("Task id not found");
            }
        }
    }

    // Method to check the correct usage of delete command
    private boolean checkDeleteCommand(String[] input) {
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

    // Method to update the task with the id entered
    private void updateTask(String[] input) {
        if (checkUpdateCommand(input)) {
            int id = Integer.parseInt(input[1]);
            String description = input[2];

            Task taskToUpdate = null;
            for (Task task : tasksList) {
                if (task.getId() == id) {
                    taskToUpdate = task;
                    break;
                }
            }

            if (taskToUpdate != null) {
                taskToUpdate.setDescription(description);
                System.out.println("Task updated succesfully (ID: " + id + ")");
            }
            else 
                System.out.println("Task with Id:" + id + " not found");
        }
    }

    // Method to chekc the correct usage of update command
    private boolean checkUpdateCommand(String[] input) {
        if (input.length > 3) {
            System.out.println("There must be three arguments");
            System.out.println("Example: update [id] \"Your description\"");
            return false;
        }

        if (input.length < 3) {
            System.out.println("You gotta specify the id of the updated task and the new description");
            System.out.println("Example: update [id] \"Your description\"");
            return false;
        }

        return true;
    }

    // Method to update the status of the task with the id entered
    private void markTask(String[] input) {
        if (!checkMarkCommand(input))
            return;
        
        String markCommand = input[0];
        String markStatus = getMarkCommandStatus(markCommand);
        TaskStatus taskStatus = TaskStatus.NONE;

        switch (markStatus) {
                case "todo" -> taskStatus = TaskStatus.TO_DO;
                case "in-progress" -> taskStatus = TaskStatus.IN_PROGRESS;
                case "done" -> taskStatus = TaskStatus.DONE;
        }

        int id = Integer.parseInt(input[1]);
        Task taskUpdated = null;
        for (Task task : tasksList) {
            if (task.getId() == id) {
                taskUpdated = task;
                break;
            }
        }

        if (taskUpdated != null) {
            taskUpdated.setStatus(taskStatus);
            System.out.println("Task updated succesfully");
        }
        else 
            System.out.println("Task id not found");
    }

    // Method to verify the corract usage of mark command
    private String getMarkCommandStatus(String markCommand) {
        String pattern = "mark-";
        int start = markCommand.indexOf(pattern) + pattern.length();
        return markCommand.substring(start);
    }

    private boolean checkMarkCommand(String[] input) {
        if (input.length > 2) {
            System.out.println("List command incorrectly used");
            System.out.println("Example: mark-[status] [id]");
            System.out.println("status options: [todo, in-progress, done]");
            return false;
        }

        if (input.length == 2) {
            String markCommand = input[0];
            String markStatus = getMarkCommandStatus(markCommand);

            if (!markStatus.equals("todo") && !markStatus.equals("in-progress") && !markStatus.equals("done")) {
                System.out.println("Incorrect status command");
                System.out.println("status options: [todo, in-progress, done]");
                return false;
            }
        }
        else {
            System.out.println("Incorrect status command");
            System.out.println("status options: [todo, in-progress, done]");
        }

        return true;
    }

    // Method general to list tasks
    private void listTasks(String[] input) {
        if (!checkListCommand(input)) {
            return;
        }

        TaskStatus taskStatus = TaskStatus.NONE;

        if (input.length == 2) {
            String status = input[1]; 
            switch (status) {
                case "todo" -> taskStatus = TaskStatus.TO_DO;
                case "in-progress" -> taskStatus = TaskStatus.IN_PROGRESS;
                case "done" -> taskStatus = TaskStatus.DONE;
            }
        }

        if (taskStatus != TaskStatus.NONE)
            listAllTasks(taskStatus);
        else
            listAllTasks();
    }

    // Private method to list all tasks in list
    private void listAllTasks() {
        if (tasksList.isEmpty()) {
            System.out.println("You don't have any task");
            return;
        }

        for (Task task : tasksList)
            System.out.println(task);
    }
    
    // Private method to list all task with a certain status
    private void listAllTasks(TaskStatus status) {
        boolean empty = true;
        for (Task task : tasksList) {
            if (task.getTaskStatus() == status) {
                System.out.println(task);
                empty = false;
            }
        }
        if (empty)
            System.out.println("Currently you don't have any task with " + status.getStatus() + " status");
    }

    // Method to check the correct usage of list command
    private boolean checkListCommand(String[] input) {
        if (input.length > 2) {
            System.out.println("List command incorrectly used");
            System.out.println("Example: list [status](optional)");
            System.out.println("status options: [todo, in-progress, done]");
            return false;
        }

        if (input.length == 2) {
            String taskStatus = input[1];
            if (!taskStatus.equals("todo") && !taskStatus.equals("in-progress") && !taskStatus.equals("done")) {
                System.out.println("Incorrect status command");
                System.out.println("status options: [todo, in-progress, done]");
                return false;
            }
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
    private boolean verifyFolder() throws IOException {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
            return false;
        }
        return true;
    }
}
