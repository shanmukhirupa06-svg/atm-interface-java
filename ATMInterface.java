import java.util.Scanner;
import java.util.ArrayList;

public class ATMInterface {

    static Scanner sc = new Scanner(System.in);

    static double balance = 5000.00;

    static int correctPin = 1234;
    static String userId = "user123";

    static ArrayList<String> transactions = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("       WELCOME TO ATM");
        System.out.println("================================");

        // Login section
        boolean loginSuccess = false;

        for (int i = 1; i <= 3; i++) {

            System.out.print("Enter User ID: ");
            String id = sc.nextLine();

            System.out.print("Enter PIN: ");
            int pin = readNumber();

            if (id.equals(userId) && pin == correctPin) {
                loginSuccess = true;
                System.out.println("\nLogin Successful!");
                break;
            } else {
                System.out.println("Invalid User ID or PIN.");

                if (i < 3) {
                    System.out.println("Attempts remaining: " + (3 - i));
                }
            }
        }

        if (!loginSuccess) {
            System.out.println("\nAccount locked. Please try again later.");
            sc.close();
            return;
        }

        // ATM Menu
        int choice;

        do {
            System.out.println("\n========== ATM MENU ==========");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Transfer Money");
            System.out.println("5. Transaction History");
            System.out.println("6. Change PIN");
            System.out.println("7. Exit");
            System.out.println("==============================");

            System.out.print("Enter your choice: ");
            choice = readNumber();

            switch (choice) {

                case 1:
                    checkBalance();
                    break;

                case 2:
                    depositMoney();
                    break;

                case 3:
                    withdrawMoney();
                    break;

                case 4:
                    transferMoney();
                    break;

                case 5:
                    showTransactions();
                    break;

                case 6:
                    changePin();
                    break;

                case 7:
                    System.out.println("\nThank you for using ATM!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 7);

        sc.close();
    }

    // Read integer input safely
    static int readNumber() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    // Check Balance
    static void checkBalance() {
        System.out.println("\nYour Current Balance: Rs. " + balance);
    }

    // Deposit Money
    static void depositMoney() {

        System.out.print("\nEnter deposit amount: ");
        double amount = readAmount();

        if (amount > 0) {
            balance = balance + amount;

            transactions.add("Deposited: Rs. " + amount);

            System.out.println("Money deposited successfully!");
            System.out.println("Updated Balance: Rs. " + balance);
        } else {
            System.out.println("Please enter a positive amount.");
        }
    }

    // Withdraw Money
    static void withdrawMoney() {

        System.out.print("\nEnter withdrawal amount: ");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Please enter a positive amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient Balance!");
        } else {
            balance = balance - amount;

            transactions.add("Withdrawn: Rs. " + amount);

            System.out.println("Please collect your cash.");
            System.out.println("Updated Balance: Rs. " + balance);
        }
    }

    // Transfer Money
    static void transferMoney() {

        System.out.print("\nEnter receiver account number: ");
        String accountNumber = sc.nextLine();

        if (accountNumber.trim().isEmpty()) {
            System.out.println("Invalid account number.");
            return;
        }

        System.out.print("Enter transfer amount: ");
        double amount = readAmount();

        if (amount <= 0) {
            System.out.println("Please enter a positive amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient Balance!");
        } else {
            balance = balance - amount;

            transactions.add("Transferred Rs. " + amount
                    + " to Account: " + accountNumber);

            System.out.println("Money transferred successfully!");
            System.out.println("Updated Balance: Rs. " + balance);
        }
    }

    // Transaction History
    static void showTransactions() {

        System.out.println("\n======= TRANSACTION HISTORY =======");

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
        } else {
            for (int i = 0; i < transactions.size(); i++) {
                System.out.println((i + 1) + ". " + transactions.get(i));
            }
        }
    }

    // Change PIN
    static void changePin() {

        System.out.print("\nEnter current PIN: ");
        int oldPin = readNumber();

        if (oldPin == correctPin) {

            System.out.print("Enter new 4-digit PIN: ");
            int newPin = readNumber();

            if (newPin >= 1000 && newPin <= 9999
                    && newPin != correctPin) {

                correctPin = newPin;
                System.out.println("PIN changed successfully!");

            } else {
                System.out.println("Enter a different 4-digit PIN.");
            }

        } else {
            System.out.println("Incorrect current PIN.");
        }
    }

    // Read decimal amount safely
    static double readAmount() {
        while (true) {
            try {
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid amount: ");
            }
        }
    }
}
