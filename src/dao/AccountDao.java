package dao;

import Account.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {

    private final String url = "jdbc:mysql://localhost:3306/banking_simulator";
    private final String user = "root";
    private final String pass = "Vijay@2011";

    // ✅ Create a new account (INSERT)
    public int createAccount(Account account) {
        String sql = "INSERT INTO accounts (account_number, holder_name, balance, created_at, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, account.getAccountNumber());
            stmt.setString(2, account.getHolderName());
            stmt.setBigDecimal(3, account.getBalance());
            stmt.setTimestamp(4, account.getCreatedAt());
            stmt.setString(5, account.getPassword()); // ✅ Store password

            return stmt.executeUpdate(); // returns number of rows inserted
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // ✅ Find account by account number
    public Account findByAccountNumber(String accountNumber) {
        String sql = "SELECT account_number, holder_name, balance, created_at FROM accounts WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String accNo = rs.getString("account_number");
                String holderName = rs.getString("holder_name");
                java.math.BigDecimal balance = rs.getBigDecimal("balance");
                Timestamp createdAt = rs.getTimestamp("created_at");

                return new Account(accNo, holderName, balance, createdAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Update account balance in the database
    public void updateAccount(Account account) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, account.getBalance());
            stmt.setString(2, account.getAccountNumber());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ List all accounts
    public List<Account> listAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_number, holder_name, balance, created_at FROM accounts";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String accNo = rs.getString("account_number");
                String holderName = rs.getString("holder_name");
                java.math.BigDecimal balance = rs.getBigDecimal("balance");
                Timestamp createdAt = rs.getTimestamp("created_at");

                Account account = new Account(accNo, holderName, balance, createdAt);
                accounts.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    // ✅ Validate user login (account_number + password)
    public boolean validateUserLogin(String accountNumber, String password) {
        String sql = "SELECT * FROM accounts WHERE account_number = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, accountNumber);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // ✅ true if login valid

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // ❌ invalid login
    }
}
