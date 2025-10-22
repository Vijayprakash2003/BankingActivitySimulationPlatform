import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

public class AccountManager {
    private Map<String, BigDecimal> accounts = new HashMap<>();

    // Create a new account
    public void createAccount(String accountNumber, BigDecimal initialBalance) {
        accounts.put(accountNumber, initialBalance);
        System.out.println("Account " + accountNumber + " created with balance " + initialBalance);
    }

    // Get account balance
    public BigDecimal getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    // List all accounts
    public void listAccounts() {
        accounts.forEach((acc, balance) -> 
            System.out.println("Account: " + acc + ", Balance: " + balance)
        );
    }

    // Deposit money
    public void deposit(String accountNumber, BigDecimal amount) throws Exception {
        BigDecimal balance = accounts.get(accountNumber);
        if(balance == null) throw new Exception("Account not found");
        balance = balance.add(amount);
        accounts.put(accountNumber, balance);
        logTransaction("DEPOSIT", null, accountNumber, amount);
    }

    // Withdraw money
    public void withdraw(String accountNumber, BigDecimal amount) throws Exception {
        BigDecimal balance = accounts.get(accountNumber);
        if(balance == null) throw new Exception("Account not found");
        if(balance.compareTo(amount) < 0) throw new Exception("Insufficient balance");
        balance = balance.subtract(amount);
        accounts.put(accountNumber, balance);
        logTransaction("WITHDRAW", accountNumber, null, amount);
    }

    // Transfer money
    public void transfer(String fromAccount, String toAccount, BigDecimal amount) throws Exception {
        BigDecimal fromBalance = accounts.get(fromAccount);
        BigDecimal toBalance = accounts.get(toAccount);
        if(fromBalance == null || toBalance == null) throw new Exception("Account not found");
        if(fromBalance.compareTo(amount) < 0) throw new Exception("Insufficient balance");
        accounts.put(fromAccount, fromBalance.subtract(amount));
        accounts.put(toAccount, toBalance.add(amount));
        logTransaction("TRANSFER", fromAccount, toAccount, amount);
    }

    // Log transaction to CSV
    private void logTransaction(String txType, String fromAccount, String toAccount, BigDecimal amount) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("transactions.csv", true))) {
            pw.printf("%s,%s,%s,%s,%s,%s%n",
                    UUID.randomUUID(),
                    txType,
                    fromAccount == null ? "" : fromAccount,
                    toAccount == null ? "" : toAccount,
                    amount,
                    java.time.LocalDateTime.now());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
