package common.db;

import java.sql.*;
import utils.DbConnection;

public class DatabaseManager {
    public static boolean authenticateUser(String username, String password) {
        String query = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?";  // Assuming you have a users table with username and password fields
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;  // User is authenticated
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // User is not authenticated
    }

    public static boolean addUser(String username, String password, String role, int personId) {
        String sql = "INSERT INTO users (username, password, role, person_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, role);
            ps.setInt(4, personId);
            int affectedRows = ps.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
