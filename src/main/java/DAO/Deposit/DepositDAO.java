package DAO.Deposit;
import models.BankAccount.BankAccount;
import models.Deposit.Deposit;

import java.text.ParseException;
import java.util.List;

public interface DepositDAO
{
    /**
     * Получить количество существующих записей
     * @return int=count
     */
    public int get_count_nodes();

    /**
     * Найти записи в количестве step
     * @param id
     * @param step
     * @return List
     */
    public List<Deposit> findNoOneById(int id, int step);

    /**
     * Найти запись по id (сначала в памяти, потом в БД)
     * @param id
     * @return Object=Deposit
     */
    public Deposit findById(int id);

    /**
     * Найти запись по полному или частичному совпадению наименованию вклада (сначала в памяти, потом в БД)
     * @param name_of_deposit
     * @return List
     */
    public List<Deposit> findByName_of_deposit(String name_of_deposit);

    /**
     * Найти запись по полному или частичному совпадению времени хранения вклада (сначала в памяти, потом в БД)
     * @param storage_time
     * @return List
     */
    public List<Deposit> findByStorage_time(int storage_time);

    /**
     * Найти запись по полному или частичному совпадению процентной ставке (сначала в памяти, потом в БД)
     * @param interest_rate
     * @return List
     */
    public List<Deposit> findByInterest_rate(int interest_rate);

    /**
     * Сохранить запись
     * @param deposit
     */
    public void save(Deposit deposit);

    /**
     * Обновить запись
     * @param deposit
     */
    public void update(Deposit deposit);

    /**
     * Удалить запись
     * @param deposit
     */
    public void delete(Deposit deposit);

    /**
     * Найти запись банковского счёта по id (сначала в памяти, потом в БД)
     * @param id
     * @return Object=BankAccount
     */
    public BankAccount findBankAccountById(int id);

    /**
     * Ищет все записи Deposit в БД
     * @return List<Deposit>
     */
    public List<Deposit> findAll();
}
