package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection con = null;

    private static void connect() {
        String dbDriver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/mydatabase";
        String username = "postgres";
        String password = "mysecretpassword";

        try {
            Class.forName(dbDriver);
            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        if (con == null)
            connect();
        return con;
    }
}
