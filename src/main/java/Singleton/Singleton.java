package Singleton;

import models.BankAccount.BankAccount;
import models.Client.Client;
import models.Deposit.Deposit;
import models.Home.Home;
import java.util.Vector;

public class Singleton
{
    private Vector<BankAccount> bankAccountVector;
    private Vector<Client> clientVector;
    private Vector<Deposit> depositVector;
    private Vector<Home> homeVector;

    private static Singleton INSTANCE;

    private Singleton()
    {
        bankAccountVector = new Vector<>();
        clientVector = new Vector<>();
        depositVector = new Vector<>();
        homeVector = new Vector<>();
    }

    public static Singleton getInstance()
    {
        if (INSTANCE == null)
        {
            synchronized (Singleton.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = new Singleton();
                }
            }
        }
        return INSTANCE;
    }

    public Vector<BankAccount> getBankAccountVector()
    {
        return bankAccountVector;
    }

    public Vector<Client> getClientVector()
    {
        return clientVector;
    }

    public Vector<Deposit> getDepositVector()
    {
        return depositVector;
    }

    public Vector<Home> getHomeVector()
    {
        return homeVector;
    }
}
