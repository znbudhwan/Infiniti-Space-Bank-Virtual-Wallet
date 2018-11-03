package zainbudhwani.virtualwallet;
import java.util.UUID;

// Transaction Objects are created from withdrawals, deposits, and transfers

public class Transaction {
    private String transactionID;
    private TransactionType type;
    private double amount;
    private String timestamp;
    private String memo;

    public Transaction(double amount, String timestamp, TransactionType type, String memo) {
        this.transactionID = UUID.randomUUID().toString();
        this.type = type;
        this.amount = amount;
        this.timestamp = timestamp;
        this.memo = memo;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public String getTransactionID() {
        return this.transactionID;
    }

    public TransactionType getType() {
        return this.type;
    }

    public String getMemo() {
        return memo;
    }
}
