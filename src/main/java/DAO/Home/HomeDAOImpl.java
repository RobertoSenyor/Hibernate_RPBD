package DAO.Home;

import models.Client.Client;
import models.Home.Home;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

/** TODO добавить класс Singletone, хранящий объекты сущностей в памяти
 *  Добавить обработку и взаимодействие запросов к данным
 */

public class HomeDAOImpl implements HomeDAO
{
    @Override
    public Home findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Home.class, id);
    }

    @Override
    public Home findByAddress(String address) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Home.class, address);
    }

    @Override
    public Home findByNumber_of_flat(String number_of_flat) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Home.class, number_of_flat);
    }

    @Override
    public void save(Home home) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction save_query = session.beginTransaction();
        session.save(home);
        save_query.commit();
        session.close();
    }

    @Override
    public void update(Home home) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction save_query = session.beginTransaction();
        session.update(home);
        save_query.commit();
        session.close();
    }

    @Override
    public void delete(Home home) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction save_query = session.beginTransaction();
        session.delete(home);
        save_query.commit();
        session.close();
    }

    @Override
    public Client findClientById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, id);
    }

    @Override
    public List<Client> findAllClients() {
        List<Client> clients = (List<Client>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from client", Client.class).list();
        return clients;
    }
}
