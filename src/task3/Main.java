package task3;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final AccountManager manager = new AccountManager();

    public static void main(String[] args) {
        System.out.println("===== Welcome to the Mini Banking System =====");
        System.out.println("✅ MySQL JDBC Driver loaded successfully!\n");

        while (true) {
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    System.out.println("👋 Thank you for using the Banking System!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        }
    }

    // ✅ ADMIN LOGIN
    private static void adminLogin() {
        System.out.print("Enter Admin Username: ");
        String adminUser = sc.nextLine();
        System.out.print("Enter Admin Password: ");
        String adminPass = sc.nextLine();

        if (adminUser.equals("admin") && adminPass.equals("admin123")) {
            System.out.println("✅ Admin login successful!");
            adminMenu();
        } else {
            System.out.println("❌ Invalid admin credentials!");
        }
    }

    // ✅ ADMIN MENU
    private static void adminMenu() {
        while (true) {
            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. List All Accounts");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter account holder name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter initial balance: ");
                    BigDecimal balance = sc.nextBigDecimal();
                    sc.nextLine();
                    manager.createAccount(name, balance);
                    break;

                case 2:
                    manager.listAccounts();
                    break;

                case 3:
                    System.out.println("👋 Admin logged out!");
                    return; // ✅ Return to main menu
                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        }
    }

    // ✅ USER LOGIN
    private static void userLogin() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        // Check if valid user
        dao.AccountDao dao = new dao.AccountDao();
        if (dao.validateUserLogin(accNo, password)) {
            System.out.println("✅ User login successful!");
            userMenu(accNo);
        } else {
            System.out.println("❌ Invalid account number or password!");
        }
    }

    // ✅ USER MENU
    private static void userMenu(String accNo) {
        while (true) {
            System.out.println("\n===== USER MENU =====");
            System.out.println("1. View Account Details");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    manager.showUserDetails(accNo);
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    BigDecimal deposit = sc.nextBigDecimal();
                    sc.nextLine();
                    manager.deposit(accNo, deposit);
                    break;
                case 3:
                    System.out.print("Enter withdraw amount: ");
                    BigDecimal withdraw = sc.nextBigDecimal();
                    sc.nextLine();
                    manager.withdraw(accNo, withdraw);
                    break;
                case 4:
                    System.out.print("Enter recipient account number: ");
                    String toAcc = sc.nextLine();
                    System.out.print("Enter transfer amount: ");
                    BigDecimal transfer = sc.nextBigDecimal();
                    sc.nextLine();
                    manager.transfer(accNo, toAcc, transfer);
                    break;
                case 5:
                    System.out.println("👋 User logged out!");
                    return; // ✅ Go back to main menu
                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        }
    }
}
