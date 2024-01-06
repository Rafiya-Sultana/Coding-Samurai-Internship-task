package com.todolist.beans;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {

	private ArrayList<Task> taskList;
    private Scanner scanner;

    public TodoList() {
        this.taskList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            showMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    markTaskAsComplete();
                    break;
                case 3:
                    viewTasks();
                    break;
                case 4:
                    removeTask();
                    break;
                case 5:
                    System.out.println("Exiting the To-Do List application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n--- To-Do List Application ---");
        System.out.println("1. Add Task");
        System.out.println("2. Mark Task as Complete");
        System.out.println("3. View Tasks");
        System.out.println("4. Remove Task");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        int choice = -1;

        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            // Ignore invalid input
        }

        return choice;
    }

    private void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        Task newTask = new Task(description);
        taskList.add(newTask);
        System.out.println("Task added: " + description);
    }

    private void markTaskAsComplete() {
        viewTasks();
        System.out.print("Enter the task number to mark as complete: ");
        int taskNumber = getUserChoice();

        if (isValidTaskNumber(taskNumber)) {
            Task task = taskList.get(taskNumber - 1);
            task.markAsComplete();
            System.out.println("Task marked as complete: " + task.getDescription());
        } else {
            System.out.println("Invalid task number. Please try again.");
        }
    }

    private void viewTasks() {
        System.out.println("\n--- Your Tasks ---");
        if (taskList.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String status = task.isCompleted() ? "[X]" : "[ ]";
                System.out.println((i + 1) + ". " + status + " " + task.getDescription());
            }
        }
    }

    private void removeTask() {
        viewTasks();
        System.out.print("Enter the task number to remove: ");
        int taskNumber = getUserChoice();

        if (isValidTaskNumber(taskNumber)) {
            Task removedTask = taskList.remove(taskNumber - 1);
            System.out.println("Task removed: " + removedTask.getDescription());
        } else {
            System.out.println("Invalid task number. Please try again.");
        }
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return taskNumber > 0 && taskNumber <= taskList.size();
    }
}
