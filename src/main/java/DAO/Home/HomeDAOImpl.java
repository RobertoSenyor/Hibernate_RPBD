package DAO.Home;

import Singleton.Singleton;
import models.Client.Client;
import models.Home.Home;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeDAOImpl implements HomeDAO
{
    @Override
    public Home findById(int id)
    {
        Session session = null;
        Home buf_home = null;

        for (Home tmp : Singleton.getInstance().getHomeVector())
        {
            if(id == tmp.getId())
            {
                buf_home = tmp;
                break;
            }
        }

        if(buf_home == null) {

            try {
                session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                buf_home = session.get(Home.class, id);
            } catch (Exception e) {
                System.out.println("Exception HomeDAOImpl findById: " + e);
            } finally {
//                session.close();
            }

            if(buf_home!= null)
            Singleton.getInstance().getHomeVector().add(buf_home);
        }

        return buf_home;
    }

    @Override
    public List<Home> findByAddress(String _address)
    {
        Query query = null;
        List<Home> homes = new ArrayList<>();

        for (Home tmp : Singleton.getInstance().getHomeVector())
        {
            if(tmp.getAddress().toLowerCase().contains(_address.toLowerCase()))
            {
                homes.add(tmp);
            }
        }

        if(homes.isEmpty())
        {
            String hql = "from Home where address ilike '%" + _address + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, Home.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception HomeDAOImpl findByAddress: " + e);
            }
            finally
            {
                homes.addAll(query.list());
//                HibernateSessionFactoryUtil.getSessionFactory().close();
            }

            if(!homes.isEmpty())
            Singleton.getInstance().getHomeVector().addAll(homes);
        }
        return homes;
    }

    @Override
    public List<Home> findByNumber_of_flat(String _number_of_flat)
    {
        Query query = null;
        List<Home> homes = new ArrayList<>();

        for (Home tmp : Singleton.getInstance().getHomeVector())
        {
            if(tmp.getNumber_of_flat().toLowerCase().contains(_number_of_flat.toLowerCase()))
            {
                homes.add(tmp);
            }
        }

        if(homes.isEmpty())
        {
            String hql = "from Home where number_of_flat ilike '%" + _number_of_flat + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, Home.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception HomeDAOImpl findByNumber_of_flat: " + e);
            }
            finally
            {
                homes.addAll(query.list());
//                HibernateSessionFactoryUtil.getSessionFactory().close();
            }

            if(!homes.isEmpty())
            Singleton.getInstance().getHomeVector().addAll(homes);
        }

        return homes;
    }

    @Override
    public void save(Home home)
    {
        if (home != null)
        {
            Singleton.getInstance().getHomeVector().add(home);

            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction save_query = session.beginTransaction();
            session.persist(home);
            save_query.commit();
            session.close();
        }
        else return;
    }

    @Override
    public void update(Home home)
    {
        if (home != null)
        {
            Singleton.getInstance().getHomeVector().set(Singleton.getInstance().getHomeVector().indexOf(home), home);

            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction update_query = session.beginTransaction();
            session.merge(home);
            update_query.commit();
            session.close();
        }
        else return;
    }

    @Override
    public void delete(Home home)
    {
        if (home != null)
        {
            Singleton.getInstance().getHomeVector().remove(home);

            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction delete_query = session.beginTransaction();
            session.delete(home);
            delete_query.commit();
            session.close();
        }
        else return;
    }

    @Override
    public Client findClientById(int id)
    {
        Session session = null;
        Client buf_client = null;

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if(id == tmp.getId())
            {
                buf_client = tmp;
                break;
            }
        }

        if(buf_client == null)
        {
            try
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                buf_client = session.get(Client.class, id);
            }
            catch (Exception e)
            {
                System.out.println("Exception HomeDAOImpl findClientById: " + e);
            }
            finally
            {
//                session.close();
            }

            if(buf_client!=null)
            Singleton.getInstance().getClientVector().add(buf_client);
        }

        return buf_client;
    }

    @Override
    public List<Home> findAll()
    {
        List<Home> homes = new ArrayList<>();

        try
        {
            homes.addAll((List<Home>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Home").list());
        }
        catch (Exception e)
        {
            System.out.println("Exception HomeDAOImpl findAll: " + e);
        }
        finally
        {
//            HibernateSessionFactoryUtil.getSessionFactory().close();
        }

        return homes;
    }
}
