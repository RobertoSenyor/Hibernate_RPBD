package service.BankAccount;

import DAO.BankAccount.BankAccountDAOImpl;
import models.BankAccount.BankAccount;

import java.text.ParseException;
import java.util.List;

public class BankAccountService {
    private BankAccountDAOImpl bankAccountDAO = new BankAccountDAOImpl();

    public BankAccountService() {
    }

    /**
     * Найти запись по id (сначала в памяти, потом в БД)
     * @param id
     * @return Object=Home
     */
    public BankAccount findBankAccountById(int id) {
        return bankAccountDAO.findById(id);
    }

     /**
     * Найти запись по полному или частичному совпадению номеру счёта (сначала в памяти, потом в БД)
     * @param number_of_account
     * @return List
     */
    public List<BankAccount> findBankAccountByNumber_of_account(String number_of_account)
    {
        return bankAccountDAO.findByNumber_of_account(number_of_account);
    }

    /**
     * Найти запись по точной дате открытия счёта (сначала в памяти, потом в БД)
     * @param date_open
     * @return List
     */
    public List<BankAccount> findBankAccountByDate_open(String date_open) {
        return bankAccountDAO.findByDate_open(date_open);
    }

    /**
     * Найти запись по точной дате закрытия счёта (сначала в памяти, потом в БД)
     * @param date_close
     * @return List
     */
    public List<BankAccount> findBankAccountByDate_close(String date_close) {
        return bankAccountDAO.findByDate_close(date_close);
    }

    /**
     * Найти запись по сумме денег на счете (сначала в памяти, потом в БД)
     * @param money_sum
     * @return List
     */
    public List<BankAccount> findBankAccountByMoney_sum(int money_sum) {
        return bankAccountDAO.findByMoney_sum(money_sum);
    }

    /**
     * Найти запись по дате открытия счета в задаваемом диапазоне (сначала в памяти, потом в БД)
     * @params dateFrom, dateTo
     * @return List
     */
    public List<BankAccount> findBankAccountByBetweenDate_open(String dateFrom, String dateTo) throws ParseException {
        return bankAccountDAO.findBetweenOpenDate(dateFrom, dateTo);
    }

    /**
     * Найти запись по дате закрытия счета в задаваемом диапазоне (сначала в памяти, потом в БД)
     * @params dateFrom, dateTo
     * @return List
     */
    public List<BankAccount> findBankAccountByBetweenDate_close(String dateFrom, String dateTo) throws ParseException {
        return bankAccountDAO.findBetweenCloseDate(dateFrom, dateTo);
    }

    /**
     * Сохранить запись
     * @param bankAccount
     */
    public void saveBankAccount(BankAccount bankAccount) {
        bankAccountDAO.save(bankAccount);
    }

    /**
     * Обновить запись
     * @param bankAccount
     */
    public void updateBankAccount(BankAccount bankAccount) {
        bankAccountDAO.update(bankAccount);
    }

    /**
     * Удалить запись
     * @param bankAccount
     */
    public void deleteBankAccount(BankAccount bankAccount) {
        bankAccountDAO.delete(bankAccount);
    }

    /**
     * Ищет все записи BankAccount в БД
     * @return List<BankAccount>
     */
    public List<BankAccount> findAllBankAccounts()
    {
        return bankAccountDAO.findAll();
    }
}
