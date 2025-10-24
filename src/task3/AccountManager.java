package task3;

import Account.Account;
import dao.AccountDao;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class AccountManager {
    private AccountDao dao = new AccountDao();
    private static final String TRANSACTION_FILE = "transactions.csv";

    // Create a new account
    public void createAccount(String accountNumber, String holderName, BigDecimal initialBalance) {
        if (dao.findByAccountNumber(accountNumber) != null) {
            System.out.println("Account " + accountNumber + " already exists!");
            return;
        }

        Account account = new Account(accountNumber, holderName, initialBalance, new Timestamp(System.currentTimeMillis()));
        dao.createAccount(account);
        logTransaction("CREATE", accountNumber, "", initialBalance);
        System.out.println("Account " + accountNumber + " created with balance " + initialBalance);
    }

    // Deposit money
    public void deposit(String accountNumber, BigDecimal amount) {
        Account account = dao.findByAccountNumber(accountNumber);
        if (account == null) {
            System.out.println("Account " + accountNumber + " does not exist!");
            return;
        }

        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        dao.updateAccount(account);
        logTransaction("DEPOSIT", accountNumber, "", amount);
        System.out.println("Deposited " + amount + " to " + accountNumber + ". New balance: " + newBalance);
    }

    // Withdraw money
    public void withdraw(String accountNumber, BigDecimal amount) {
        Account account = dao.findByAccountNumber(accountNumber);
        if (account == null) {
            System.out.println("Account " + accountNumber + " does not exist!");
            return;
        }

        if (account.getBalance().compareTo(amount) < 0) {
            System.out.println("Insufficient balance!");
            return;
        }

        BigDecimal newBalance = account.getBalance().subtract(amount);
        account.setBalance(newBalance);
        dao.updateAccount(account);
        logTransaction("WITHDRAW", accountNumber, "", amount);
        System.out.println("Withdrew " + amount + " from " + accountNumber + ". New balance: " + newBalance);
    }

    // Transfer money
    public void transfer(String fromAccount, String toAccount, BigDecimal amount) {
        Account sender = dao.findByAccountNumber(fromAccount);
        Account receiver = dao.findByAccountNumber(toAccount);

        if (sender == null || receiver == null) {
            System.out.println("One of the accounts does not exist!");
            return;
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            System.out.println("Insufficient balance!");
            return;
        }

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(amount));

        dao.updateAccount(sender);
        dao.updateAccount(receiver);

        logTransaction("TRANSFER", fromAccount, toAccount, amount);
        System.out.println("Transferred " + amount + " from " + fromAccount + " to " + toAccount);
    }

    // Log transactions to CSV
    private void logTransaction(String type, String fromAccount, String toAccount, BigDecimal amount) {
        try (FileWriter fw = new FileWriter(TRANSACTION_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {
            LocalDateTime now = LocalDateTime.now();
            pw.println(type + "," + fromAccount + "," + toAccount + "," + amount + "," + now);
        } catch (IOException e) {
            System.out.println("Error logging transaction: " + e.getMessage());
        }
    }

    // List all accounts from database
    public void listAccounts() {
        List<Account> accounts = dao.listAllAccounts();
        accounts.forEach(System.out::println);
    }
}
