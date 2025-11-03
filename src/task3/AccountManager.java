package task3;

import Account.Account;
import dao.AccountDao;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AccountManager {
    private AccountDao dao = new AccountDao();

    // ✅ Always save the CSV file in the main project folder (not src)
    private static final String TRANSACTION_FILE =
            "C:\\Users\\ramac\\git-practice\\BankingActivitySimulationPlatform\\transactions.csv";

    // ✅ Load MySQL Driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver loaded in AccountManager.");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ MySQL JDBC Driver not found! Please check the JAR path.");
        }
    }

    private final String url = "jdbc:mysql://localhost:3306/banking_simulator";
    private final String user = "root";
    private final String pass = "Vijay@2011";

    // ✅ Create a new account (now includes password)
    public void createAccount(String holderName, BigDecimal initialBalance) {
        if (!holderName.matches("[a-zA-Z ]+")) {
            System.out.println("❌ Invalid name! Only alphabets and spaces are allowed.");
            return;
        }

        if (initialBalance.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("❌ Initial balance must be greater than 0!");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Set a password for this account: ");
        String password = sc.nextLine().trim();

        if (password.isEmpty()) {
            System.out.println("⚠ Password cannot be empty! Setting default password 'user123'.");
            password = "user123";
        }

        String accountNumber = String.valueOf(System.currentTimeMillis()).substring(6);
        Account account = new Account(accountNumber, holderName, initialBalance,
                new Timestamp(System.currentTimeMillis()));

        // Insert account with password
        String sql = "INSERT INTO accounts (account_number, holder_name, balance, created_at, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, account.getAccountNumber());
            stmt.setString(2, account.getHolderName());
            stmt.setBigDecimal(3, account.getBalance());
            stmt.setTimestamp(4, account.getCreatedAt());
            stmt.setString(5, password);
            int rows = stmt.executeUpdate();

            System.out.println("Inserted " + rows + " row(s) into database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        logTransaction("CREATE", accountNumber, "", initialBalance);

        System.out.println("✅ Account created successfully!");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Initial Balance: ₹" + initialBalance);
        System.out.println("Password: " + password);
        System.out.println("💡 Use this Account Number (" + accountNumber + ") and password for future logins.");
    }

    // ✅ Deposit money
    public void deposit(String accountNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("❌ Deposit amount must be positive!");
            return;
        }

        Account account = dao.findByAccountNumber(accountNumber);
        if (account == null) {
            System.out.println("❌ Account " + accountNumber + " does not exist!");
            return;
        }

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        dao.updateAccount(account);
        logTransaction("DEPOSIT", accountNumber, "", amount);

        System.out.println("💰 Deposited ₹" + amount + " to " + accountNumber + ". New balance: ₹" + newBalance);
    }

    // ✅ Withdraw money
    public void withdraw(String accountNumber, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("❌ Withdrawal amount must be positive!");
            return;
        }

        Account account = dao.findByAccountNumber(accountNumber);
        if (account == null) {
            System.out.println("❌ Account " + accountNumber + " does not exist!");
            return;
        }

        if (account.getBalance().compareTo(amount) < 0) {
            System.out.println("⚠ Insufficient balance!");
            return;
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);
        dao.updateAccount(account);
        logTransaction("WITHDRAW", accountNumber, "", amount);

        System.out.println("💸 Withdrew ₹" + amount + " from " + accountNumber + ". New balance: ₹" + newBalance);
    }

    // ✅ Transfer money between accounts
    public void transfer(String fromAccount, String toAccount, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("❌ Transfer amount must be positive!");
            return;
        }

        Account sender = dao.findByAccountNumber(fromAccount);
        Account receiver = dao.findByAccountNumber(toAccount);

        if (sender == null || receiver == null) {
            System.out.println("❌ One of the accounts does not exist!");
            return;
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            System.out.println("⚠ Insufficient balance!");
            return;
        }

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));

        dao.updateAccount(sender);
        dao.updateAccount(receiver);

        logTransaction("TRANSFER", fromAccount, toAccount, amount);
        System.out.println("✅ Transferred ₹" + amount + " from " + fromAccount + " to " + toAccount);
    }

    // ✅ Log all transactions to MySQL + CSV
    private void logTransaction(String type, String fromAccount, String toAccount, BigDecimal amount) {
        String transactionId = "TXN" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        LocalDateTime now = LocalDateTime.now();

        // Log to CSV
        try (FileWriter fw = new FileWriter(TRANSACTION_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(transactionId + "," + type + "," + fromAccount + "," + toAccount + "," + amount + "," + now);
        } catch (IOException e) {
            System.out.println("⚠ Error writing to CSV: " + e.getMessage());
        }

        // Log to MySQL
        String sql = "INSERT INTO transactions (transaction_id, type, from_account, to_account, amount, txn_time) VALUES (?, ?, ?, ?, ?, NOW())";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, transactionId);
            ps.setString(2, type);
            ps.setString(3, fromAccount);
            ps.setString(4, toAccount);
            ps.setBigDecimal(5, amount);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("⚠ Database error while logging transaction: " + e.getMessage());
        }

        System.out.println("🧾 Transaction ID: " + transactionId);
    }

    // ✅ List all accounts
    public void listAccounts() {
        List<Account> accounts = dao.listAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("📭 No accounts found in the database.");
        } else {
            accounts.forEach(account ->
                    System.out.println("Account Number: " + account.getAccountNumber() +
                            " | Name: " + account.getHolderName() +
                            " | Balance: ₹" + account.getBalance())
            );
        }
    }

    // ✅ Show user details (for user login)
    public void showUserDetails(String accNo) {
        String sql = "SELECT account_number, holder_name, balance, created_at FROM accounts WHERE account_number = ?";
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, accNo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n===== ACCOUNT DETAILS =====");
                System.out.println("Account Number: " + rs.getString("account_number"));
                System.out.println("Holder Name: " + rs.getString("holder_name"));
                System.out.println("Balance: ₹" + rs.getBigDecimal("balance"));
                System.out.println("Created At: " + rs.getTimestamp("created_at"));
            } else {
                System.out.println("❌ Account not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
