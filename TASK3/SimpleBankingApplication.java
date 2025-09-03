package TASK3;
// SimpleBankingApplication.java
import java.util.ArrayList;
import java.util.Scanner;

public class SimpleBankingApplication {
    private ArrayList<BankAccount> accounts;
    private Scanner scanner;
    private BankAccount currentAccount;
    
    public SimpleBankingApplication() {
        accounts = new ArrayList<>();
        scanner = new Scanner(System.in);
        currentAccount = null;
        
        // Create some sample accounts for demonstration
        createSampleAccounts();
    }
    
    // Create sample accounts
    private void createSampleAccounts() {
        accounts.add(new BankAccount("ACC001", "John Doe", 1000.0, "Savings"));
        accounts.add(new BankAccount("ACC002", "Jane Smith", 2500.0, "Checking"));
        accounts.add(new BankAccount("ACC003", "Bob Johnson", 500.0, "Savings"));
    }
    
    // Main application loop
    public void run() {
        System.out.println("🏦 Welcome to Simple Banking System!");
        System.out.println("Please log in to your account to continue.");
        
        // Login process
        if (!login()) {
            System.out.println("❌ Login failed. Exiting application.");
            return;
        }
        
        // Main banking operations
        while (true) {
            displayMainMenu();
            
            int choice = getIntInput("Choose an option (1-7): ");
            
            switch (choice) {
                case 1:
                    currentAccount.checkBalance();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    currentAccount.displayAccountInfo();
                    break;
                case 6:
                    displayTransactionHistory();
                    break;
                case 7:
                    System.out.println("👋 Thank you for using Simple Banking System!");
                    System.out.println("Have a great day!");
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
            
            // Pause before showing menu again
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    // Login method
    private boolean login() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("              LOGIN");
        System.out.println("=".repeat(40));
        
        // Show available accounts for demo purposes
        System.out.println("📋 Available Demo Accounts:");
        for (BankAccount account : accounts) {
            System.out.println("  • " + account.getAccountNumber() + " - " + account.getAccountHolder());
        }
        System.out.println();
        
        for (int attempts = 3; attempts > 0; attempts--) {
            System.out.print("Enter your account number: ");
            String accountNumber = scanner.nextLine().trim();
            
            // Find the account
            for (BankAccount account : accounts) {
                if (account.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                    currentAccount = account;
                    System.out.println("✅ Login successful!");
                    System.out.println("Welcome, " + account.getAccountHolder() + "!");
                    return true;
                }
            }
            
            System.out.println("❌ Account not found. " + (attempts - 1) + " attempts remaining.");
        }
        
        return false;
    }
    
    // Display main menu
    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("             BANKING OPERATIONS");
        System.out.println("   Account: " + currentAccount.getAccountNumber() + 
                          " | " + currentAccount.getAccountHolder());
        System.out.println("=".repeat(50));
        System.out.println("1. 💰 Check Balance");
        System.out.println("2. 💵 Deposit Money");
        System.out.println("3. 💸 Withdraw Money");
        System.out.println("4. 🔄 Transfer Money");
        System.out.println("5. ℹ️  Account Information");
        System.out.println("6. 📋 Transaction History");
        System.out.println("7. 🚪 Exit");
        System.out.println("=".repeat(50));
    }
    
    // Perform deposit operation
    private void performDeposit() {
        System.out.println("\n=== DEPOSIT MONEY ===");
        System.out.printf("Current Balance: $%.2f%n", currentAccount.getBalance());
        
        double amount = getDoubleInput("Enter amount to deposit: $");
        currentAccount.deposit(amount);
    }
    
    // Perform withdrawal operation
    private void performWithdrawal() {
        System.out.println("\n=== WITHDRAW MONEY ===");
        System.out.printf("Current Balance: $%.2f%n", currentAccount.getBalance());
        
        double amount = getDoubleInput("Enter amount to withdraw: $");
        currentAccount.withdraw(amount);
    }
    
    // Perform transfer operation
    private void performTransfer() {
        System.out.println("\n=== TRANSFER MONEY ===");
        System.out.printf("Current Balance: $%.2f%n", currentAccount.getBalance());
        
        // Show available accounts for transfer
        System.out.println("\nAvailable accounts for transfer:");
        ArrayList<BankAccount> otherAccounts = new ArrayList<>();
        for (BankAccount account : accounts) {
            if (!account.getAccountNumber().equals(currentAccount.getAccountNumber())) {
                otherAccounts.add(account);
                System.out.println((otherAccounts.size()) + ". " + account.getAccountNumber() + 
                                 " - " + account.getAccountHolder());
            }
        }
        
        if (otherAccounts.isEmpty()) {
            System.out.println("❌ No other accounts available for transfer.");
            return;
        }

        int index = getIntInput("Select target account by number: ") - 1;
        if (index < 0 || index >= otherAccounts.size()) {
            System.out.println("❌ Invalid selection.");
            return;
        }

        BankAccount target = otherAccounts.get(index);
        double amount = getDoubleInput("Enter amount to transfer: $");
        currentAccount.transfer(target, amount);
    }

    // Display transaction history for current account
    private void displayTransactionHistory() {
        System.out.println("\n=== TRANSACTION HISTORY ===");
        ArrayList<String> history = currentAccount.getTransactionHistory();
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ". " + history.get(i));
        }
    }

    // Helper to read an integer with prompt and basic validation
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number. Please try again.");
            }
        }
    }

    // Helper to read a double with prompt and basic validation
    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid amount. Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        new SimpleBankingApplication().run();
    }
}