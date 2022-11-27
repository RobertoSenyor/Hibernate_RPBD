package DAO.Deposit;

import Singleton.Singleton;
import models.BankAccount.BankAccount;
import models.Client.Client;
import models.Deposit.Deposit;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.security.Signature;
import java.util.ArrayList;
import java.util.List;

public class DepositDAOImpl implements DepositDAO
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
                    size = session.createQuery("from Deposit").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception DepositDAOImpl get_count: " + e);
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
                    size = session.createQuery("from Deposit").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception DepositDAOImpl get_count: " + e);
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
                    size = session.createQuery("from Deposit").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception DepositDAOImpl get_count: " + e);
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
                    size = session.createQuery("from Deposit").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception DepositDAOImpl get_count: " + e);
                }
            }
        }

        return size;
    }

    @Override
    public List<Deposit> findNoOneById(int id, int step)
    {
        Query query = null;
        List<Deposit> deposits = new ArrayList<>();

        Singleton.getInstance().getDepositVector().clear();

        String hql = "from Deposit where id >= " + id + " order by id limit " + step;

        Session session = null;

        if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
        {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

            if (session.getTransaction().isActive())
            {
                Transaction transaction = session.getTransaction();

                try
                {
                    query = session.createQuery(hql, Deposit.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception DepositDAOImpl find_no_one: " + e);
                }
                finally
                {
                    deposits.addAll(query.list());
                    transaction.commit();
                }
                if (!deposits.isEmpty())
                    Singleton.getInstance().getDepositVector().addAll(deposits);
            }
            else
            {
                Transaction transaction = session.beginTransaction();

                try
                {
                    query = session.createQuery(hql, Deposit.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception DepositDAOImpl find_no_one: " + e);
                }
                finally
                {
                    deposits.addAll(query.list());
                }
                if (!deposits.isEmpty())
                    Singleton.getInstance().getDepositVector().addAll(deposits);
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
                    query = session.createQuery(hql, Deposit.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception DepositDAOImpl find_no_one: " + e);
                }
                finally
                {
                    deposits.addAll(query.list());
                    transaction.commit();
                }
                if (!deposits.isEmpty())
                    Singleton.getInstance().getDepositVector().addAll(deposits);
            }
            else
            {
                Transaction transaction = session.beginTransaction();

                try
                {
                    query = session.createQuery(hql, Deposit.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception DepositDAOImpl find_no_one: " + e);
                }
                finally
                {
                    deposits.addAll(query.list());
                    transaction.commit();
                }
                if (!deposits.isEmpty())
                    Singleton.getInstance().getDepositVector().addAll(deposits);

            }
        }

        return deposits;
    }

    @Override
    public Deposit findById(int id)
    {
        Session session = null;
        Deposit buf_deposit = null;

        for (Deposit tmp : Singleton.getInstance().getDepositVector())
        {
            if (tmp.getId() == id)
            {
                buf_deposit = tmp;
                break;
            }
        }

        if (buf_deposit==null)
        {
            try
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                buf_deposit = session.get(Deposit.class, id);
            }
            catch (Exception e)
            {
                System.out.println("Exception DepositDAOImpl findById: " + e);
            }

            if (buf_deposit != null)
                Singleton.getInstance().getDepositVector().add(buf_deposit);
        }

        return buf_deposit;
    }

    @Override
    public List<Deposit> findByName_of_deposit(String name_of_deposit) {
        Query query = null;
        List<Deposit> deposits = new ArrayList<>();

        for (Deposit tmp : Singleton.getInstance().getDepositVector())
        {
            if(tmp.getName_of_deposit().toLowerCase().contains(name_of_deposit.toLowerCase()))
            {
                deposits.add(tmp);
            }
        }

        if(deposits.isEmpty())
        {
            String hql = "from Deposit where name_of_deposit ilike '%" + name_of_deposit + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, Deposit.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception DepositDAOImpl findByName_of_deposit: " + e);
            }
            finally
            {
                deposits.addAll(query.list());
            }

            if(!deposits.isEmpty())
                Singleton.getInstance().getDepositVector().addAll(deposits);
        }

        return deposits;
    }

    @Override
    public List<Deposit> findByStorage_time(int storage_time)
    {
        Query query = null;
        List<Deposit> deposits = new ArrayList<>();

        for (Deposit tmp : Singleton.getInstance().getDepositVector())
        {
            if(String.valueOf(tmp.getStorage_time()).toLowerCase().contains(String.valueOf(storage_time).toLowerCase()))
            {
                deposits.add(tmp);
            }
        }

        if(deposits.isEmpty())
        {
            String hql =
                    "from Deposit where substring(cast(storage_time as char(10)), 1, 10) ilike '%" + storage_time + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, Deposit.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception DepositDAOImpl findByStorage_time: " + e);
            }
            finally
            {
                deposits.addAll(query.list());
            }

            if(!deposits.isEmpty())
                Singleton.getInstance().getDepositVector().addAll(deposits);
        }

        return deposits;
    }

    @Override
    public List<Deposit> findByInterest_rate(int interest_rate)
    {
        Query query = null;
        List<Deposit> deposits = new ArrayList<>();

        for (Deposit tmp : Singleton.getInstance().getDepositVector())
        {
            if(String.valueOf(tmp.getInterest_rate()).toLowerCase().contains(String.valueOf(interest_rate).toLowerCase()))
            {
                deposits.add(tmp);
            }
        }

        if(deposits.isEmpty())
        {
            String hql =
                    "from Deposit where substring(cast(interest_rate as char(10)), 1, 10) ilike '%" + interest_rate + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, Deposit.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception DepositDAOImpl findByInterest_rate: " + e);
            }
            finally
            {
                deposits.addAll(query.list());
            }

            if(!deposits.isEmpty())
                Singleton.getInstance().getDepositVector().addAll(deposits);
        }

        return deposits;
    }

    @Override
    public void save(Deposit deposit)
    {
        if (deposit != null)
        {
            Singleton.getInstance().getDepositVector().add(deposit);

            Session session = null;

            if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

                if (session.getTransaction().isActive())
                {
                    Transaction save_query = session.getTransaction();

                    try
                    {
                        session.persist(deposit);
                    }
                    catch (Exception e)
                    {
                        System.out.println("DepositDAOImpl save: " + e);
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
                        session.persist(deposit);
                    }
                    catch (Exception e)
                    {
                        System.out.println("DepositDAOImpl update: " + e);
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
                        session.persist(deposit);
                    }
                    catch (Exception e)
                    {
                        System.out.println("DepositDAOImpl save: " + e);
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
                        session.persist(deposit);
                    }
                    catch (Exception e)
                    {
                        System.out.println("DepositDAOImpl save: " + e);
                    }
                }
            }
        }
        else
            return;
    }

    @Override
    public void update(Deposit deposit)
    {
        if (deposit != null)
        {
            Singleton.getInstance().getDepositVector().set(
                    Singleton.getInstance().getDepositVector().indexOf(deposit), deposit);

            Session session = null;

            if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

                if (session.getTransaction().isActive())
                {
                    Transaction update_query = session.getTransaction();

                    try
                    {
                        session.merge(deposit);
                    }
                    catch (Exception e)
                    {
                        System.out.println("DepositDAOImpl update: " + e);
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
                        session.merge(deposit);
                    }
                    catch (Exception e)
                    {
                        System.out.println("DepositDAOImpl update: " + e);
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
                        session.merge(deposit);
                    }
                    catch (Exception e)
                    {
                        System.out.println("DepositDAOImpl update: " + e);
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
                        session.merge(deposit);
                    }
                    catch (Exception e)
                    {
                        System.out.println("DepositDAOImpl update: " + e);
                    }
                }
            }
        }
        else
            return;
    }

    @Override
    public void delete(Deposit deposit)
    {
        if (deposit != null)
        {
            Singleton.getInstance().getDepositVector().remove(deposit);

            Session session = null;

            if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

                if (session.getTransaction().isActive())
                {
                    Transaction delete_query = session.getTransaction();

                    try
                    {
                        session.delete(deposit);
                    }
                    catch (Exception e)
                    {
                        System.out.println("DepositDAOImpl delete: " + e);
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
                        session.delete(deposit);
                    }
                    catch (Exception e)
                    {
                        System.out.println("DepositDAOImpl delete: " + e);
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
                        session.delete(deposit);
                    }
                    catch (Exception e)
                    {
                        System.out.println("DepositDAOImpl delete: " + e);
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
                        session.delete(deposit);
                    }
                    catch (Exception e)
                    {
                        System.out.println("DepositDAOImpl delete: " + e);
                    }
                }
            }
        }
        else
            return;
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
                System.out.println("Exception DepositDAOImpl findBankAccountById: " + e);
            }

            if(buf_account != null)
                Singleton.getInstance().getBankAccountVector().add(buf_account);
        }

        return buf_account;
    }

    @Override
    public List<Deposit> findAll() {
        List<Deposit> deposits = new ArrayList<>();

        try
        {
            deposits.addAll((List<Deposit>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from Deposit").list());
        }
        catch (Exception e)
        {
            System.out.println("Exception DepositDAOImpl findAll: " + e);
        }

        return deposits;
    }
}
