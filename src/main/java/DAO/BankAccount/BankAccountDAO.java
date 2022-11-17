package DAO.BankAccount;

import models.BankAccount.BankAccount;
import models.Client.Client;
import models.Deposit.Deposit;

public interface BankAccountDAO
{
    public BankAccount findById(int id);
    public BankAccount findByNumber_of_account(String number_of_account);
    public BankAccount findByDate_open(String date_open);
    public BankAccount findByDate_close(String date_close);
    public BankAccount findByMoney_sum(int money_sum);

    public void save(BankAccount bankAccount);
    public void update(BankAccount bankAccount);
    public void delete(BankAccount bankAccount);

    public Client findClientById(int id);
    public Deposit findDepositById(int id);
}
