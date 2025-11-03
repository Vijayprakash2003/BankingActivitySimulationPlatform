package task3;

import java.sql.*;
import java.util.Scanner;

public class LoginManager {

    private String url = "jdbc:mysql://localhost:3306/banking_simulator";
    private String user = "root";
    private String pass = "Vijay@2011";

    // Check admin login
    public boolean adminLogin(String username, String password) {
        String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // true if login valid

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Check user login (for account holders)
    public boolean userLogin(String accountNumber, String password) {
        String sql = "SELECT * FROM accounts WHERE account_number = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, accountNumber);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
