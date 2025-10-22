package Account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {

    String accountNumber;
    String holderName;
    String email;
    BigDecimal balance;

    public Account(String accountNumber, String holderName, BigDecimal balance, String email) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.email = email;
        this.balance = (balance != null) ? balance : BigDecimal.ZERO;
    }

    public void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Deposit amount must be positive!");
            return;
        }
        balance = balance.add(amount);
        System.out.println("Deposited " + amount + " | New Balance: " + balance);
    }

    public void withdraw(BigDecimal amount) throws Exception {
        if (amount == null) {
            System.out.println("Withdrawal amount is null!");
            return;
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Withdrawal amount must be positive!");
            return;
        }
        if (amount.compareTo(balance) > 0) {
            System.out.println("Insufficient balance!");
            return;
        }
        balance = balance.subtract(amount);
        System.out.println("Withdrawn " + amount + " | Remaining Balance: " + balance);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        try {
            Account a = new Account("A1001", "Alice", new BigDecimal("5000.00"), "test@test.com");
            a.deposit(new BigDecimal("1000.00"));
            a.withdraw(new BigDecimal("2000.00"));

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            System.out.println("\nAccount Number: " + a.accountNumber);
            System.out.println("Holder Name: " + a.holderName);
            System.out.println("Email: " + a.email);
            System.out.println("Balance: " + a.getBalance());
            System.out.println("Date: " + LocalDateTime.now().format(fmt));
        }  catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}