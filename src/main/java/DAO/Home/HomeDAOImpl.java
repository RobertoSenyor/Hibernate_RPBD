package DAO.Home;

import models.Client.Client;
import models.Home.Home;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
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
    public List<Home> findByAddress(String address) {

        String query_find = "from Home where address ilike '%" + address + "%'";

        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                createQuery(query_find, Home.class);
        return query.list();
    }

    @Override
    public List<Home> findByNumber_of_flat(String _number_of_flat) {

        String query_find = "from Home where number_of_flat ilike '%" + _number_of_flat + "%'";

        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                createQuery(query_find, Home.class);
        return query.list();
    }

    @Override
    public void save(Home home) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction save_query = session.beginTransaction();
        session.persist(home);
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
    public List<Home> findAll() {
        List<Home> clients = (List<Home>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Home", Home.class).list();
        return clients;
    }
}
