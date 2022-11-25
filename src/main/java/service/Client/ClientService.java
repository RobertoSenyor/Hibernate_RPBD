package service.Client;

import DAO.Client.ClientDAOImpl;
import models.BankAccount.BankAccount;
import models.Client.Client;

import java.util.List;

public class ClientService
{
    private ClientDAOImpl clientDAO = new ClientDAOImpl();

    /**
     * Найти запись по id (сначала в памяти, потом в БД)
     * @param id
     * @return Object=Client
     */
    public Client findClientById(int id)
    {
        return clientDAO.findById(id);
    }

    public List<Client> findClientByName_Surname(String name, String surname)
    {
        return clientDAO.findByName_Surname(name, surname);
    }

    /**
     * Найти запись по полному или частичному совпадению имени (сначала в памяти, потом в БД)
     * @param name
     * @return List
     */
    public List<Client> findClientByName(String name)
    {
        return clientDAO.findByName(name);
    }

    /**
     * Найти запись по полному или частичному совпадению фамилии (сначала в памяти, потом в БД)
     * @param surname
     * @return List
     */
    public List<Client> findClientBySurame(String surname)
    {
        return clientDAO.findBySurname(surname);
    }

    /**
     * Найти запись по полному или частичному совпадению отчества (сначала в памяти, потом в БД)
     * @param fathername
     * @return List
     */
    public List<Client> findClientByFathername(String fathername)
    {
        return clientDAO.findByFathername(fathername);
    }

    /**
     * Найти запись по полному или частичному совпадению серии паспорта клиента (сначала в памяти, потом в БД)
     * @param serial
     * @return List
     */
    public List<Client> findClientBySerial_of_pasport(int serial)
    {
        return clientDAO.findBySerial_of_pasport(serial);
    }

     /**
     * Найти запись по полному или частичному совпадению номеру паспорта клиента (сначала в памяти, потом в БД)
     * @param number
     * @return List
     */
    public List<Client> findClientByNumber_of_pasport(int number)
    {
        return clientDAO.findByNumber_of_pasport(number);
    }

    /**
     * Найти запись по полному или частичному совпадению номеру телефона клиента (сначала в памяти, потом в БД)
     * @param telephone
     * @return List
     */
    public List<Client> findClientByTelephone(String telephone)
    {
        return clientDAO.findByTelephone(telephone);
    }

    /**
     * Сохранить запись
     * @param client
     */
    public void saveClient(Client client)
    {
        clientDAO.save(client);
    }

    /**
     * Обновить запись
     * @param client
     */
    public void updateClient(Client client)
    {
        clientDAO.update(client);
    }

    /**
     * Удалить запись
     * @param client
     */
    public void deleteClient(Client client)
    {
        clientDAO.delete(client);
    }

    /**
     * Найти запись банковского счёта по id (сначала в памяти, потом в БД)
     * @param id
     * @return Object=BankAccount
     */
    public BankAccount findBankAccountById(int id)
    {
        return clientDAO.findBankAccountById(id);
    }

    /**
     * Ищет все записи Client в БД
     * @return List<Client>
     */
    public List<Client> findAllClients()
    {
        return clientDAO.findAll();
    }
}
