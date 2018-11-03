## Infiniti-Space-Bank-Virtual-Wallet


## Assumptions
1. Can only withdraw up to the amount that you have. If it exceeds, then it simply withraws as much as it can. So max(balance, amount)
2. A user can be created, and can only create or make changes to the accounts using the Wallet API.

## Main API Endpoints
1. User.getWallet(), Creates a new wallet for a user.
2. Wallet.addAccount(), Adds an account that a user can perfrom transactions on.
3. Wallet.viewAccounts(), Returns an ArrayList of accountID's that users can pass to the wallet to perform transactions.
4. Wallet.deposit(double amount, String accountID), Adds the amount to the account's balance.
5. Wallet.withdraw(double amount, String accountID), Subtracts the amount from the account's balance
6. Wallet.transfer(double amount, String senderID, String recipientID), Subtracts the amount from the sender's balance and adds it to the recipient's balance.
7. Wallet.getAccountBalance(String accountID), Returns the total balance from the given account ID.
8. Wallet.getLastNTransactions(String accountID, int N), Returns an ArrayList of Transaction Objects
