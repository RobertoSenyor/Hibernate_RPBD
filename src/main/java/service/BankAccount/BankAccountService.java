package service.BankAccount;

import DAO.BankAccount.BankAccountDAOImpl;
import models.BankAccount.BankAccount;
import models.Client.Client;
import models.Deposit.Deposit;

import java.text.ParseException;
import java.util.List;

public class BankAccountService {
    private BankAccountDAOImpl bankAccountDAO = new BankAccountDAOImpl();

    public BankAccountService() {
    }

    public BankAccount findBankAccountById(int id) {
        return bankAccountDAO.findById(id);
    }

    public List<BankAccount> findBankAccountByDate_open(String date_open) {
        return bankAccountDAO.findByDate_open(date_open);
    }

    public List<BankAccount> findBankAccountByDate_close(String date_close) {
        return bankAccountDAO.findByDate_close(date_close);
    }

    public List<BankAccount> findBankAccountByMoney_sum(int money_sum) {
        return bankAccountDAO.findByMoney_sum(money_sum);
    }

    public List<BankAccount> findBankAccountByBetweenDate_open(String dateFrom, String dateTo) throws ParseException {
        return bankAccountDAO.findBetweenOpenDate(dateFrom, dateTo);
    }

    public List<BankAccount> findBankAccountByBetweenDate_close(String dateFrom, String dateTo) throws ParseException {
        return bankAccountDAO.findBetweenCloseDate(dateFrom, dateTo);
    }

    public void saveBankAccount(BankAccount bankAccount) {
        bankAccountDAO.save(bankAccount);
    }

    public void updateBankAccount(BankAccount bankAccount) {
        bankAccountDAO.update(bankAccount);
    }

    public void deleteBankAccount(BankAccount bankAccount) {
        bankAccountDAO.delete(bankAccount);
    }

    public Client findClientById(int id) {
        return bankAccountDAO.findClientById(id);
    }

    public Deposit findDepositById(int id) {
        return bankAccountDAO.findDepositById(id);
    }

    public List<BankAccount> findAllBankAccounts()
    {
        return bankAccountDAO.findAll();
    }
}
