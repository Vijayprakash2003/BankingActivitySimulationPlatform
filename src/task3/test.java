package task3;

import Account.Account;
import dao.AccountDao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {

        AccountDao dao = new AccountDao();
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Account Creation ---");
        System.out.print("Enter number of accounts to create: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 0; i < n; i++) {
            System.out.println("\nCreating Account " + (i + 1) + ":");
            System.out.print("Enter Account Number: ");
            String accNo = sc.nextLine();

            System.out.print("Enter Holder Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Initial Balance: ");
            BigDecimal balance = sc.nextBigDecimal();
            sc.nextLine(); // consume newline

            Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

            Account account = new Account(accNo, name, balance, createdAt);
            dao.createAccount(account);
        }

        System.out.println("\nAll accounts inserted successfully!\n");

        // List all accounts from database
        List<Account> allAccounts = dao.listAllAccounts();
        System.out.println("All Accounts in Database:");
        for (Account acc : allAccounts) {
            System.out.println(
                    acc.getAccountNumber() + " | " +
                    acc.getHolderName() + " | " +
                    acc.getBalance() + " | " +
                    acc.getCreatedAt()
            );
        }

        sc.close();
    }
}
