package Menus;

import db.DatabaseManager;

import java.util.*;


public class SupervisorMenu {
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        while (true) {
            System.out.println("\nSupervisor Menu");
            System.out.println("1. Add User");
            System.out.println("2. Delete User");
            System.out.println("3. Add Course");
            System.out.println("4. Delete Course");
            System.out.println("5. Exit");
            System.out.print("Enter option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    deleteCourse();
                    break;
                case 5:
                    return;  // Exit the menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (student/instructor/supervisor): ");
        String role = scanner.nextLine();

        if (DatabaseManager.addUser(username, password, role)) {
            System.out.println("User added successfully!");
        } else {
            System.out.println("Failed to add user.");
        }
    }

    private void deleteUser() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        if (DatabaseManager.deleteUser(userId)) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("Failed to delete user.");
        }
    }

    private void addCourse() {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter instructor ID (or 0 for none): ");
        int instructorId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (DatabaseManager.addCourse(courseName, instructorId)) {
            System.out.println("Course added successfully!");
        } else {
            System.out.println("Failed to add course.");
        }
    }

    private void deleteCourse() {
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        if (DatabaseManager.deleteCourse(courseId)) {
            System.out.println("Course deleted successfully!");
        } else {
            System.out.println("Failed to delete course.");
        }
    }
}
