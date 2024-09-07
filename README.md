# Task Manager Application

Welcome to the Task Manager application! This application provides a simple command-line interface to manage your tasks. You can add, update, delete, and list tasks, as well as change their status.

## Features

- **Add a Task**: Add a new task with a unique ID, description, and status.
- **Update a Task**: Update the description and status of an existing task.
- **Delete a Task**: Remove a task using its ID.
- **List All Tasks**: Display all tasks with their details.
- **List Tasks by Status**: Filter and display tasks by their status.
- **Change Task Status**: Update the status of an existing task.

## Prerequisites

- **Java**: Ensure you have Java installed on your machine.
- **Jackson**: The application uses Jackson for JSON file operations. Ensure the Jackson library is included in your project.

## Running the Application

1. **Compile and Run**:
   - Ensure Jackson dependencies are included in your `pom.xml` if using Maven, or add the Jackson JAR files to your classpath.
   - Compile the `TaskManager` class.
   - Run the `TaskManager` class to start the application.

2. **Main Class**:
   - The entry point of the application is the `main` method in the `TaskManager` class. Running this class will display the command-line interface and allow you to interact with the task manager.

## JSON File Operations

The application uses Jackson to read from and write to a `tasks.json` file:

- **Writing Tasks**: When adding or updating tasks, the tasks are written to `tasks.json` in the same directory.
- **Reading Tasks**: Upon startup, the application reads existing tasks from `tasks.json`. If the file does not exist, it will be created.

## Menu Options

Upon running the application, you will see the following options:

1. **Add a Task**
2. **Update a Task**
3. **Delete a Task**
4. **List All Tasks**
5. **List Tasks by Status**
6. **Change Task Status**
0. **Exit**

## Example Usage

### Adding a Task

1. Select option **1**.
2. Enter the **Task number** (unique identifier for the task).
3. Enter the **Task description**.
4. Enter the **Task Status**: `todo`, `in-progress`, or `done` (case-sensitive).

```
Enter the Task number: 1
Enter the Task description: Buy groceries
Enter the Task Status: todo
```

### Updating a Task
1. Select option **2**.
2. Enter the **Task number** you want to update.
3. Enter the new **Task description**.
4. Enter the new **Task Status**: `todo`, `in-progress`, or `done`.

```
Enter the Task number you want to update: 1
Enter the new description: Buy groceries and milk
Enter the new Task Status: in-progress
```

Deleting a Task
1. Select option **3**.
2. Enter the **Task number** you want to delete.
```
Enter the Task number you want to delete: 1
```

Listing All Tasks
1. Select option **4**.
This will display a list of all tasks with their details.

Listing Tasks by Status
1. Select option **5**.
2. Enter the **Task Status** you want to filter by: `todo`, `in-progress`, or `done`.
```
Enter the Task Status: done
```

Changing Task Status
1. Select option **6**.
2. Enter the **Task ID** whose status you want to change.
3. Enter the new Task Status: `todo`, `in-progress`, or `done`.
```
Enter the Task ID whose status you want to change: 1
Enter the new Task Status: done
```

Exiting the Application
1. Select option **0**.

## Important Notes

- **Task IDs**: Ensure task IDs are unique.
- **Status Case Sensitivity**: Task statuses are case-sensitive.
- **JSON File**: The application will create and manage `tasks.json` in the same directory.

## Author

- **Created By**: Amit Sanuria

