import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws Exception {
        // Step 1: Create AccountManager object
        AccountManager manager = new AccountManager();

        // Step 2: Create accounts
        manager.createAccount("A001", BigDecimal.valueOf(1000));
        manager.createAccount("A002", BigDecimal.valueOf(2000));

        // Step 3: Deposit money
        manager.deposit("A001", BigDecimal.valueOf(500));

        // Step 4: Withdraw money
        manager.withdraw("A002", BigDecimal.valueOf(300));

        // Step 5: Transfer money
        manager.transfer("A001", "A002", BigDecimal.valueOf(200));

        // Step 6: List all accounts to see final balances
        manager.listAccounts();

        System.out.println("\nCheck transactions.csv for logged transactions.");
    }
}
