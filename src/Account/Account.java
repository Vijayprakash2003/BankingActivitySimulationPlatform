package Account;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Account {

    private String accountNumber;
    private String holderName;
    private String email;
    private BigDecimal balance;
    private Timestamp createdAt;
    private String password;  // ✅ New field added

    // ✅ New constructor with password, email, and createdAt
    public Account(String accountNumber, String holderName, BigDecimal balance, String email, String password, Timestamp createdAt) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = (balance != null) ? balance : BigDecimal.ZERO;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    // ✅ Existing constructor with password but without email
    public Account(String accountNumber, String holderName, BigDecimal balance, String password, Timestamp createdAt) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = (balance != null) ? balance : BigDecimal.ZERO;
        this.password = password;
        this.createdAt = createdAt;
    }

    // Existing constructor (for compatibility)
    public Account(String accountNumber, String holderName, BigDecimal balance, Timestamp createdAt) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = (balance != null) ? balance : BigDecimal.ZERO;
        this.createdAt = createdAt;
    }

    // Deposit
    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) return;
        balance = balance.add(amount);
    }

    // Withdraw
    public void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) return;
        if (amount.compareTo(balance) > 0) return;
        balance = balance.subtract(amount);
    }

    // Getters & Setters
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getHolderName() { return holderName; }
    public void setHolderName(String holderName) { this.holderName = holderName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public String getPassword() { return password; }     // ✅ getter
    public void setPassword(String password) { this.password = password; }  // ✅ setter

    @Override
    public String toString() {
        return String.format(
            "Account Number: %s | Holder Name: %s | Email: %s | Balance: ₹%.2f | Created At: %s",
            accountNumber, holderName, email, balance, createdAt
        );
    }
}
