package service.Deposit;

import DAO.Deposit.DepositDAO;
import DAO.Deposit.DepositDAOImpl;
import models.BankAccount.BankAccount;
import models.Deposit.Deposit;

import java.util.List;

public class DepositService
{
    private DepositDAOImpl depositDAO = new DepositDAOImpl();

    public DepositService(){}

    /**
     * Найти запись по id (сначала в памяти, потом в БД)
     * @param id
     * @return Object=Deposit
     */
    public Deposit findDepositById(int id)
    {
        return depositDAO.findById(id);
    }

    /**
     * Найти запись по полному или частичному совпадению наименованию вклада (сначала в памяти, потом в БД)
     * @param name_of_deposit
     * @return List
     */
    public List<Deposit> findDepositByName_of_deposit(String name_of_deposit)
    {
        return depositDAO.findByName_of_deposit(name_of_deposit);
    }

    /**
     * Найти запись по полному или частичному совпадению времени хранения вклада (сначала в памяти, потом в БД)
     * @param storage_time
     * @return List
     */
    public List<Deposit> findDepositByStorage_time(int storage_time)
    {
        return depositDAO.findByStorage_time(storage_time);
    }

    /**
     * Найти запись по полному или частичному совпадению процентной ставке (сначала в памяти, потом в БД)
     * @param interest_rate
     * @return List
     */
    public List<Deposit> findDepositByInterest_rate(int interest_rate)
    {
        return depositDAO.findByInterest_rate(interest_rate);
    }

    /**
     * Сохранить запись
     * @param deposit
     */
    public void saveDeposit(Deposit deposit) { depositDAO.save(deposit); }

    /**
     * Обновить запись
     * @param deposit
     */
    public void updateDeposit(Deposit deposit) { depositDAO.update(deposit); }

    /**
     * Удалить запись
     * @param deposit
     */
    public void deleteDeposit(Deposit deposit) { depositDAO.delete(deposit); }

    /**
     * Найти запись банковского счёта по id (сначала в памяти, потом в БД)
     * @param id
     * @return Object=BankAccount
     */
    public BankAccount findBankAccountById(int id)
    {
        return depositDAO.findBankAccountById(id);
    }

    /**
     * Ищет все записи Deposit в БД
     * @return List<Deposit>
     */
    public List<Deposit> findAllDeposits()
    {
        return depositDAO.findAll();
    }
}
