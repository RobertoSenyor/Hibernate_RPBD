package DAO.BankAccount;

import models.BankAccount.BankAccount;
import models.Client.Client;
import models.Deposit.Deposit;

import java.text.ParseException;
import java.util.List;

public interface BankAccountDAO
{
    /**
     * Найти запись по id (сначала в памяти, потом в БД)
     * @param id
     * @return Object=Home
     */
    public BankAccount findById(int id);

    /**
     * Найти запись по полному или частичному совпадению номеру счёта (сначала в памяти, потом в БД)
     * @param number_of_account
     * @return List
     */
    public List<BankAccount> findByNumber_of_account(String number_of_account);

    /**
     * Найти запись по точной дате открытия счёта (сначала в памяти, потом в БД)
     * @param date_open
     * @return List
     */
    public List<BankAccount> findByDate_open(String date_open);

    /**
     * Найти запись по точной дате закрытия счёта (сначала в памяти, потом в БД)
     * @param date_close
     * @return List
     */
    public List<BankAccount> findByDate_close(String date_close);

    /**
     * Найти запись по сумме денег на счете (сначала в памяти, потом в БД)
     * @param money_sum
     * @return List
     */
    public List<BankAccount> findByMoney_sum(int money_sum);

    /**
     * Найти запись по дате открытия счета в задаваемом диапазоне (сначала в памяти, потом в БД)
     * @params dateFrom, dateTo
     * @return List
     */
    public List<BankAccount> findBetweenOpenDate(String dateFrom, String dateTo) throws ParseException;

    /**
     * Найти запись по дате закрытия счета в задаваемом диапазоне (сначала в памяти, потом в БД)
     * @params dateFrom, dateTo
     * @return List
     */
    public List<BankAccount> findBetweenCloseDate(String dateFrom, String dateTo) throws ParseException;

    /**
     * Сохранить запись
     * @param bankAccount
     */
    public void save(BankAccount bankAccount);

    /**
     * Обновить запись
     * @param bankAccount
     */
    public void update(BankAccount bankAccount);

    /**
     * Удалить запись
     * @param bankAccount
     */
    public void delete(BankAccount bankAccount);

    /**
     * Ищет все записи BankAccount в БД
     * @return List<BankAccount>
     */
    public List<BankAccount> findAll();
}
