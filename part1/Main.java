import java.util.*;
import utils.DbConnection;
import java.sql.*;
import Common.db.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("\nWelcome to the Student Grading System");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    loginScreen();
                    break;
                case 2:
                    signUpScreen();
                    break;
                case 3:
                    System.out.println("Exiting system.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void loginScreen() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        String role = DatabaseManager.authenticateUser(username, password);
        SupervisorMenu supermenu = new SupervisorMenu();
        InstructorMenu insmenu = new InstructorMenu();
        StudentMenu stdmenu = new StudentMenu();

        if (role != null) {
            switch (role) {
                case "supervisor":
                    supermenu.displayMenu();
                    break;
                case "instructor":
                    insmenu.displayMenu();
                    break;
                case "student":
                    stdmenu.displayMenu();
                    break;
            }
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }

    private static void signUpScreen() {
        System.out.println("\nSign Up");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Role (student/instructor/supervisor): ");
        String role = scanner.nextLine();

        if (DatabaseManager.addUser(username, password, role)) {
            System.out.println("Sign up successful! You can now login.");
        } else {
            System.out.println("Sign up failed. Please try again.");
        }
    }



}
