package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    static String fileName = "Tasks.json";

    public static void listAllTasks() {
        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Read existing JSON content from the file
        List<Task> tasks = readTasksFromFile(objectMapper, fileName);

        for (Task task : tasks) {
            System.out.println("ID: " + task.getId() + " | Description: " + task.getDescription() + " | Status: " + task.getStatus());
        }

        if (tasks.isEmpty()) {
            System.out.println("The task list is empty");
        }
    }

    public static void addTask(Task newTask) {

        // Create an ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Read existing JSON content from the file
        List<Task> tasks = readTasksFromFile(objectMapper, fileName);

        if (isDuplicateId(tasks, newTask.getId())) {
            System.out.println("Task with ID " + newTask.getId() + " already exists.");
        } else {
            // Add new Task to the list
            tasks.add(newTask);

            // Write all tasks back to the JSON file
            performTask(objectMapper, "Add", tasks);
        }

    }

    public static void updateTask(Task newTask, long id) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Task> tasks = readTasksFromFile(objectMapper, fileName);
        boolean updated = false;
        for (Task task : tasks) {
            if (id == task.getId()) {
                task.setDescription(newTask.getDescription());
                task.setStatus(newTask.getStatus());
                task.setUpdatedAt(new Date());
                performTask(objectMapper, "Update", tasks);
                updated = true;
                break;
            }
        }

        if (!updated) System.out.println("No task found with id: " + id);
    }

    public static void updateStatus(String status, long id) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Task> tasks = readTasksFromFile(objectMapper, fileName);
        boolean updated = false;
        for (Task task : tasks) {
            if (id == task.getId()) {
                task.setStatus(status);
                task.setUpdatedAt(new Date());
                performTask(objectMapper, "Update", tasks);
                updated = true;
                break;
            }
        }

        if (!updated) System.out.println("No task found with id: " + id);
    }

    public static void deleteTask(long id) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Task> tasks = readTasksFromFile(objectMapper, fileName);
        boolean deleted = false;
        for (Task task : tasks) {
            if (id == task.getId()) {
                tasks.remove(task);
                performTask(objectMapper, "Delete", tasks);
                deleted = true;
                break;
            }
        }

        if (!deleted) System.out.println("No task found with id: " + id);
    }

    public static void findByStatus(String status) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Task> tasks = readTasksFromFile(objectMapper, fileName);
        boolean foundAtLeastOne = false;
        for (Task task : tasks) {
            if (Objects.equals(task.getStatus(), status)) {
                foundAtLeastOne = true;
                System.out.println("ID: " + task.getId() + " | Description: " + task.getDescription() + " | Status: " + task.getStatus());
            }
        }
        if (!foundAtLeastOne) {
            System.out.println("No task found with status: " + status);
        }
    }

    private static List<Task> readTasksFromFile(ObjectMapper objectMapper, String fileName) {
        File file = new File(fileName);
        List<Task> tasks = new ArrayList<>();

        if (file.exists() && file.length() != 0) { // Check if file exists and is not empty
            try {
                tasks = objectMapper.readValue(file, new TypeReference<List<Task>>() {
                });
            } catch (IOException e) {
                System.err.println("Failed to read existing tasks from file: " + e.getMessage());
            }
        } else {
            // Create the file if it does not exist
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Failed to create new file: " + e.getMessage());
            }
        }
        return tasks;
    }

    public static void performTask(ObjectMapper objectMapper, String taskName, List<Task> tasks) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), tasks);
            System.out.println("Task " + taskName + " to JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isDuplicateId(List<Task> tasks, long id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return true; // Found a duplicate
            }
        }
        return false;
    }

    public static void main(String[] args) {
//        Task task = new Task(3, "Updated 3", "In progress");

        Scanner sc = new Scanner(System.in);
        int userInput;

        do {
            System.out.println("Welcome to your Task Manager");
            System.out.println("Press 1: To add the task");
            System.out.println("Press 2: To update a task");
            System.out.println("Press 3: To delete a task");
            System.out.println("press 4: To list all tasks");
            System.out.println("press 5: To list all tasks by status");
            System.out.println("press 6: To change status of task");
            System.out.println("press 0: To exit your task manager");
            userInput = sc.nextInt();
            sc.nextLine();
            long taskId;
            String description;
            String status;
            Task newTask;
            switch (userInput) {
                case 1:
                    System.out.println("Enter the Task number");
                    taskId = sc.nextLong();
                    sc.nextLine(); // Consume leftover newline
                    System.out.println("Enter the Task description");
                    description = sc.nextLine();
                    while (true) {
                        System.out.println("Enter the Task Status: todo / in-progress / done Note: Are case sensitive");
                        status = sc.nextLine();
                        if (status.equals("todo") || status.equals("in-progress") || status.equals("done")) {
                            break;
                        }
                    }
                    newTask = new Task(taskId, description, status);
                    addTask(newTask);
                    break;

                case 2:
                    System.out.println("Enter the Task number you want to update");
                    taskId = sc.nextLong();
                    sc.nextLine();
                    System.out.println("Enter the description to be updated");
                    description = sc.nextLine();
                    while (true) {
                        System.out.println("Enter the Task Status: todo / in-progress / done Note: Are case sensitive");
                        status = sc.nextLine();
                        if (status.equals("todo") || status.equals("in-progress") || status.equals("done")) {
                            break;
                        }
                    }
                    newTask = new Task(taskId, description, status);
                    updateTask(newTask, newTask.getId());
                    break;

                case 3:
                    System.out.println("Enter the Task number you want to delete");
                    taskId = sc.nextLong();
                    deleteTask(taskId);
                    break;

                case 4:
                    listAllTasks();
                    break;

                case 5:
                    while (true) {
                        System.out.println("Enter the Task status you want to see the list of");
                        System.out.println("Enter the Task Status: todo / in-progress / done Note: Are case sensitive");
                        status = sc.nextLine();
                        if (status.equals("todo") || status.equals("in-progress") || status.equals("done")) {
                            break;
                        }
                    }
                    findByStatus(status);
                    break;

                case 6:
                    System.out.println("Enter the Task id whose status you want to change");
                    taskId = sc.nextLong();
                    System.out.println("Enter the Task status");
                    while (true) {
                        System.out.println("Enter the Task Status: todo / in-progress / done Note: Are case sensitive");
                        status = sc.nextLine();
                        if (status.equals("todo") || status.equals("in-progress") || status.equals("done")) {
                            break;
                        }
                    }
                    updateStatus(status, taskId);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Please enter the correct command");
            }
        } while (userInput != 0);
        sc.close();
        System.out.println("Thank you for using Task Manager. Created By: Amit Sanuria");
    }
}