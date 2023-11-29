package sql_db;

import bank_app.BankAccount;

import java.util.List;
import java.util.Scanner;

import static bank_app.BankApp.deleteAccount;

public class InteractiveBankApp {     public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    AccountDAO accountDAO = new AccountDAO();

    List<BankAccount> allAccounts = accountDAO.getAllAccounts();

    System.out.println("Balances of All Accounts:");
    for (BankAccount account : allAccounts) {
        System.out.println("Account Holder: " + account.getAccountHolder() + ", Balance: " + account.getBalance());
    }

    boolean exit = false;

    while (!exit) {
        printMainMenu();
        int mainChoice = scanner.nextInt();
        switch (mainChoice) {
            case 1:
                createAccount(accountDAO, scanner);
                break;
            case 2:
                interactWithAccount(accountDAO, scanner);
                break;
            case 3:
                displayAllBalances(allAccounts);
                break;
            case 4:
                deleteAccount(accountDAO, scanner);
                break;
            case 5:
                exit = true;
                System.out.println("Exiting the application.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    scanner.close();
}

    private static void printMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Create New Account");
        System.out.println("2. Interact with Account");
        System.out.println("3. Display All Balances");
        System.out.println("4. Delete Account");
        System.out.println("5. Exit");
        System.out.print("Select an option: ");
    }

    private static void createAccount(AccountDAO accountDAO, Scanner scanner) {
        try {
            System.out.print("Enter the account holder's name: ");
            String accountHolderName = scanner.next();
            System.out.print("Enter the initial balance: ");
            double initialBalance = scanner.nextDouble();

            if (initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance cannot be negative.");
            }

            BankAccount newAccount = new BankAccount(accountHolderName, initialBalance);
            accountDAO.insertAccount(newAccount);
            System.out.println("Account created for " + accountHolderName);
        } catch (IllegalArgumentException e) {
            System.out.println("Account creation failed: " + e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for the initial balance.");
            scanner.nextLine();
        }
    }

    private static void interactWithAccount(AccountDAO accountDAO, Scanner scanner) {
        System.out.print("Enter the account holder's name: ");
        String accountHolderName = scanner.next();
        BankAccount account = accountDAO.findBankAccountByName(accountHolderName);

        if (account == null) {
            System.out.println("Account not found for the provided name: " + accountHolderName);
            return;
        }

        boolean accountMenuClosed = false;

        while (!accountMenuClosed) {
            printAccountMenu(account);
            int accountChoice = scanner.nextInt();
            switch (accountChoice) {
                case 1:
                    depositMoney(accountDAO, account, scanner);
                    break;
                case 2:
                    withdrawMoney(accountDAO, account, scanner);
                    break;
                case 3:
                    transferMoney(accountDAO, account, scanner);
                    break;
                case 4:
                    System.out.println("Your current balance is: " + account.getBalance());
                    break;
                case 5:
                    accountMenuClosed = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static BankAccount findAccountByName(AccountDAO accountDAO, String accountHolderName) {
        List<BankAccount> allAccounts = accountDAO.getAllAccounts();
        for (BankAccount account : allAccounts) {
            if (account.getAccountHolder().equals(accountHolderName)) {
                return account;
            }
        }
        return null;
    }

    private static void printAccountMenu(BankAccount account) {
        System.out.println("\nAccount Menu for " + account.getAccountHolder() + ":");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Check Balance");
        System.out.println("5. Back to Main Menu");
        System.out.print("Select an option: ");
    }

    private static void depositMoney(AccountDAO accountDAO, BankAccount account, Scanner scanner) {
        System.out.print("Enter the deposit amount: ");
        double depositAmount = scanner.nextDouble();
        account.deposit(depositAmount);
        accountDAO.updateAccount(account);
    }

    private static void withdrawMoney(AccountDAO accountDAO, BankAccount account, Scanner scanner) {
        System.out.print("Enter the withdrawal amount: ");
        double withdrawalAmount = scanner.nextDouble();
        if (withdrawalAmount > 0 && withdrawalAmount <= account.getBalance()) {
            account.withdraw(withdrawalAmount);
            System.out.println("Withdrawn: " + withdrawalAmount);

            accountDAO.updateAccount(account);
        } else {
            System.out.println("Withdrawal failed. Insufficient balance.");
        }
    }

    private static void displayAllBalances(List<BankAccount> allAccounts) {
        System.out.println("\nBalances of All Accounts:");
        for (BankAccount account : allAccounts) {
            System.out.println("Account Holder: " + account.getAccountHolder() + ", Balance: " + account.getBalance());
        }
    }

    private static void transferMoney(AccountDAO accountDAO, BankAccount sender, Scanner scanner) {
        System.out.print("Enter the recipient's account holder's name: ");
        String recipientName = scanner.next();

        BankAccount recipient = accountDAO.findBankAccountByName(recipientName);

        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        System.out.print("Enter the transfer amount: ");
        double transferAmount = scanner.nextDouble();

        if (transferAmount > 0 && sender.getBalance() >= transferAmount) {
            sender.withdraw(transferAmount);
            recipient.deposit(transferAmount);

            accountDAO.updateAccount(sender);
            accountDAO.updateAccount(recipient);

            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed. Check your inputs.");
        }
    }

}
