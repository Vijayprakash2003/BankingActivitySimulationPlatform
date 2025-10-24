package task3;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountManager manager = new AccountManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. List Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter account number: ");
                        String accNum = sc.next();
                        System.out.print("Enter initial balance: ");
                        BigDecimal balance = sc.nextBigDecimal();
                        manager.createAccount(accNum, balance);
                        break;

                    case 2:
                        System.out.print("Enter account number: ");
                        String depAcc = sc.next();
                        System.out.print("Enter amount to deposit: ");
                        BigDecimal depAmt = sc.nextBigDecimal();
                        manager.deposit(depAcc, depAmt);
                        break;

                    case 3:
                        System.out.print("Enter account number: ");
                        String wAcc = sc.next();
                        System.out.print("Enter amount to withdraw: ");
                        BigDecimal wAmt = sc.nextBigDecimal();
                        manager.withdraw(wAcc, wAmt);
                        break;

                    case 4:
                        System.out.print("Enter FROM account number: ");
                        String fromAcc = sc.next();
                        System.out.print("Enter TO account number: ");
                        String toAcc = sc.next();
                        System.out.print("Enter amount to transfer: ");
                        BigDecimal tAmt = sc.nextBigDecimal();
                        manager.transfer(fromAcc, toAcc, tAmt);
                        break;

                    case 5:
                        manager.listAccounts();
                        break;

                    case 6:
                        System.out.println("Exiting... Check transactions.csv for transaction log.");
                        sc.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
