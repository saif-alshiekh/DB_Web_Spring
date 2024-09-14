import java.util.*;
import utils.DbConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) {
        Connection conn = DbConnection.getConnection();
        if (conn != null) {
            System.out.println("Connection successful!");
            listTables(conn);
        } else {
            System.out.println("Failed to connect.");
        }
    }

    private static void listTables(Connection conn) {
        String query = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'student_grading_atypon';";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("Tables in the database:");
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
