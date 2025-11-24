import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    private String accountNumber;
    private String accountHolderName;
    private String password;
    private double balance;
    private List<String> transactionHistory;

    public BankAccount(String accountNumber, String accountHolderName, String password) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.password = password;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposit: +" + amount);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add("Withdrawal: -" + amount);
        } 
        else {
            System.out.println("Insufficient funds!");
        }
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getPassword() {
        return password;
    }

    public String getStatement() {
        StringBuffer statement = new StringBuffer();
        statement.append("Account Holder: ").append(accountHolderName).append("\n");
        statement.append("Account Number: ").append(accountNumber).append("\n");
        statement.append("Balance: ").append(balance).append("\n");
        statement.append("Transaction History:\n");
        for (String transaction : transactionHistory) {
            statement.append(transaction).append("\n");
        }
        return statement.toString();
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Account Holder: " + accountHolderName + ", Balance: " + balance;
    }
}