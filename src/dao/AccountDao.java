package dao;

import Account.Account;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

public class AccountDao {

    private String url = "jdbc:mysql://localhost:3306/banking_simulator";
    private String user = "root";
    private String pass = "Vijay@2011";

    // Create a new account
    public void createAccount(Account account) {
        String sql = "INSERT INTO accounts(account_number, holder_name, balance, created_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, account.getAccountNumber());
            stmt.setString(2, account.getHolderName());
            stmt.setBigDecimal(3, account.getBalance());
            stmt.setTimestamp(4, account.getCreatedAt());

            int rows = stmt.executeUpdate();
            System.out.println("Inserted " + rows + " row(s) into database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update account balance
    public void updateAccount(Account account) {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, account.getBalance());
            stmt.setString(2, account.getAccountNumber());

            int rows = stmt.executeUpdate();
            System.out.println("Updated " + rows + " row(s) in database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Find account by account number
    public Account findByAccountNumber(String accountNumber) {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String holderName = rs.getString("holder_name");
                BigDecimal balance = rs.getBigDecimal("balance");
                Timestamp createdAt = rs.getTimestamp("created_at");

                return new Account(accountNumber, holderName, balance, createdAt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // List all accounts
    public List<Account> listAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String accountNumber = rs.getString("account_number");
                String holderName = rs.getString("holder_name");
                BigDecimal balance = rs.getBigDecimal("balance");
                Timestamp createdAt = rs.getTimestamp("created_at");

                accounts.add(new Account(accountNumber, holderName, balance, createdAt));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
