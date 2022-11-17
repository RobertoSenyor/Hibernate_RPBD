package service.BankAccount;

import DAO.BankAccount.BankAccountDAO;
import models.BankAccount.BankAccount;
import models.Client.Client;
import models.Deposit.Deposit;
import models.Home.Home;
import util.HibernateSessionFactoryUtil;

public class BankAccountDAOImpl implements BankAccountDAO
{
    @Override
    public BankAccount findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(BankAccount.class, id);
    }

    @Override
    public BankAccount findByNumber_of_account(String number_of_account) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(BankAccount.class, number_of_account);
    }

    @Override
    public BankAccount findByDate_open(String date_open) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(BankAccount.class, date_open);
    }

    @Override
    public BankAccount findByDate_close(String date_close) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(BankAccount.class, date_close);
    }

    @Override
    public BankAccount findByMoney_sum(int money_sum) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(BankAccount.class, money_sum);
    }

    @Override
    public void save(BankAccount bankAccount) {

    }

    @Override
    public void update(BankAccount bankAccount) {

    }

    @Override
    public void delete(BankAccount bankAccount) {

    }

    @Override
    public Client findClientById(int id) {
        return null;
    }

    @Override
    public Deposit findDepositById(int id) {
        return null;
    }
}
