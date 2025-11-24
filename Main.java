import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String dataFilePath = "bank_accounts.txt";
        BankManagementSystem bankManagementSystem = new BankManagementSystem(dataFilePath);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Bank Management System!");

        boolean loggedIn = false;
        String loggedInAccount = "";

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (mainChoice) {
                case 1:
                    // Login
                    System.out.print("Enter Account Number: ");
                    String accountNumber = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();

                    if (bankManagementSystem.login(accountNumber, password)) {
                        System.out.println("Login successful!");
                        loggedIn = true;
                        loggedInAccount = accountNumber;
                    } else {
                        System.out.println("Invalid account number or password! Please try again.");
                    }
                    break;

                case 2:
                    // Create Account
                    if (loggedIn) {
                        System.out.println("Logout to create a new account.");
                    } else {
                        System.out.print("Enter Account Number: ");
                        accountNumber = scanner.nextLine();
                        System.out.print("Enter Account Holder Name: ");
                        String accountHolderName = scanner.nextLine();
                        System.out.print("Enter Password: ");
                        password = scanner.nextLine();
                        bankManagementSystem.createAccount(accountNumber, accountHolderName, password);
                        System.out.println("Account created successfully!");
                        bankManagementSystem.saveData(); // Save data after creating an account
                    }
                    break;

                case 3:
                    // Exit
                    if (loggedIn) {
                        loggedIn = false;
                        loggedInAccount = "";
                        System.out.println("Logged out successfully!");
                    }
                    bankManagementSystem.saveData(); // Save data before exiting
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }

            while (loggedIn) {
                System.out.println("\nLogged-in Menu:");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Change Password");
                System.out.println("4. Check Account Balance");
                System.out.println("5. Print Account Statement");
                System.out.println("6. Logout");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        // Deposit
                        System.out.print("Enter Deposit Amount: ");
                        double depositAmount = scanner.nextDouble();
                        bankManagementSystem.deposit(loggedInAccount, depositAmount);
                        System.out.println("Deposit successful!");
                        bankManagementSystem.saveData(); // Save data after depositing
                        break;

                    case 2:
                        // Withdraw
                        System.out.print("Enter Withdrawal Amount: ");
                        double withdrawalAmount = scanner.nextDouble();
                        bankManagementSystem.withdraw(loggedInAccount, withdrawalAmount);
                        System.out.println("Withdrawal successful!");
                        bankManagementSystem.saveData(); // Save data after withdrawing
                        break;

                    case 3:
                        // Change Password
                        System.out.print("Enter Old Password: ");
                        String oldPassword = scanner.nextLine();
                        if (bankManagementSystem.login(loggedInAccount, oldPassword)) {
                            System.out.print("Enter New Password: ");
                            String newPassword = scanner.nextLine();
                            bankManagementSystem.changePassword(loggedInAccount, newPassword);
                            System.out.println("Password changed successfully!");
                            bankManagementSystem.saveData(); // Save data after changing password
                        } else {
                            System.out.println("Invalid password! Please try again.");
                        }
                        break;

                    case 4:
                        // Check Account Balance
                        double balance = bankManagementSystem.getBalance(loggedInAccount);
                        System.out.println("Account Balance: " + balance);
                        break;

                    case 5:
                        // Print Account Statement
                        bankManagementSystem.printAccountStatement(loggedInAccount);
                        break;

                    case 6:
                        // Logout
                        loggedIn = false;
                        loggedInAccount = "";
                        System.out.println("Logged out successfully!");
                        bankManagementSystem.saveData(); // Save data after logging out
                        break;

                    default:
                        System.out.println("Invalid choice! Please try again.");
                        break;
                }
            }
        }
    }
}