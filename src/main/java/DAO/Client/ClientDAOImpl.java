package DAO.Client;

import Singleton.Singleton;
import models.BankAccount.BankAccount;
import models.Client.Client;
import models.Home.Home;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO
{
    @Override
    public Client findById(int id)
    {
        Session session = null;
        Client buf_client = null;

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if(tmp.getId() == id)
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

            if(buf_client != null)
                Singleton.getInstance().getClientVector().add(buf_client);
        }

        return buf_client;
    }

    @Override
    public List<Client> findByName_Surname(String name, String surname)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if(tmp.getName().toLowerCase().contains(name.toLowerCase()) && tmp.getSurname().toLowerCase().contains(surname.toLowerCase()))
            {
                clients.add(tmp);
            }
        }

        if(clients.isEmpty())
        {
            String hql = "from Client where name ilike '%" + name + "%' and surname ilike '%" + surname + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, Client.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findByName: " + e);
            }
            finally
            {
                clients.addAll(query.list());
            }

            if(!clients.isEmpty())
                Singleton.getInstance().getClientVector().addAll(clients);
        }

        return clients;
    }

    @Override
    public List<Client> findByName(String name)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if(tmp.getName().toLowerCase().contains(name.toLowerCase()))
            {
                clients.add(tmp);
            }
        }

        if(clients.isEmpty())
        {
            String hql = "from Client where name ilike '%" + name + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, Client.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findByName: " + e);
            }
            finally
            {
                clients.addAll(query.list());
            }

            if(!clients.isEmpty())
                Singleton.getInstance().getClientVector().addAll(clients);
        }

        return clients;
    }

    @Override
    public List<Client> findBySurname(String surname)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if(tmp.getSurname().toLowerCase().contains(surname.toLowerCase()))
            {
                clients.add(tmp);
            }
        }

        if(clients.isEmpty())
        {
            String hql = "from Client where surname ilike '%" + surname + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, Client.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findBySurname: " + e);
            }
            finally
            {
                clients.addAll(query.list());
            }

            if(!clients.isEmpty())
                Singleton.getInstance().getClientVector().addAll(clients);
        }

        return clients;
    }

    @Override
    public List<Client> findByFathername(String fathername)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if(tmp.getFathername().toLowerCase().contains(fathername.toLowerCase()))
            {
                clients.add(tmp);
            }
        }

        if(clients.isEmpty())
        {
            String hql = "from Client where fathername ilike '%" + fathername + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, Client.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findByFathername: " + e);
            }
            finally
            {
                clients.addAll(query.list());
            }

            if(!clients.isEmpty())
                Singleton.getInstance().getClientVector().addAll(clients);
        }

        return clients;
    }

    @Override
    public List<Client> findBySerial_of_pasport(int serial)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if(String.valueOf(tmp.getSerial_of_pasport()).toLowerCase().contains(String.valueOf(serial).toLowerCase()))
            {
                clients.add(tmp);
            }
        }

        if(clients.isEmpty())
        {
            String hql = "from Client where serial_of_pasport::varchar ilike '%" + String.valueOf(serial) + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, Client.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findBySerial_of_pasport: " + e);
            }
            finally
            {
                clients.addAll(query.list());
            }

            if(!clients.isEmpty())
                Singleton.getInstance().getClientVector().addAll(clients);
        }

        return clients;
    }

    @Override
    public List<Client> findByNumber_of_pasport(int number)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if(String.valueOf(tmp.getNumber_of_pasport()).toLowerCase().contains(String.valueOf(number).toLowerCase()))
            {
                clients.add(tmp);
            }
        }

        if(clients.isEmpty())
        {
            String hql = "from Client where number_of_pasport::varchar ilike '%" + String.valueOf(number) + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, Client.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findByNumber_of_pasport: " + e);
            }
            finally
            {
                clients.addAll(query.list());
            }

            if(!clients.isEmpty())
                Singleton.getInstance().getClientVector().addAll(clients);
        }

        return clients;
    }

    @Override
    public List<Client> findByTelephone(String telephone)
    {
        Query query = null;
        List<Client> clients = new ArrayList<>();

        for (Client tmp : Singleton.getInstance().getClientVector())
        {
            if(tmp.getTelephone().toLowerCase().contains(telephone.toLowerCase()))
            {
                clients.add(tmp);
            }
        }

        if(clients.isEmpty())
        {
            String hql = "from Client where telephone ilike '%" + telephone + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, Client.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception ClientDAOImpl findByTelephone: " + e);
            }
            finally
            {
                clients.addAll(query.list());
            }

            if(!clients.isEmpty())
                Singleton.getInstance().getClientVector().addAll(clients);
        }

        return clients;
    }

    @Override
    public void save(Client client)
    {
        if(client != null)
        {
            Singleton.getInstance().getClientVector().add(client);

            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction save_query = session.beginTransaction();
            session.persist(client);
            save_query.commit();
            session.close();
        }
        else return;
    }

    @Override
    public void update(Client client)
    {
        if(client != null)
        {
            Singleton.getInstance().getClientVector().add(client);

            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction update_query = session.beginTransaction();
            session.merge(client);
            update_query.commit();
            session.close();
        }
        else return;
    }

    @Override
    public void delete(Client client)
    {
        if(client != null)
        {
            Singleton.getInstance().getClientVector().add(client);

            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction delete_query = session.beginTransaction();
            session.delete(client);
            delete_query.commit();
            session.close();
        }
        else return;
    }

    @Override
    public BankAccount findBankAccountById(int id)
    {
        Session session = null;
        BankAccount buf_account = null;

        for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
        {
            if(tmp.getId() == id)
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

            if(buf_account != null)
                Singleton.getInstance().getBankAccountVector().add(buf_account);
        }

        return buf_account;
    }

    @Override
    public List<Client> findAll()
    {
        List<Client> clients = new ArrayList<>();

        try
        {
            clients.addAll((List<Client>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Client").list());
        }
        catch (Exception e)
        {
            System.out.println("Exception ClientDAOImpl findAll: " + e);
        }

        return clients;
    }
}
