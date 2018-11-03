package zainbudhwani.virtualwallet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

// Our main focus of manipulation using the Wallet API

public class TransactionAccount {
    private String accountNumber;
    private double accountBalance;
    private ArrayList<Transaction> transactions;

    private ArrayList<Transaction> getTransactions(int N) {
        ArrayList<Transaction> lastNTransactions = new ArrayList<>();
        if(N >= transactions.size()) { // Will return the max of the list of the transactions or N
            return transactions;
        } else {
            for(int i = N; i >= 0; i--) {
                lastNTransactions.add(transactions.get(i));
            }
        }
        return lastNTransactions;
    }

    private void depositAmount(double amount) {
        accountBalance += amount;
        Transaction creditTransaction = new Transaction(amount, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),
                TransactionType.DEPOSIT,"");
        transactions.add(creditTransaction);
    }

    private void withDrawAmount(double amount) {
        accountBalance -= amount;
        Transaction debitTransaction = new Transaction(amount, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),
                TransactionType.WITHDRAWAL,"");
        transactions.add(debitTransaction);
    }

    private void transferAmount(String transferType, double amount, String accountID) {
        if(transferType.equals("sender")) {
            accountBalance -= amount;
            Transaction transferTransaction = new Transaction(amount, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),
                    TransactionType.TRANSFER,"Transferred to: " + accountID);
            transactions.add(transferTransaction);
        } else {
            accountBalance += amount;
            Transaction transferTransaction = new Transaction(amount, new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()),
                    TransactionType.TRANSFER,"Transferred from: " + accountID);
            transactions.add(transferTransaction);
        }
    }


    public TransactionAccount() {
        this.accountBalance = 0.0;
        this.accountNumber = UUID.randomUUID().toString();
        this.transactions = new ArrayList<>();
    }

    public double getAccountBalance() {
        return this.accountBalance;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void deposit(double amount) {
        this.depositAmount(amount);
    }

    public void withdraw(double amount) {
        this.withDrawAmount(amount);
    }

    public void transfer(String transferType, double amount, String accountID) {
        this.transferAmount(transferType, amount, accountID);
    }

    ArrayList<Transaction> getLastNTransactions(int N) {
        return this.getTransactions(N);
    }
}
