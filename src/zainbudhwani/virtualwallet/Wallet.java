package zainbudhwani.virtualwallet;

import java.util.ArrayList;
import java.util.HashMap;

public class Wallet {

    // Hashmap will store the key value pair of the accountID to the AccountObject

    // Users will need to pass in the accountID in order to make changes, which they can receive from viewAccounts()

    private HashMap<String, TransactionAccount> transactionAccounts;


    public Wallet() {
        transactionAccounts = new HashMap<>();
    }

    // Adds an account that user can perform transactions on.
    void addAccount() {
        TransactionAccount newAccount = new TransactionAccount();
        transactionAccounts.put(newAccount.getAccountNumber(), newAccount);
    }

    // Returns an ArrayList of accountID's that users can pass to the Wallet API to perform transactions.
    ArrayList<String> viewAccounts() {
        return new ArrayList<>(transactionAccounts.keySet());
    }

    // Adds the amount to the account's balance. Needs to be synchronized to avoid race conditions
    public synchronized void deposit(double amount, String accountID) throws IncorrectAccountIDException {
        if(transactionAccounts.containsKey(accountID)) {
            transactionAccounts.get(accountID).deposit(amount);
        } else {
            throw new IncorrectAccountIDException(accountID + " does not exist.");
        }
    }

    // Subtracts the amount from the account's balance. Needs to be synchronized to avoid race conditions
    public synchronized void withdraw(double amount, String accountID) throws IncorrectAccountIDException, AvailableBalanceZeroException {
        if(transactionAccounts.containsKey(accountID)) {
            double balance = transactionAccounts.get(accountID).getAccountBalance();
            if(balance != 0){
                if(amount >= balance) {
                    amount = balance; // Can only withdraw as much as you have.
                }
                transactionAccounts.get(accountID).withdraw(amount);
            } else {
                throw new AvailableBalanceZeroException("Cannot withdraw. Balance is zero");
            }
        } else {
            throw new IncorrectAccountIDException(accountID + " does not exist.");
        }
    }

    // Subtracts the amount from the senders's balance and adds to recipient's balance.
    // Needs to be synchronized to avoid race conditions
    public synchronized void transfer(double amount, String senderID, String recipientID) throws IncorrectAccountIDException, AvailableBalanceZeroException {
        if(transactionAccounts.containsKey(senderID) && transactionAccounts.containsKey(recipientID)) {
            double balance = transactionAccounts.get(senderID).getAccountBalance();
            if(balance != 0) {
                if(amount >= balance) { // Can only transfer as much as you have.
                    amount = balance;
                }
                transactionAccounts.get(senderID).transfer("sender", amount, recipientID);
                transactionAccounts.get(recipientID).transfer("recipient", amount, senderID);
            } else {
                throw new AvailableBalanceZeroException("Cannot transfer. Balance is zero");
            }
        } else {
            throw new IncorrectAccountIDException(senderID + " or " + recipientID + " does not exist");
        }
    }

    // Returns an ArrayList of the last N Transaction objects
    ArrayList<Transaction> getLastNTransactions(String accountID, int N) throws IncorrectAccountIDException {
        if(transactionAccounts.containsKey(accountID)) {
            return transactionAccounts.get(accountID).getLastNTransactions(N);
        } else {
            throw new IncorrectAccountIDException(accountID + " does not exist.");
        }
    }

    // Returns the total balance from the given ID's account
    public double getAccountBalance(String accountID) throws IncorrectAccountIDException {
        if(transactionAccounts.containsKey(accountID)) {
            return transactionAccounts.get(accountID).getAccountBalance();
        } else {
            throw new IncorrectAccountIDException(accountID + " does not exist.");
        }
    }

}
