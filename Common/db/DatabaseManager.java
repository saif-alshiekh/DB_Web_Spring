package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import utils.DbConnection;

public class DatabaseManager {
    public static String authenticateUser(String username, String password) {
        String query = "SELECT role FROM Users WHERE username = ? AND password = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("role");  // Return the role if credentials are correct
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if authentication fails
    }

    public static int getUserId(String username, String password) {
        String query = "SELECT user_id FROM Users WHERE username = ? AND password = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");  // Return the role if credentials are correct
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;  // Return null if authentication fails
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

    public static boolean deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            int affectedRows = ps.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteCourse(int courseId) {
        String sql = "DELETE FROM Courses WHERE course_id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, courseId);
            int affectedRows = ps.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> getCoursesForInstructor(int instructorId) {
        List<String> courses = new ArrayList<String>();
        String sql = "SELECT course_id, course_name FROM Courses WHERE instructor_id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, instructorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                String courseDetails = "Course ID: " + courseId + ", Course Name: " + courseName;
                courses.add(courseDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public static List<String> getCoursesForStudent(int studentId) {
        List<String> courses = new ArrayList<>();
        String sql = "SELECT c.course_id, c.course_name FROM Courses c JOIN Enrollments e ON c.course_id = e.course_id WHERE e.student_id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int courseId = rs.getInt("course_id");
                String courseName = rs.getString("course_name");
                courses.add("Course ID: " + courseId + ", Course Name: " + courseName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public static List<String> getGradesForStudent(int studentId) {
        List<String> grades = new ArrayList<>();
        String sql = "SELECT c.course_name, g.grade FROM Grades g JOIN Courses c ON g.course_id = c.course_id WHERE g.student_id = ?";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String courseName = rs.getString("course_name");
                String grade = rs.getString("grade");
                grades.add("Course: " + courseName + ", Grade: " + grade);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grades;
    }
}
