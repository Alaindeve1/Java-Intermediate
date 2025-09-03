package TASK3;
import java.util.ArrayList;

// BankAccount.java
class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    private String accountType;
    private ArrayList<String> transactionHistory;
    
    // Constructor
    public BankAccount(String accountNumber, String accountHolder, double initialBalance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.accountType = accountType;
        this.transactionHistory = new ArrayList<>();
        this.transactionHistory.add(String.format("Account opened with balance $%.2f", initialBalance));
    }
    
    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }
    public String getAccountType() { return accountType; }
    
    // Deposit method
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Error: Deposit amount must be positive.");
            return false;
        }
        
        if (amount > 100000) {
            System.out.println("❌ Error: Daily deposit limit is $100,000.");
            return false;
        }
        
        balance += amount;
        System.out.printf("✅ Successfully deposited $%.2f%n", amount);
        System.out.printf("💰 New balance: $%.2f%n", balance);
        transactionHistory.add(String.format("Deposited $%.2f | Balance: $%.2f", amount, balance));
        return true;
    }
    
    // Withdraw method
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Error: Withdrawal amount must be positive.");
            return false;
        }
        
        if (amount > balance) {
            System.out.printf("❌ Error: Insufficient funds. Your balance is $%.2f%n", balance);
            return false;
        }
        
        if (amount > 10000) {
            System.out.println("❌ Error: Daily withdrawal limit is $10,000.");
            return false;
        }
        
        balance -= amount;
        System.out.printf("✅ Successfully withdrew $%.2f%n", amount);
        System.out.printf("💰 Remaining balance: $%.2f%n", balance);
        transactionHistory.add(String.format("Withdrew $%.2f | Balance: $%.2f", amount, balance));
        return true;
    }
    
    // Check balance method
    public void checkBalance() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("           ACCOUNT BALANCE");
        System.out.println("=".repeat(40));
        System.out.printf("💰 Current Balance: $%.2f%n", balance);
        System.out.println("=".repeat(40));
    }
    
    // Display account information
    public void displayAccountInfo() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("              ACCOUNT INFORMATION");
        System.out.println("=".repeat(50));
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Account Type: " + accountType);
        System.out.printf("Current Balance: $%.2f%n", balance);
        System.out.println("=".repeat(50));
    }
    
    // Transfer money to another account
    public boolean transfer(BankAccount targetAccount, double amount) {
        if (amount <= 0) {
            System.out.println("❌ Error: Transfer amount must be positive.");
            return false;
        }
        
        if (amount > balance) {
            System.out.printf("❌ Error: Insufficient funds for transfer. Your balance is $%.2f%n", balance);
            return false;
        }
        
        if (amount > 50000) {
            System.out.println("❌ Error: Daily transfer limit is $50,000.");
            return false;
        }
        
        // Perform the transfer
        this.balance -= amount;
        targetAccount.balance += amount;
        this.transactionHistory.add(String.format("Transferred $%.2f to %s (%s) | Balance: $%.2f", amount, targetAccount.getAccountHolder(), targetAccount.getAccountNumber(), this.balance));
        targetAccount.transactionHistory.add(String.format("Received $%.2f from %s (%s) | Balance: $%.2f", amount, this.getAccountHolder(), this.getAccountNumber(), targetAccount.balance));
        
        System.out.printf("✅ Successfully transferred $%.2f to %s%n", amount, targetAccount.getAccountHolder());
        System.out.printf("💰 Your remaining balance: $%.2f%n", this.balance);
        
        return true;
    }

    public ArrayList<String> getTransactionHistory() {
        return transactionHistory;
    }
}

