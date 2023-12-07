import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {
    private static int balance = 50000;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Welcome to ATM ... ");
                System.out.println("Select 1 for Withdraw");
                System.out.println("Select 2 for Deposit");
                System.out.println("Select 3 for Check Balance");
                System.out.println("Select 4 for EXIT");
                System.out.print("Select the appropriate options you want to perform:");

                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        System.out.print("Enter the amount to be withdrawn: ");
                        int withdrawAmount = sc.nextInt();
                        withdraw(withdrawAmount);
                        break;
                    case 2:
                        System.out.print("Enter the amount to be deposited: ");
                        int depositAmount = sc.nextInt();
                        deposit(depositAmount);
                        break;
                    case 3:
                        printBalance();
                        System.out.println();
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a valid option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); // consume the invalid input to avoid an infinite loop
            } finally {
                // Any cleanup code can be placed here
            }
        }
    }

    public static void printBalance() {
        System.out.println("The Current Balance: " + balance);
        System.out.println();
    }

    public static void withdraw(int withdrawAmount) {
        try {
            System.out.println("Withdrawal Operation:");
            System.out.println("The withdrawing Amount is: " + withdrawAmount);

            if (balance >= withdrawAmount) {
                balance -= withdrawAmount;
                System.out.println("Please collect your money and remove the card");
                printBalance();
            } else {
                throw new IllegalArgumentException("Insufficient funds. Your balance is: " + balance);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }

    public static void deposit(int depositAmount) {
        try {
            System.out.println("Deposit Operation:");
            System.out.println("The depositing amount is: " + depositAmount);

            if (depositAmount > 0) {
                balance += depositAmount;
                System.out.println("Your Money has been successfully deposited");
                printBalance();
            } else {
                throw new IllegalArgumentException("Invalid deposit amount. Please enter a positive value.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
    }
}
