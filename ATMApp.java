// ATM 
import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class Account {
    private String accountNumber;
    private String pin;
    private double balance;

    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        balance -= amount;
    }

    public void deposit(double amount) {
        balance += amount;
    }
}

class ATM {
    private Account[] accounts;

    public ATM() {
        // Initialize accounts (for demonstration purposes)
        accounts = new Account[2];
        accounts[0] = new Account("123456", "1234", 1000.0);
        accounts[1] = new Account("789012", "5678", 500.0);
    }

    public Account authenticateUser(String accountNumber, String pin) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber) && account.getPin().equals(pin)) {
                return account;
            }
        }
        return null;
    }

    public void displayMenu() {
        System.out.println("1. Balance Inquiry");
        System.out.println("2. Withdrawal");
        System.out.println("3. Deposit");
        System.out.println("4. Exit");
    }

    public void performOperation(Account account, int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                System.out.println("Balance: $" + account.getBalance());
                break;
            case 2:
                try {
                    System.out.print("Enter withdrawal amount: $");
                    double amount = scanner.nextDouble();
                    account.withdraw(amount);
                    System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
                } catch (InsufficientBalanceException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case 3:
                System.out.print("Enter deposit amount: $");
                double depositAmount = scanner.nextDouble();
                account.deposit(depositAmount);
                System.out.println("Deposit successful. New balance: $" + account.getBalance());
                break;
            case 4:
                System.out.println("Exiting. Thank you!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}

public class ATMApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        System.out.print("Enter account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter PIN: ");
        String pin = scanner.next();

        Account userAccount = atm.authenticateUser(accountNumber, pin);

        if (userAccount != null) {
            System.out.println("Authentication successful. Welcome!");
            while (true) {
                atm.displayMenu();
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                atm.performOperation(userAccount, choice, scanner);
            }
        } else {
            System.out.println("Authentication failed. Exiting.");
        }
        scanner.close();
    }
}