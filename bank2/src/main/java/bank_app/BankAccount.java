package bank_app;

public class BankAccount {

    private String accountNumber;
    private String accountHolder;
    private double balance;
    private String name;

    public BankAccount() {
        this.balance = 0.0;
    }

    public BankAccount(String name, double yourBalance) {
        this.balance = yourBalance;
        this.name = name;
    }

    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance = balance + amount;
            System.out.println("deposited: " + amount);
        } else {
            System.out.println("Impossible to deposit 0");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance = balance - amount;
            System.out.println("withdrawn: " + amount);
        } else {
            System.out.println("Impossible to withdraw");
        }
        return false;
    }

    public void printBalance() {
        System.out.println(name + " Your current balance is: " + balance);
    }

    public void transfer(BankAccount receiver, double amount) {
        if (amount > 0 && amount <= balance) {
            withdraw(amount);
            receiver.deposit(amount);
            System.out.println(amount + " euros has been sent to " + receiver.getName());
        } else {
            System.out.println("Impossible to send money");
        }
    }
    @Override
    public String toString() {
        return "Account Number: " + accountNumber + ", Account Holder: " + accountHolder + ", Balance: " + balance;
    }

}
