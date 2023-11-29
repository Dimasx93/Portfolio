package bank_app;
import sql_db.AccountDAO;

import java.util.List;
import java.util.Scanner;

public class BankApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();

        AccountDAO accountDAO = new AccountDAO();

        List<BankAccount> allAccounts = accountDAO.getAllAccounts();

        System.out.println("All Accounts:");
        for (BankAccount account : allAccounts) {
            System.out.println(account);
        }

        boolean exit = false;

        while (!exit) {
            printMainMenu();
            int mainChoice = scanner.nextInt();
            switch (mainChoice) {
                case 1:
                    createAccount(bank, scanner);
                    break;
                case 2:
                    interactWithAccount(bank, scanner);
                    break;
                case 3:
                    bank.displayAllBalances();
                    break;
                case 4:
//                    deleteAccount(bank, scanner);
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

    private static void createAccount(Bank bank, Scanner scanner) {
        try {
            System.out.print("Enter the account holder's name: ");
            String accountHolderName = scanner.next();
            System.out.print("Enter the initial balance: ");
            double initialBalance = scanner.nextDouble();

            if (initialBalance < 0) {
                throw new IllegalArgumentException("Initial balance cannot be negative.");
            }

            BankAccount newAccount = new BankAccount(accountHolderName, initialBalance);
            bank.addBankAccount(newAccount);
            System.out.println("Account created for " + accountHolderName);
        } catch (IllegalArgumentException e) {
            System.out.println("Account creation failed: " + e.getMessage());
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number for the initial balance.");
            scanner.nextLine();
        }
    }

    private static void interactWithAccount(Bank bank, Scanner scanner) {
        System.out.print("Enter the account holder's name: ");
        String accountHolderName = scanner.next();
        BankAccount account = bank.findBankAccountByName(accountHolderName);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        boolean accountMenuClosed = false;

        while (!accountMenuClosed) {
            printAccountMenu(account);
            int accountChoice = scanner.nextInt();
            switch (accountChoice) {
                case 1:
                    depositMoney(account, scanner);
                    break;
                case 2:
                    withdrawMoney(account, scanner);
                    break;
                case 3:
//                    transferMoney(bank, account, scanner);
                    break;
                case 4:
                    account.printBalance();
                    break;
                case 5:
                    accountMenuClosed = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printAccountMenu(BankAccount account) {
        System.out.println("\nAccount Menu for " + account.getName() + ":");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Check Balance");
        System.out.println("5. Back to Main Menu");
        System.out.print("Select an option: ");
    }

    private static void depositMoney(BankAccount account, Scanner scanner) {
        System.out.print("Enter the deposit amount: ");
        double depositAmount = scanner.nextDouble();
        account.deposit(depositAmount);
    }

    private static void withdrawMoney(BankAccount account, Scanner scanner) {
        System.out.print("Enter the withdrawal amount: ");
        double withdrawAmount = scanner.nextDouble();
        account.withdraw(withdrawAmount);
    }

    public static void transferMoney(AccountDAO bank, BankAccount sender, Scanner scanner) {
        System.out.print("Enter the recipient's account holder's name: ");
        String recipientName = scanner.next();
        System.out.print("Enter the transfer amount: ");
        double transferAmount = scanner.nextDouble();
        bank.makeTransfer(sender.getName(), recipientName, transferAmount);
    }

    public static void deleteAccount(AccountDAO bank, Scanner scanner) {
        System.out.print("Enter the account holder's name to delete: ");
        String accountHolderName = scanner.next();

        BankAccount accountToDelete = bank.findBankAccountByName(accountHolderName);
        if (accountToDelete == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Are you sure you want to delete the account (y/n)? ");
        String confirmation = scanner.next().toLowerCase();

        if (confirmation.equals("y")) {
            double accountBalance = accountToDelete.getBalance();

            if (accountBalance > 0) {
                System.out.println("There are funds left in the account: Euro " + accountBalance);
                System.out.print("Are you sure you want to delete it (y/n)? ");
                confirmation = scanner.next().toLowerCase();

                if (confirmation.equals("y")) {
                    bank.deleteBankAccount(accountHolderName);
                    System.out.println("Account deleted.");
                } else {
                    System.out.println("Account not deleted.");
                }
            } else {
                bank.deleteBankAccount(accountHolderName);
                System.out.println("Account deleted.");
            }
        } else {
            System.out.println("Account not deleted.");
        }
    }

}