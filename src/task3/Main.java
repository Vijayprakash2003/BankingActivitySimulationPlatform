package task3;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountManager manager = new AccountManager();
        Scanner sc = new Scanner(System.in);

        System.out.println("===== Welcome to the Mini Banking System =====");

        while (true) {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. List Accounts");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine(); // clear newline after nextInt()

                switch (choice) {
                    case 1 -> {
                        // ✅ Account number is no longer entered manually
                        System.out.print("Enter account holder name: ");
                        String holderName = sc.nextLine().trim();

                        // ✅ Validate that name only contains alphabets and spaces
                        if (!holderName.matches("[a-zA-Z ]+")) {
                            System.out.println("❌ Invalid name! Only alphabets and spaces are allowed.");
                            break;
                        }

                        System.out.print("Enter initial balance: ");
                        BigDecimal balance = sc.nextBigDecimal();

                        // ✅ Auto-generates account number internally
                        manager.createAccount(holderName, balance);
                    }

                    case 2 -> {
                        System.out.print("Enter account number: ");
                        String depAcc = sc.next();
                        System.out.print("Enter amount to deposit: ");
                        BigDecimal depAmt = sc.nextBigDecimal();
                        manager.deposit(depAcc, depAmt);
                    }

                    case 3 -> {
                        System.out.print("Enter account number: ");
                        String wAcc = sc.next();
                        System.out.print("Enter amount to withdraw: ");
                        BigDecimal wAmt = sc.nextBigDecimal();
                        manager.withdraw(wAcc, wAmt);
                    }

                    case 4 -> {
                        System.out.print("Enter FROM account number: ");
                        String fromAcc = sc.next();
                        System.out.print("Enter TO account number: ");
                        String toAcc = sc.next();
                        System.out.print("Enter amount to transfer: ");
                        BigDecimal tAmt = sc.nextBigDecimal();
                        manager.transfer(fromAcc, toAcc, tAmt);
                    }

                    case 5 -> manager.listAccounts();

                    case 6 -> {
                        System.out.println("🏦 Exiting... Check 'transactions.csv' and database for logs.");
                        sc.close();
                        return;
                    }

                    default -> System.out.println("⚠️ Invalid choice! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter valid numbers.");
                sc.nextLine(); // clear invalid input
            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
            }
        }
    }
}
