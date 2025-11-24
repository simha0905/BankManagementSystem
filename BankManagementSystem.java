import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankManagementSystem {
    private List<BankAccount> accounts;
    private Map<String, String> accountPasswords; // Account Number -> Password mapping
    private String dataFilePath;

    public BankManagementSystem(String dataFilePath) {
        this.accounts = new ArrayList<>();
        this.accountPasswords = new HashMap<>();
        this.dataFilePath = dataFilePath;
        loadAccounts();
    }

    public void createAccount(String accountNumber, String accountHolderName, String password) {
        BankAccount account = new BankAccount(accountNumber, accountHolderName, password);
        accounts.add(account);
        accountPasswords.put(accountNumber, password);
    }

    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public boolean login(String accountNumber, String password) {
        String storedPassword = accountPasswords.get(accountNumber);
        return storedPassword != null && storedPassword.equals(password);
    }

    public void deposit(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    public void changePassword(String accountNumber, String newPassword) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            account.setPassword(newPassword);
        } else {
            System.out.println("Account not found!");
        }
    }

    public double getBalance(String accountNumber) {
        BankAccount account = findAccount(accountNumber);
        return (account != null) ? account.getBalance() : 0.0;
    }

    public void printAccountStatement(String accountNumber) {
        BankAccount account = findAccount(accountNumber);
        if (account != null) {
            System.out.println("Account Statement for Account Number: " + accountNumber);
            System.out.println(account.getStatement());
        } else {
            System.out.println("Account not found!");
        }
    }

    public void saveData() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
            outputStream.writeObject(accounts);
            outputStream.writeObject(accountPasswords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadAccounts() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(dataFilePath))) {
            Object data = inputStream.readObject();
            if (data instanceof List) {
                accounts = (List<BankAccount>) data;
            }

            data = inputStream.readObject();
            if (data instanceof Map) {
                accountPasswords = (Map<String, String>) data;
            }
        } catch (IOException | ClassNotFoundException e) {
            // If the file does not exist or data cannot be loaded, ignore and continue with an empty account list.
            accounts = new ArrayList<>();
            accountPasswords = new HashMap<>();
        }
    }
}