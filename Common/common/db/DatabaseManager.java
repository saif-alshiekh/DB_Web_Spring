package common.db;

import java.sql.*;
import utils.DbConnection;

public class DatabaseManager {
    public static boolean authenticateUser(String username, String password) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";  // Assuming you have a users table with username and password fields
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;  // User is authenticated
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // User is not authenticated
    }

    public static boolean addUser(String username, String password, String role) {
        String sql = "INSERT INTO Users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            int affectedRows = ps.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to add a course
    public static boolean addCourse(String courseName, int instructorId) {
        String sql = "INSERT INTO Courses (course_name, instructor_id) VALUES (?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, courseName);
            ps.setInt(2, instructorId);
            int affectedRows = ps.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to enroll a student in a course
    public static boolean enrollStudent(int studentId, int courseId) {
        String sql = "INSERT INTO Enrollments (student_id, course_id) VALUES (?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            int affectedRows = ps.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to record or update a grade
    public static boolean recordGrade(int studentId, int courseId, String grade) {
        String sql = "INSERT INTO Grades (student_id, course_id, grade) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE grade = VALUES(grade)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setString(3, grade);
            int affectedRows = ps.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    

}
