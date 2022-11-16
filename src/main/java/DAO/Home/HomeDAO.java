package DAO.Home;

import models.Client.Client;
import models.Home.Home;

import java.util.List;

public interface HomeDAO
{
    public Home findById(int id);
    public Home findByAddress(String address);
    public Home findByNumber_of_flat(String number_of_flat);

    public void save(Home home);
    public void update(Home home);
    public void delete(Home home);

    public Client findClientById(int id);
    public List<Client> findAllClients();
}
