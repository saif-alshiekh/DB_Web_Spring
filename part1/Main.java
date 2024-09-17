import java.util.*;
import utils.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import common.db.DatabaseManager;


public class Main {

    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        while (true) {
            System.out.println("1. Signup");
            System.out.println("2. Exit");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    signup();
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }


    private static void signup() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter role (student/instructor/supervisor): ");
        String role = scanner.nextLine();
        System.out.println("Enter person ID: ");
        int personId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        if (DatabaseManager.addUser(username, password, role, personId)) {
            System.out.println("Signup successful!");
        } else {
            System.out.println("Signup failed. Please try again.");
        }
    }

    private static void login() {
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Username: ");
            String username = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();

            if (DatabaseManager.authenticateUser(username, password)) {
                System.out.println("Login successful!");
                loggedIn = true;
                // Additional functionality post-login can be implemented here
            } else {
                System.out.println("Login failed. Please try again.");
            }
        }
    }


}
