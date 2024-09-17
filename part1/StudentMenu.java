import java.util.*;
import Common.db.*;

public class StudentMenu {
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        while (true) {
            System.out.println("\nStudent Menu");
            System.out.println("1. View My Courses");
            System.out.println("2. View My Grades");
            System.out.println("3. Exit");
            System.out.print("Enter option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    viewMyCourses();
                    break;
                case 2:
                    viewMyGrades();
                    break;
                case 3:
                    return;  // Exit the menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void viewMyCourses() {
        int studentId = getCurrentUserId();
        List<String> courses = DatabaseManager.getCoursesForStudent(studentId);
        if (courses.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
        } else {
            System.out.println("Courses you are enrolled in:");
            for (String course : courses) {
                System.out.println(course);
            }
        }
    }

    public void viewMyGrades() {
        int studentId = getCurrentUserId();
        List<String> grades = DatabaseManager.getGradesForStudent(studentId);
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
