import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int PIN = 2512;
        double balance = 50000.00;

        ArrayList<String> transactions = new ArrayList<>();

        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter your PIN: ");
            int enteredPin = sc.nextInt();

            if (enteredPin == PIN) {
                System.out.println("PIN correct! Welcome to ATM.");
                boolean exit = false;

                while (!exit) {
                    System.out.println("\nATM Menu:");
                    System.out.println("1. Check Balance");
                    System.out.println("2. Deposit Money");
                    System.out.println("3. Withdraw Money");
                    System.out.println("4. Mini Statement");
                    System.out.println("5. Exit");
                    System.out.print("Choose an option: ");
                    int choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println("Your current balance is: ₹" + balance);
                            break;

                        case 2:
                            System.out.print("Enter deposit amount: ₹");
                            double deposit = sc.nextDouble();
                            balance += deposit;
                            addTransaction(transactions, "Deposited: ₹" + deposit);
                            printReceipt("Deposit", deposit, balance);
                            break;

                        case 3:
                            System.out.print("Enter withdrawal amount (multiples of 50): ₹");
                            double withdraw = sc.nextDouble();

                            if (withdraw > balance) {
                                System.out.println("Insufficient funds!");
                            } else if (withdraw % 50 != 0) {
                                System.out.println("Please enter amount in multiples of ₹50.");
                            } else {
                                balance -= withdraw;
                                addTransaction(transactions, "Withdrew: ₹" + withdraw);
                                printReceipt("Withdrawal", withdraw, balance);

                                showDenomination(withdraw);

                                if (balance < 500) {
                                    System.out.println("⚠ Warning: Your balance is below ₹500!");
                                }
                            }
                            break;

                        case 4:
                            System.out.println("\n--- Mini Statement ---");
                            int start = Math.max(transactions.size() - 5, 0);
                            for (int i = start; i < transactions.size(); i++) {
                                System.out.println(transactions.get(i));
                            }
                            break;

                        case 5:
                            System.out.println("Thank you for using ATM. Goodbye!");
                            exit = true;
                            break;

                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }
                break; 
            } else {
                attempts++;
                System.out.println("Incorrect PIN. Attempts left: " + (3 - attempts));
            }
        }

        if (attempts == 3) {
            System.out.println("Too many incorrect attempts. Card blocked.");
        }
        sc.close();
    }

    private static void addTransaction(ArrayList<String> transactions, String transaction) {
        transactions.add(new Date() + " - " + transaction);
    }

    private static void printReceipt(String type, double amount, double balance) {
        System.out.println("\n--- Transaction Receipt ---");
        System.out.println("Date: " + new Date());
        System.out.println("Type: " + type);
        System.out.println("Amount: ₹" + amount);
        System.out.println("Remaining Balance: ₹" + balance);
        System.out.println("---------------------------\n");
    }

    private static void showDenomination(double amount) {
        int amt = (int) amount;
        int notes2000 = amt / 2000; amt %= 2000;
        int notes500 = amt / 500; amt %= 500;
        int notes100 = amt / 100; amt %= 100;
        int notes50 = amt / 50;

        System.out.println("💵 Denomination Breakdown:");
        if (notes2000 > 0) System.out.println("₹2000 x " + notes2000);
        if (notes500 > 0) System.out.println("₹500  x " + notes500);
        if (notes100 > 0) System.out.println("₹100  x " + notes100);
        if (notes50 > 0) System.out.println("₹50   x " + notes50);
        System.out.println("---------------------------");
    }
}
