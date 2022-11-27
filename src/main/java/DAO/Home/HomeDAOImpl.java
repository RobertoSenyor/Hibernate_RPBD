package DAO.Home;

import Singleton.Singleton;
import models.Client.Client;
import models.Home.Home;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeDAOImpl implements HomeDAO
{
    @Override
    public int get_count_nodes()
    {
        int size = 0;

        Session session = null;

        if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
        {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

            if (session.getTransaction().isActive())
            {
                Transaction transaction = session.getTransaction();

                try
                {
                    size = session.createQuery("from Home").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception HomeDAOImpl get_count: " + e);
                }
                finally
                {
                    transaction.commit();
                }
            }
            else
            {
                Transaction transaction = session.beginTransaction();

                try
                {
                    size = session.createQuery("from Home").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception HomeDAOImpl get_count: " + e);
                }
            }
        }
        else
        {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

            if (session.getTransaction().isActive())
            {
                Transaction transaction = session.getTransaction();

                try
                {
                    size = session.createQuery("from Home").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception HomeDAOImpl get_count: " + e);
                }
                finally
                {
                    transaction.commit();
                }
            }
            else
            {
                Transaction transaction = session.beginTransaction();

                try
                {
                    size = session.createQuery("from Home").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception HomeDAOImpl get_count: " + e);
                }
            }
        }

        return size;
    }

    @Override
    public List<Home> findNoOneById(int id, int step)
    {
        Query query = null;
        List<Home> homes = new ArrayList<>();

        Singleton.getInstance().getHomeVector().clear();

        String hql = "from Home where id >= " + id + " order by id limit " + step;

        Session session = null;

        if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
        {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

            if (session.getTransaction().isActive())
            {
                Transaction transaction = session.getTransaction();

                try
                {
                    query = session.createQuery(hql, Home.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception HomeDAOImpl find_no_one: " + e);
                }
                finally
                {
                    homes.addAll(query.list());
                    transaction.commit();
                }
                if (!homes.isEmpty())
                    Singleton.getInstance().getHomeVector().addAll(homes);
            }
            else
            {
                Transaction transaction = session.beginTransaction();

                try
                {
                    query = session.createQuery(hql, Home.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception HomeDAOImpl find_no_one: " + e);
                }
                finally
                {
                    homes.addAll(query.list());
                }
                if (!homes.isEmpty())
                    Singleton.getInstance().getHomeVector().addAll(homes);
            }
        }
        else
        {
            session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

            if (session.getTransaction().isActive())
            {
                Transaction transaction = session.getTransaction();

                try
                {
                    query = session.createQuery(hql, Home.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception HomeDAOImpl find_no_one: " + e);
                }
                finally
                {
                    homes.addAll(query.list());
                    transaction.commit();
                }
                if (!homes.isEmpty())
                    Singleton.getInstance().getHomeVector().addAll(homes);
            }
            else
            {
                Transaction transaction = session.beginTransaction();

                try
                {
                    query = session.createQuery(hql, Home.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception HomeDAOImpl find_no_one: " + e);
                }
                finally
                {
                    homes.addAll(query.list());
                    transaction.commit();
                }
                if (!homes.isEmpty())
                    Singleton.getInstance().getHomeVector().addAll(homes);
            }
        }

        return homes;
    }

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
        List<Home> buf_homes = new ArrayList<>();

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
                buf_homes.addAll(query.list());
                boolean find_contains = false;

                for (Home tmp1 : buf_homes)
                {
                    find_contains=false;

                    for (Home tmp : Singleton.getInstance().getHomeVector())
                    {
                        if (tmp.getAddress().contains(tmp1.getAddress()))
                            find_contains = true;
                    }

                    if (find_contains == false)
                        homes.add(tmp1);
                }
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
        List<Home> buf_homes = new ArrayList<>();

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
                buf_homes.addAll(query.list());
                boolean find_contains = false;

                for (Home tmp1 : buf_homes)
                {
                    find_contains=false;

                    for (Home tmp : Singleton.getInstance().getHomeVector())
                    {
                        if (tmp.getNumber_of_flat().contains(tmp1.getNumber_of_flat()))
                            find_contains = true;
                    }

                    if (find_contains == false)
                        homes.add(tmp1);
                }
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

            Session session = null;

            if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

                if (session.getTransaction().isActive())
                {
                    Transaction save_query = session.getTransaction();

                    try
                    {
                        session.persist(home);
                    }
                    catch (Exception e)
                    {
                        System.out.println("HomeDAOImpl save: " + e);
                    }
                    finally
                    {
                        save_query.commit();
                    }
                }
                else
                {
                    Transaction save_query = session.beginTransaction();

                    try
                    {
                        session.persist(home);
                    }
                    catch (Exception e)
                    {
                        System.out.println("HomeDAOImpl update: " + e);
                    }
                }
            }
            else
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

                if (session.getTransaction().isActive())
                {
                    Transaction save_query = session.getTransaction();

                    try
                    {
                        session.persist(home);
                    }
                    catch (Exception e)
                    {
                        System.out.println("HomeDAOImpl save: " + e);
                    }
                    finally
                    {
                        save_query.commit();
                    }
                }
                else
                {
                    Transaction save_query = session.beginTransaction();

                    try
                    {
                        session.persist(home);
                    }
                    catch (Exception e)
                    {
                        System.out.println("HomeDAOImpl save: " + e);
                    }
                }
            }
        }
        else
            return;
    }

    @Override
    public void update(Home home)
    {
        if (home != null)
        {
            Singleton.getInstance().getHomeVector().set(Singleton.getInstance().getHomeVector().indexOf(home), home);

            Session session = null;

            if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

                if (session.getTransaction().isActive())
                {
                    Transaction update_query = session.getTransaction();

                    try
                    {
                        session.merge(home);
                    }
                    catch (Exception e)
                    {
                        System.out.println("HomeDAOImpl update: " + e);
                    }
                    finally
                    {
                        update_query.commit();
                    }
                }
                else
                {
                    Transaction update_query = session.beginTransaction();

                    try
                    {
                        session.merge(home);
                    }
                    catch (Exception e)
                    {
                        System.out.println("HomeDAOImpl update: " + e);
                    }
                }
            }
            else
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

                if (session.getTransaction().isActive())
                {
                    Transaction update_query = session.getTransaction();

                    try
                    {
                        session.merge(home);
                    }
                    catch (Exception e)
                    {
                        System.out.println("HomeDAOImpl update: " + e);
                    }
                    finally
                    {
                        update_query.commit();
                    }
                }
                else
                {
                    Transaction update_query = session.beginTransaction();

                    try
                    {
                        session.merge(home);
                    }
                    catch (Exception e)
                    {
                        System.out.println("HomeDAOImpl update: " + e);
                    }
                }
            }
        }
        else
            return;
    }

    @Override
    public void delete(Home home)
    {
        if (home != null)
        {
            Singleton.getInstance().getHomeVector().remove(home);

            Session session = null;

            if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

                if (session.getTransaction().isActive())
                {
                    Transaction delete_query = session.getTransaction();

                    try
                    {
                        session.delete(home);
                    }
                    catch (Exception e)
                    {
                        System.out.println("HomeDAOImpl delete: " + e);
                    }
                    finally
                    {
                        delete_query.commit();
                    }
                }
                else
                {
                    Transaction delete_query = session.beginTransaction();

                    try
                    {
                        session.delete(home);
                    }
                    catch (Exception e)
                    {
                        System.out.println("HomeDAOImpl delete: " + e);
                    }
                }
            }
            else
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

                if (session.getTransaction().isActive())
                {
                    Transaction delete_query = session.getTransaction();

                    try
                    {
                        session.delete(home);
                    }
                    catch (Exception e)
                    {
                        System.out.println("HomeDAOImpl delete: " + e);
                    }
                    finally
                    {
                        delete_query.commit();
                    }
                }
                else
                {
                    Transaction delete_query = session.beginTransaction();

                    try
                    {
                        session.delete(home);
                    }
                    catch (Exception e)
                    {
                        System.out.println("HomeDAOImpl delete: " + e);
                    }
                }
            }
        }
        else
            return;
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
