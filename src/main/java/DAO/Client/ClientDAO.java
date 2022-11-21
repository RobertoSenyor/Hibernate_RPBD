package DAO.Client;

import models.BankAccount.BankAccount;
import models.Client.Client;
import models.Home.Home;

import java.util.List;

public interface ClientDAO
{
    /**
     * Найти запись по id (сначала в памяти, потом в БД)
     * @param id
     * @return Object=Client
     */
    public Client findById(int id);

    /**
     * Найти запись по полному или частичному совпадению имени (сначала в памяти, потом в БД)
     * @param name
     * @return List
     */
    public List<Client> findByName(String name);

    /**
     * Найти запись по полному или частичному совпадению фамилии (сначала в памяти, потом в БД)
     * @param surname
     * @return List
     */
    public List<Client> findBySurname(String surname);

    /**
     * Найти запись по полному или частичному совпадению отчества (сначала в памяти, потом в БД)
     * @param fathername
     * @return List
     */
    public List<Client> findByFathername(String fathername);

    /**
     * Найти запись по полному или частичному совпадению серии паспорта клиента (сначала в памяти, потом в БД)
     * @param serial
     * @return List
     */
    public List<Client> findBySerial_of_pasport(int serial);

    /**
     * Найти запись по полному или частичному совпадению номеру паспорта клиента (сначала в памяти, потом в БД)
     * @param number
     * @return List
     */
    public List<Client> findByNumber_of_pasport(int number);

    /**
     * Найти запись по полному или частичному совпадению номеру телефона клиента (сначала в памяти, потом в БД)
     * @param telephone
     * @return List
     */
    public List<Client> findByTelephone(String telephone);

    /**
     * Сохранить запись
     * @param client
     */
    public void save(Client client);

    /**
     * Обновить запись
     * @param client
     */
    public void update(Client client);

    /**
     * Удалить запись
     * @param client
     */
    public void delete(Client client);

    /**
     * Найти запись банковского счёта по id (сначала в памяти, потом в БД)
     * @param id
     * @return Object=BankAccount
     */
    public BankAccount findBankAccountById(int id);

    /**
     * Ищет все записи Client в БД
     * @return List<Client>
     */
    public List<Client> findAll();
}
