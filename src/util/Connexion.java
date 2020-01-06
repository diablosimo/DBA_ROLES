package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static String urlstring = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static String driverName = "oracle.jdbc.driver.OracleDriver";
    private static String username = "SYS as SYSDBA";
    private static String password = "";
    private static Connection con;

    public static Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(urlstring, username, password);
                System.out.println("connetion ok");
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
            ex.printStackTrace();
        }
        return con;
    }
}
