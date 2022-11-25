package DAO.Deposit;

import Singleton.Singleton;
import models.BankAccount.BankAccount;
import models.Client.Client;
import models.Deposit.Deposit;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

public class DepositDAOImpl implements DepositDAO
{
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
            String hql = "from Deposit where storage_time::varchar ilike '%" + String.valueOf(storage_time) + "%'";

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
            String hql = "from Deposit where interest_rate::varchar ilike '%" + String.valueOf(interest_rate) + "%'";

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
        if(deposit != null)
        {
            Singleton.getInstance().getDepositVector().add(deposit);

            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction save_query = session.beginTransaction();
            session.persist(deposit);
            save_query.commit();
            session.close();
        }
        else return;
    }

    @Override
    public void update(Deposit deposit)
    {
        if(deposit != null)
        {
            Singleton.getInstance().getDepositVector().add(deposit);

            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction save_query = session.beginTransaction();
            session.merge(deposit);
            save_query.commit();
            session.close();
        }
        else return;
    }

    @Override
    public void delete(Deposit deposit)
    {
        if(deposit != null)
        {
            Singleton.getInstance().getDepositVector().add(deposit);

            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction save_query = session.beginTransaction();
            session.delete(deposit);
            save_query.commit();
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
