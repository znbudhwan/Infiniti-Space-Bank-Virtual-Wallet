package zainbudhwani.virtualwallet;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class TestingAllTransactions {

    private User user = new User();
    private Wallet wallet = user.getWallet();


    @Test
    public void testAccountAddition() {
        wallet.addAccount();
        wallet.addAccount();
        assertEquals(wallet.viewAccounts().size(), 2, 0);

    }

    @Test
    public void testDepositTransaction() {
        wallet.addAccount();
        double testingBalance = 0.0;
        try {
            wallet.deposit(5.0, wallet.viewAccounts().get(0));

        } catch(Exception e) {
            System.out.println(e);
        }
        try {
            testingBalance = wallet.getAccountBalance(wallet.viewAccounts().get(0));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(testingBalance, 5.0, 0);
    }

    @Test
    public void testWithdrawTransaction() {
        wallet.addAccount();
        double testingBalance = 0.0;
        try {
            wallet.deposit(5.0, wallet.viewAccounts().get(0));

        } catch(Exception e) {
            System.out.println(e);
        }
        try {
            wallet.withdraw(2.0, wallet.viewAccounts().get(0));

        } catch(Exception e) {
            System.out.println(e);
        }
        try {
            testingBalance = wallet.getAccountBalance(wallet.viewAccounts().get(0));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(testingBalance, 3.0, 0);
    }

    @Test
    public void testTransferTransaction() {
        wallet.addAccount();
        wallet.addAccount();
        double testingBalanceOne = 0.0;
        double testingBalanceTwo = 0.0;
        try {
            wallet.deposit(5.0, wallet.viewAccounts().get(0));

        } catch(Exception e) {
            System.out.println(e);
        }
        try {
            wallet.transfer(2.0, wallet.viewAccounts().get(0), wallet.viewAccounts().get(1));

        } catch(Exception e) {
            System.out.println(e);
        }
        try {
            testingBalanceOne = wallet.getAccountBalance(wallet.viewAccounts().get(0));
            testingBalanceTwo = wallet.getAccountBalance(wallet.viewAccounts().get(1));
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(testingBalanceOne, 3.0, 0);
        assertEquals(testingBalanceTwo, 2.0, 0);
    }

    @Test
    public void testListingNTransaction() {
        wallet.addAccount();
        wallet.addAccount();
        int testingAmount = 0;
        try {
            wallet.deposit(5.0, wallet.viewAccounts().get(0));

        } catch(Exception e) {
            System.out.println(e);
        }
        try {
            wallet.transfer(2.0, wallet.viewAccounts().get(0), wallet.viewAccounts().get(1));

        } catch(Exception e) {
            System.out.println(e);
        }
        try {
            testingAmount = wallet.getLastNTransactions(wallet.viewAccounts().get(0), 3).size();

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(testingAmount, 2, 0);
    }

    @Test
    public void testConcurrency() {
        wallet.addAccount();
        double testingBalance = 0.0;
        try {
            testingBalance = wallet.getAccountBalance(wallet.viewAccounts().get(0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i < 10; i++) {
                    try {
                        wallet.deposit(10.0, wallet.viewAccounts().get(0));
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        wallet.withdraw(8.0, wallet.viewAccounts().get(0));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e)
        {
            System.out.println("Interrupt Occurred");
            e.printStackTrace();
        }

        try {
            testingBalance = wallet.getAccountBalance(wallet.viewAccounts().get(0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(testingBalance, 20.0, 0);

    }


}
