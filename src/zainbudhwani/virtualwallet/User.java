package zainbudhwani.virtualwallet;

// User.java

// User can have multiple transaction accounts and uses a single wallet to manage transactions within those accounts

public class User {

    private Wallet wallet;

    public Wallet getWallet() {
        wallet = new Wallet();
        return this.wallet;
    }
}
