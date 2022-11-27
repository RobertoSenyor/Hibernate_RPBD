package DAO.Client;

import DAO.BankAccount.BankAccountDAOImpl;
import Singleton.Singleton;
import java.util.ArrayList;
import java.util.List;
import models.BankAccount.BankAccount;
import models.Client.Client;
import models.Home.Home;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import service.BankAccount.BankAccountService;
import util.HibernateSessionFactoryUtil;

public class ClientDAOImpl implements ClientDAO
{
    @Override public int get_count_nodes()
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
                    size = session.createQuery("from Client").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception ClientDAOImpl get_count: " + e);
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
                    size = session.createQuery("from Client").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception ClientDAOImpl get_count: " + e);
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
                    size = session.createQuery("from Client").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception ClientDAOImpl get_count: " + e);
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
                    size = session.createQuery("from Client").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception ClientDAOImpl get_count: " + e);
                }
            }
        }

        return size;
    }

    @Override public List<Client> findNoOneById(int id, int step)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        Singleton.getInstance().getClientVector().clear();

        String hql = "from Client where id >= " + id + " order by id limit " + step;

        Session session = null;

        if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
        {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

            if (session.getTransaction().isActive())
            {
                Transaction transaction = session.getTransaction();

                try
                {
                    query = session.createQuery(hql, Client.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception ClientDAOImpl find_no_one: " + e);
                }
                finally
                {
                    clients.addAll(query.list());
                    transaction.commit();
                }
                if (!clients.isEmpty())
                    Singleton.getInstance().getClientVector().addAll(clients);
            }
            else
            {
                Transaction transaction = session.beginTransaction();

                try
                {
                    query = session.createQuery(hql, Client.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception ClientDAOImpl find_no_one: " + e);
                }
                finally
                {
                    clients.addAll(query.list());
                }
                if (!clients.isEmpty())
                    Singleton.getInstance().getClientVector().addAll(clients);
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
                    query = session.createQuery(hql, Client.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception ClientDAOImpl find_no_one: " + e);
                }
                finally
                {
                    clients.addAll(query.list());
                    transaction.commit();
                }
                if (!clients.isEmpty())
                    Singleton.getInstance().getClientVector().addAll(clients);
            }
            else
            {
                Transaction transaction = session.beginTransaction();

                try
                {
                    query = session.createQuery(hql, Client.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception ClientDAOImpl find_no_one: " + e);
                }
                finally
                {
                    clients.addAll(query.list());
                    transaction.commit();
                }
                if (!clients.isEmpty())
                    Singleton.getInstance().getClientVector().addAll(clients);
            }
        }

        return clients;
    }

    @Override public Client findById(int id)
    {
        Session session = null;
        Client buf_client = null;

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if (tmp.getId() == id)
            {
                buf_client = tmp;
                break;
            }
        }

        if (buf_client == null)
        {
            try
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                buf_client = session.get(Client.class, id);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findById: " + e);
            }
            finally
            {
                //                HibernateSessionFactoryUtil.getSessionFactory().close();
            }

            if (buf_client != null)
                Singleton.getInstance().getClientVector().add(buf_client);
        }

        return buf_client;
    }

    @Override public List<Client> findByName_Surname(String name, String surname)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if (tmp.getName().toLowerCase().contains(name.toLowerCase()) &&
                tmp.getSurname().toLowerCase().contains(surname.toLowerCase()))
            {
                clients.add(tmp);
            }
        }

        if (clients.isEmpty())
        {
            String hql = "from Client where name ilike '%" + name + "%' and surname ilike '%" +
                surname + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                    hql, Client.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findByName: " + e);
            }
            finally
            {
                clients.addAll(query.list());
            }

            if (!clients.isEmpty())
                Singleton.getInstance().getClientVector().addAll(clients);
        }

        return clients;
    }

    @Override public List<Client> findByName(String name)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if (tmp.getName().toLowerCase().contains(name.toLowerCase()))
            {
                clients.add(tmp);
            }
        }

        if (clients.isEmpty())
        {
            String hql = "from Client where name ilike '%" + name + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                    hql, Client.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findByName: " + e);
            }
            finally
            {
                clients.addAll(query.list());
            }

            if (!clients.isEmpty())
                Singleton.getInstance().getClientVector().addAll(clients);
        }

        return clients;
    }

    @Override public List<Client> findBySurname(String surname)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if (tmp.getSurname().toLowerCase().contains(surname.toLowerCase()))
            {
                clients.add(tmp);
            }
        }

        if (clients.isEmpty())
        {
            String hql = "from Client where surname ilike '%" + surname + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                    hql, Client.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findBySurname: " + e);
            }
            finally
            {
                clients.addAll(query.list());
            }

            if (!clients.isEmpty())
                Singleton.getInstance().getClientVector().addAll(clients);
        }

        return clients;
    }

    @Override public List<Client> findByFathername(String fathername)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if (tmp.getFathername().toLowerCase().contains(fathername.toLowerCase()))
            {
                clients.add(tmp);
            }
        }

        if (clients.isEmpty())
        {
            String hql = "from Client where fathername ilike '%" + fathername + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                    hql, Client.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findByFathername: " + e);
            }
            finally
            {
                clients.addAll(query.list());
            }

            if (!clients.isEmpty())
                Singleton.getInstance().getClientVector().addAll(clients);
        }

        return clients;
    }

    @Override public List<Client> findBySerial_of_pasport(int serial)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if (String.valueOf(tmp.getSerial_of_pasport())
                    .toLowerCase()
                    .contains(String.valueOf(serial).toLowerCase()))
            {
                clients.add(tmp);
            }
        }

        if (clients.isEmpty())
        {
            String hql =
                "from Client where substring(cast(serial_of_pasport as char(4)), 1, 4) ilike '%" +
                serial + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                    hql, Client.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findBySerial_of_pasport: " + e);
            }
            finally
            {
                clients.addAll(query.list());
            }

            if (!clients.isEmpty())
                Singleton.getInstance().getClientVector().addAll(clients);
        }

        return clients;
    }

    @Override public List<Client> findByNumber_of_pasport(int number)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if (String.valueOf(tmp.getNumber_of_pasport())
                    .toLowerCase()
                    .contains(String.valueOf(number).toLowerCase()))
            {
                clients.add(tmp);
            }
        }

        if (clients.isEmpty())
        {

            String hql =
                "from Client where substring(cast(number_of_pasport as char(6)), 1, 6) ilike '%" +
                number + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                    hql, Client.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findByNumber_of_pasport: " + e);
            }
            finally
            {
                clients.addAll(query.list());
            }

            if (!clients.isEmpty())
                Singleton.getInstance().getClientVector().addAll(clients);
        }

        return clients;
    }

    @Override public List<Client> findByTelephone(String telephone)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if (tmp.getTelephone().toLowerCase().contains(telephone.toLowerCase()))
            {
                clients.add(tmp);
            }
        }

        if (clients.isEmpty())
        {
            String hql = "from Client where telephone ilike '%" + telephone + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                    hql, Client.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findByTelephone: " + e);
            }
            finally
            {
                clients.addAll(query.list());
            }

            if (!clients.isEmpty())
                Singleton.getInstance().getClientVector().addAll(clients);
        }

        return clients;
    }

    @Override public void save(Client client)
    {
        if (client != null)
        {
            Singleton.getInstance().getClientVector().add(client);

            Session session = null;

            if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

                if (session.getTransaction().isActive())
                {
                    Transaction save_query = session.getTransaction();

                    try
                    {
                        session.persist(client);
                    }
                    catch (Exception e)
                    {
                        System.out.println("ClientDAOImpl save: " + e);
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
                        session.persist(client);
                    }
                    catch (Exception e)
                    {
                        System.out.println("ClientDAOImpl update: " + e);
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
                        session.persist(client);
                    }
                    catch (Exception e)
                    {
                        System.out.println("ClientDAOImpl save: " + e);
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
                        session.persist(client);
                    }
                    catch (Exception e)
                    {
                        System.out.println("ClientDAOImpl save: " + e);
                    }
                }
            }
        }
        else
            return;
    }

    @Override public void update(Client client)
    {
        if (client != null)
        {
            Singleton.getInstance().getClientVector().set(
                Singleton.getInstance().getClientVector().indexOf(client), client);

            Session session = null;

            if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

                if (session.getTransaction().isActive())
                {
                    Transaction update_query = session.getTransaction();

                    try
                    {
                        session.merge(client);
                    }
                    catch (Exception e)
                    {
                        System.out.println("ClientDAOImpl update: " + e);
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
                        session.merge(client);
                    }
                    catch (Exception e)
                    {
                        System.out.println("ClientDAOImpl update: " + e);
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
                        session.merge(client);
                    }
                    catch (Exception e)
                    {
                        System.out.println("ClientDAOImpl update: " + e);
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
                        session.merge(client);
                    }
                    catch (Exception e)
                    {
                        System.out.println("ClientDAOImpl update: " + e);
                    }
                }
            }
        }
        else
            return;
    }

    @Override public void delete(Client client)
    {
        if (client != null)
        {
            Singleton.getInstance().getClientVector().remove(client);

            Session session = null;

            if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

                if (session.getTransaction().isActive())
                {
                    Transaction delete_query = session.getTransaction();

                    try
                    {
                        session.delete(client);
                    }
                    catch (Exception e)
                    {
                        System.out.println("ClientDAOImpl delete: " + e);
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
                        session.delete(client);
                    }
                    catch (Exception e)
                    {
                        System.out.println("ClientDAOImpl delete: " + e);
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
                        session.delete(client);
                    }
                    catch (Exception e)
                    {
                        System.out.println("ClientDAOImpl delete: " + e);
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
                        session.delete(client);
                    }
                    catch (Exception e)
                    {
                        System.out.println("ClientDAOImpl delete: " + e);
                    }
                }
            }
        }
        else
            return;
    }

    @Override public BankAccount findBankAccountById(int id)
    {
        Session session = null;
        BankAccount buf_account = null;

        for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
        {
            if (tmp.getId() == id)
            {
                buf_account = tmp;
                break;
            }
        }

        if (buf_account == null)
        {
            try
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                buf_account = session.get(BankAccount.class, id);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findBankAccountById: " + e);
            }

            if (buf_account != null)
                Singleton.getInstance().getBankAccountVector().add(buf_account);
        }

        return buf_account;
    }

    @Override public List<Client> findAll()
    {
        List<Client> clients = new ArrayList<>();

        try
        {
            clients.addAll((List<Client>)HibernateSessionFactoryUtil.getSessionFactory()
                               .openSession()
                               .createQuery("from Client")
                               .list());
        }
        catch (Exception e)
        {
            System.out.println("Exception ClientDAOImpl findAll: " + e);
        }

        return clients;
    }
}
