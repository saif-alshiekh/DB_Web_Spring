import java.util.*;
import Common.db.*;

public class InstructorMenu {
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
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
                    viewMyCourses();
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

    private void viewMyCourses() {
        int instructorId = getCurrentUserId(); // This method needs to retrieve the current user's ID from the session or context
        List<Course> courses = DatabaseManager.getCoursesForInstructor(instructorId);
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            System.out.println("Courses you are teaching:");
            for (Course course : courses) {
                System.out.println("Course ID: " + course.getCourseId() + ", Course Name: " + course.getCourseName());
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
