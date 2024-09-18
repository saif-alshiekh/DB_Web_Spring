package Menus;

import db.DatabaseManager;
import java.util.*;

public class StudentMenu {
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu(int userid) {
        while (true) {
            System.out.println("\nStudent Menu");
            System.out.println("1. View My Courses");
            System.out.println("2. View My Grades");
            System.out.println("3. Exit");
            System.out.print("Enter option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewMyCourses(userid);
                    break;
                case 2:
                    viewMyGrades(userid);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void viewMyCourses(int userid) {
        List<String> courses = DatabaseManager.getCoursesForStudent(userid);
        if (courses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
        } else {
            System.out.println("Courses you are enrolled in:");
            for (String course : courses) {
                System.out.println(course);
            }
        }
    }

    public void viewMyGrades(int userid) {
        List<String> grades = DatabaseManager.getGradesForStudent(userid);
        if (grades.isEmpty()) {
            System.out.println("No grades available.");
        } else {
            System.out.println("Your grades:");
            for (String grade : grades) {
                System.out.println(grade);
            }
        }
    }
}
