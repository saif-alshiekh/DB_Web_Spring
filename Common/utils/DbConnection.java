package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.FileInputStream;

public class DbConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("Common/config/appConfig.properties"));

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
