package sql_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            String url = "jdbc:sqlite:/C:/Users/dmste/Desktop/db2.db/";
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url);
                System.out.println("Connection to SQLite has been established.");
            }
        } catch (SQLException e) {
            System.err.println("SQLite Connection Error: " + e.getMessage());
        }
        return connection;
    }
}
