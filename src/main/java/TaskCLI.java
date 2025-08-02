

public class TaskCLI {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.readTasks();
        taskManager.readCommand(args);
        taskManager.saveTasks();
    }
}
