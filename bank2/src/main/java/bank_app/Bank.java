package bank_app;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final List<BankAccount> bankAccounts;

    public Bank() {
        bankAccounts = new ArrayList<>();
    }

    public void addBankAccount(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }

    public BankAccount findBankAccountByName(String accountHolderName) {
        for (int i = 0; i < bankAccounts.size(); i++) {
            BankAccount bankAccount = bankAccounts.get(i);
            if (bankAccount.getName().equals(accountHolderName)) {
                return bankAccount;
            }
        }
        return null;
    }

    public void makeTransfer(String senderName, String receiverName, double transferAmount) {
        BankAccount sender = findBankAccountByName(senderName);
        BankAccount receiver = findBankAccountByName(receiverName);

        if (sender == null || receiver == null) {
            System.out.println("Invalid account name.");
            return;
        }

        try {
            sender.transfer(receiver, transferAmount);
        } catch (IllegalArgumentException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    public void displayAllBalances() {
        for (int i = 0; i < bankAccounts.size(); i++) {
            BankAccount bankAccount = bankAccounts.get(i);
            bankAccount.printBalance();
        }
    }

    public void deleteBankAccount(String accountHolderName) {
        BankAccount accountToDelete = findBankAccountByName(accountHolderName);
        if (accountToDelete != null) {
            bankAccounts.remove(accountToDelete);
            System.out.println("Account for " + accountHolderName + " deleted.");
        } else {
            System.out.println("Account not found. Deletion failed.");
        }
    }

}

