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
import java.util.UUID;

public class AccountManager {
    private AccountDao dao = new AccountDao();
    private static final String TRANSACTION_FILE = "transactions.csv";

    // ✅ Create a new account
    public void createAccount(String holderName, BigDecimal initialBalance) {
        // Validate name
        if (!holderName.matches("[a-zA-Z ]+")) {
            System.out.println("❌ Invalid name! Only alphabets and spaces are allowed.");
            return;
        }

        // Validate initial balance
        if (initialBalance.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("❌ Initial balance must be greater than 0!");
            return;
        }

        // Auto-generate numeric account number
        String accountNumber = String.valueOf(System.currentTimeMillis()).substring(6);

        // Create account object
        Account account = new Account(accountNumber, holderName, initialBalance,
                new Timestamp(System.currentTimeMillis()));

        // Insert into database
        dao.createAccount(account);

        // Log transaction
        logTransaction("CREATE", accountNumber, "", initialBalance);

        System.out.println("✅ Account created successfully!");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name: " + holderName);
        System.out.println("Initial Balance: " + initialBalance);
        System.out.println("💡 Use this Account Number (" + accountNumber + ") for future deposits, withdrawals, or transfers.");
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

        System.out.println("💰 Deposited " + amount + " to " + accountNumber + ". New balance: " + newBalance);
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
            System.out.println("⚠️ Insufficient balance!");
            return;
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);
        dao.updateAccount(account);
        logTransaction("WITHDRAW", accountNumber, "", amount);

        System.out.println("💸 Withdrew " + amount + " from " + accountNumber + ". New balance: " + newBalance);
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
            System.out.println("⚠️ Insufficient balance!");
            return;
        }

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));

        dao.updateAccount(sender);
        dao.updateAccount(receiver);

        logTransaction("TRANSFER", fromAccount, toAccount, amount);
        System.out.println("✅ Transferred " + amount + " from " + fromAccount + " to " + toAccount);
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
            System.out.println("⚠️ Error writing to CSV: " + e.getMessage());
        }

        // Log to MySQL
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/banking_simulator", "root", "Vijay@2011");
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO transactions (transaction_id, type, from_account, to_account, amount, txn_time) VALUES (?, ?, ?, ?, ?, NOW())")) {

            ps.setString(1, transactionId);
            ps.setString(2, type);
            ps.setString(3, fromAccount);
            ps.setString(4, toAccount);
            ps.setBigDecimal(5, amount);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("⚠️ Database error while logging transaction: " + e.getMessage());
        }

        System.out.println("🧾 Transaction ID: " + transactionId);
    }

    // ✅ List all accounts
    public void listAccounts() {
        List<Account> accounts = dao.listAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("📭 No accounts found in the database.");
        } else {
            accounts.forEach(System.out::println);
        }
    }
}
