# Task Tracker

This is a simple command line interface (CLI) application to manage tasks.

## Features
- **Add a task:** Add a new task with a description.
- **Update a task:** Update a task with a new description.
- **Delete a task:** Delete a task by it's ID
- **Mark a task:** Mark a task as "todo", "in-progress" or "done".
- **List tasks:** List all tasks with status specified or all in general.

# Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/JoaquinQFox/task-tracker.git
   cd task-tracker

2. **Compile the source code:**
    ```bash
    cd src/main/java
    javac *.java
3. **Run the application:**
    ```bash
   java TaskCLI <command> [arguments]
   ```

## Usage 

```bash
    # Adding a new task
    java TaskCLI add "Do homework"
    # Output: Task added successfully (ID: 1)

    # Updating a task
    java TaskCLI update 1 "Do math homework"
    # Output: Task updated successfully (ID: 1)

    # Deleting a task
    java TaskCLI delete 1
    # Output: Task deleted successfully

    # Marking a task as in progress
    java TaskCLI mark-in-progress 1
    # Output: Task updated succesfully

    # Marking a task as done
    java TaskCLI mark-done 1
    # Output: Task updated succefully

    # Marking a task as todo
    java TaskCLI mark-todo
    # Output: Task updated succesfully

    # Listing all tasks
    java TaskCLI list
    # Output: List of all tasks

    # Listing tasks by status
    java TaskCLI list todo
    java TaskCLI list in-progress
    java TaskCLI list done
```