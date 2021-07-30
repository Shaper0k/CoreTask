package jm.task.core.jdbc.util;



import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "garena0408";

    public static Connection connectionDB() {
        Connection connection = null;
        if (connection == null) {
            try {
                 connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }
}


