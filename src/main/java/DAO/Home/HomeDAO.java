package DAO.Home;

import models.Client.Client;
import models.Home.Home;

import java.util.List;

public interface HomeDAO
{
    /**
     * Найти запись по id (сначала в памяти, потом в БД)
     * @param id
     * @return Object=Home
     */
    public Home findById(int id);

    /**
     * Найти запись по полному или частичному совпадению адреса (сначала в памяти, потом в БД)
     * @param address
     * @return List
     */
    public List<Home> findByAddress(String address);

    /**
     * Найти запись по полному или частичному совпадению номера квартиры (сначала в памяти, потом в БД)
     * @param _number_of_flat
     * @return List
     */
    public List<Home> findByNumber_of_flat(String _number_of_flat);

    /**
     * Сохранить запись
     * @param home
     */
    public void save(Home home);

    /**
     * Обновить запись
     * @param home
     */
    public void update(Home home);

    /**
     * Удалить запись
     * @param home
     */
    public void delete(Home home);

    /**
     * Найти запись клиента по id (сначала в памяти, потом в БД)
     * @param id
     * @return Object=Client
     */
    public Client findClientById(int id);

    /**
     * Ищет все записи Home в БД
     * @return List<Home>
     */
    public List<Home> findAll();
}
