package service.Home;

import DAO.Home.HomeDAOImpl;
import models.Client.Client;
import models.Home.Home;

import java.util.List;

public class HomeService
{
    private HomeDAOImpl homeDAO = new HomeDAOImpl();

    public HomeService(){}

    /**
     * Найти запись по id (сначала в памяти, потом в БД)
     * @param id
     * @return Object=Home
     */
    public Home findHomeById(int id)
    {
        return homeDAO.findById(id);
    }

    /**
     * Найти запись по полному или частичному совпадению адреса (сначала в памяти, потом в БД)
     * @param address
     * @return List
     */
    public List<Home> findHomeByAddress(String address)
    {
        return homeDAO.findByAddress(address);
    }

    /**
     * Найти запись по полному или частичному совпадению номера квартиры (сначала в памяти, потом в БД)
     * @param _number_of_flat
     * @return List
     */
    public List<Home> findHomeByNumber_of_flat(String _number_of_flat)
    {
        return homeDAO.findByNumber_of_flat(_number_of_flat);
    }

    /**
     * Сохранить запись
     * @param home
     */
    public void saveHome(Home home)
    {
        homeDAO.save(home);
    }

    /**
     * Обновить запись
     * @param home
     */
    public void updateHome(Home home)
    {
        homeDAO.update(home);
    }

    /**
     * Удалить запись
     * @param home
     */
    public void deleteHome(Home home)
    {
        homeDAO.delete(home);
    }

    /**
     * Найти запись клиента по id (сначала в памяти, потом в БД)
     * @param id
     * @return Object=Client
     */
    public Client findClientById(int id)
    {
        return  homeDAO.findClientById(id);
    }

    /**
     * Ищет все записи Home в БД
     * @return List<Home>
     */
    public List<Home> findAll(String input)
    {
        return homeDAO.findAll();
    }
}
