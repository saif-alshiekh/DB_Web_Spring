package Menus;

import db.DatabaseManager;

import java.util.*;

public class InstructorMenu {
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu(int userid) {
        while (true) {
            System.out.println("\nInstructor Menu");
            System.out.println("1. View My Courses");
            System.out.println("2. Enroll Student");
            System.out.println("3. Record Grade");
            System.out.println("4. Exit");
            System.out.print("Enter option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewMyCourses(userid);
                    break;
                case 2:
                    enrollStudent();
                    break;
                case 3:
                    recordGrade();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewMyCourses(int userid) {
        List<String> courses = DatabaseManager.getCoursesForInstructor(userid);
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            System.out.println("Courses you are teaching:");
            for (String course : courses) {
                System.out.println(course);
            }
        }
    }



    private void enrollStudent() {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        if (DatabaseManager.enrollStudent(studentId, courseId)) {
            System.out.println("Student enrolled successfully!");
        } else {
            System.out.println("Failed to enroll student.");
        }
    }

    private void recordGrade() {
        System.out.print("Enter student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter course ID: ");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter grade (e.g., A, B+): ");
        String grade = scanner.nextLine();

        if (DatabaseManager.recordGrade(studentId, courseId, grade)) {
            System.out.println("Grade recorded successfully!");
        } else {
            System.out.println("Failed to record grade.");
        }
    }
}
