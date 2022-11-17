package service.Home;

import DAO.Home.HomeDAOImpl;
import models.Client.Client;
import models.Home.Home;

import java.util.List;

public class HomeService
{
    private HomeDAOImpl homeDAO = new HomeDAOImpl();

    public HomeService(){}

    public Home findHomeById(int id)
    {
        return homeDAO.findById(id);
    }

    public List<Home> findHomeByAddress(String address)
    {
        return homeDAO.findByAddress(address);
    }

    public List<Home> findHomeByNumber_of_flat(String _number_of_flat)
    {
        return homeDAO.findByNumber_of_flat(_number_of_flat);
    }

    public void saveHome(Home home)
    {
        homeDAO.save(home);
    }

    public void updateHome(Home home)
    {
        homeDAO.update(home);
    }

    public void deleteHome(Home home)
    {
        homeDAO.delete(home);
    }

    public Client findClientById(int id)
    {
        return  homeDAO.findClientById(id);
    }

    public List<Home> findAll()
    {
        return homeDAO.findAll();
    }
}
