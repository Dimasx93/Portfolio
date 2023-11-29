package sql_db;

import bank_app.BankAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class AccountDAO { public List<BankAccount> getAllAccounts() {
    String sql = "SELECT * FROM accounts";
    List<BankAccount> accounts = new ArrayList<>();

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement pstmt = connection.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {

        while (rs.next()) {
            BankAccount account = new BankAccount(
                    rs.getString("account_number"),
                    rs.getString("account_holder"),
                    rs.getDouble("balance")
            );
            accounts.add(account);
        }
    } catch (SQLException e) {
        System.err.println("Error querying accounts: " + e.getMessage());
    }
    return accounts;
}

    public BankAccount getAccountByAccountNumber(String accountNumber) {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        BankAccount account = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                account = new BankAccount(
                        rs.getString("account_number"),
                        rs.getString("account_holder"),
                        rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error querying account: " + e.getMessage());
        }

        return account;
    }

    public void insertAccount(BankAccount account) {
        String sql = "INSERT INTO accounts (account_number, account_holder, balance) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, account.getAccountNumber());
            pstmt.setString(2, account.getAccountHolder());
            pstmt.setDouble(3, account.getBalance());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting account: " + e.getMessage());
        }
    }

    public void updateAccount(BankAccount account) {
        String sql = "UPDATE accounts SET account_holder = ?, balance = ? WHERE account_number = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, account.getAccountHolder());
            pstmt.setDouble(2, account.getBalance());
            pstmt.setString(3, account.getAccountNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating account: " + e.getMessage());
        }
    }

    public void deleteAccount(String accountNumber) {
        String sql = "DELETE FROM accounts WHERE account_number = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, accountNumber);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting account: " + e.getMessage());
        }
    }

    public void makeTransfer(String senderAccountNumber, String recipientAccountNumber, double amount) {
        // Check if both sender and recipient accounts exist
        BankAccount sender = getAccountByAccountNumber(senderAccountNumber);
        BankAccount recipient = getAccountByAccountNumber(recipientAccountNumber);

        if (sender == null || recipient == null) {
            System.out.println("Sender or recipient account not found.");
            return;
        }

        // Check if the sender has sufficient balance for the transfer
        if (sender.getBalance() < amount) {
            System.out.println("Transfer failed. Insufficient balance in the sender's account.");
            return;
        }

        // Update the sender's balance (decrease) and recipient's balance (increase)
        double senderNewBalance = sender.getBalance() - amount;
        double recipientNewBalance = recipient.getBalance() + amount;

        try (Connection connection = DatabaseConnection.getConnection()) {
            String updateSenderSql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
            String updateRecipientSql = "UPDATE accounts SET balance = ? WHERE account_number = ?";

            try (PreparedStatement updateSenderStmt = connection.prepareStatement(updateSenderSql);
                 PreparedStatement updateRecipientStmt = connection.prepareStatement(updateRecipientSql)) {
                connection.setAutoCommit(false); // Start a transaction

                updateSenderStmt.setDouble(1, senderNewBalance);
                updateSenderStmt.setString(2, senderAccountNumber);
                updateSenderStmt.executeUpdate();

                updateRecipientStmt.setDouble(1, recipientNewBalance);
                updateRecipientStmt.setString(2, recipientAccountNumber);
                updateRecipientStmt.executeUpdate();

                connection.commit(); // Commit the transaction
            } catch (SQLException e) {
                connection.rollback(); // Rollback the transaction in case of an error
                System.err.println("Transfer failed: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }

        System.out.println("Transfer successful. New balances: Sender - " + senderNewBalance + ", Recipient - " + recipientNewBalance);
    }

    public BankAccount findBankAccountByName(String accountHolderName) {
        String sql = "SELECT * FROM accounts WHERE account_holder = ?";
        BankAccount account = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, accountHolderName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                account = new BankAccount(
                        rs.getString("account_number"),
                        rs.getString("account_holder"),
                        rs.getDouble("balance")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error finding account: " + e.getMessage());
        }

        return account;
    }

    public void deleteBankAccount(String accountHolderName) {
        String sql = "DELETE FROM accounts WHERE account_holder = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, accountHolderName);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Account for " + accountHolderName + " has been deleted.");
            } else {
                System.out.println("No account found for " + accountHolderName + ". No deletion performed.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting account: " + e.getMessage());
        }
    }
}
