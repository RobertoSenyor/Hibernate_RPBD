package DAO.BankAccount;

import Singleton.Singleton;
import models.BankAccount.BankAccount;
import models.Client.Client;
import models.Deposit.Deposit;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BankAccountDAOImpl implements BankAccountDAO
{
    @Override
    public BankAccount findById(int id)
    {
        Session session = null;
        BankAccount buf_bankaccounts = null;

        for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
        {
            if(id == tmp.getId())
            {
                buf_bankaccounts = tmp;
                break;
            }
        }

        if(buf_bankaccounts==null)
        {
            try
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                buf_bankaccounts = session.get(BankAccount.class, id);
            }
            catch (Exception e)
            {
                System.out.println("Exception BankAccountDAOImpl findById: " + e);
            }
            finally
            {
//                session.close();
            }
            if(buf_bankaccounts != null)
                Singleton.getInstance().getBankAccountVector().add(buf_bankaccounts);

            return buf_bankaccounts;
        }
        return buf_bankaccounts;
    }

    @Override
    public List<BankAccount> findByNumber_of_account(String _number_of_account)
    {
        Query query = null;
        List<BankAccount> bankAccounts = new ArrayList<>();

        for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
        {
            if(tmp.getNumber_of_account().toLowerCase().contains(_number_of_account.toLowerCase()))
                bankAccounts.add(tmp);
        }

        if(bankAccounts.isEmpty())
        {
            String hql = "from BankAccount where number_of_account ilike '%" + _number_of_account + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, BankAccount.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception BankAccountDAOImpl findByNumber_of_account: " + e);
            }
            finally
            {
                bankAccounts.addAll(query.list());
//                HibernateSessionFactoryUtil.getSessionFactory().close();
            }
            if(!bankAccounts.isEmpty())
                Singleton.getInstance().getBankAccountVector().addAll(bankAccounts);

            return bankAccounts;
        }

        return bankAccounts;
    }

    @Override
    public List<BankAccount> findByDate_open(String _date_open)
    {
        Query query = null;
        List<BankAccount> bankAccounts = new ArrayList<>();

        for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
        {
            if(new SimpleDateFormat("yyyy-MM-dd").format(tmp.getDate_open()).contains(_date_open))
                bankAccounts.add(tmp);
        }

        if(bankAccounts.isEmpty())
        {
            String hql = "from BankAccount where date_open = '" + _date_open.toString() + "'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, BankAccount.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception BankAccountDAOImpl findByDate_open: " + e);
            }
            finally
            {
                bankAccounts.addAll(query.list());
//                HibernateSessionFactoryUtil.getSessionFactory().close();
            }
            if(!bankAccounts.isEmpty())
            Singleton.getInstance().getBankAccountVector().addAll(bankAccounts);

            return bankAccounts;
        }

        return bankAccounts;
    }

    @Override
    public List<BankAccount> findByDate_close(String _date_close) 
    {
        Query query = null;
        List<BankAccount> bankAccounts = new ArrayList<>();

        for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
        {
            if(new SimpleDateFormat("yyyy-MM-dd").format(tmp.getDate_close()).contains(_date_close))
                bankAccounts.add(tmp);
        }

        if(bankAccounts.isEmpty())
        {
            String hql = "from BankAccount where date_close = '" + _date_close.toString() + "'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, BankAccount.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception BankAccountDAOImpl findByDate_close: " + e);
            }
            finally
            {
                bankAccounts.addAll(query.list());
//                HibernateSessionFactoryUtil.getSessionFactory().close();
            }
            if(!bankAccounts.isEmpty())
            Singleton.getInstance().getBankAccountVector().addAll(bankAccounts);

            return bankAccounts;
        }

        return bankAccounts;
    }

    @Override
    public List<BankAccount> findByMoney_sum(int money_sum) 
    {
        Query query = null;
        List<BankAccount> bankAccounts = new ArrayList<>();

        for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
        {
            if(tmp.getMoney_sum() == money_sum)
                bankAccounts.add(tmp);
        }

        if(bankAccounts.isEmpty())
        {
            String hql = "from BankAccount where money_sum = '" + money_sum + "'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, BankAccount.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception BankAccountDAOImpl findByMoney_sum: " + e);
            }
            finally
            {
                bankAccounts.addAll(query.list());
//                HibernateSessionFactoryUtil.getSessionFactory().close();
            }
            if(!bankAccounts.isEmpty())
            Singleton.getInstance().getBankAccountVector().addAll(bankAccounts);

            return bankAccounts;
        }

        return bankAccounts;
    }

    @Override
    public List<BankAccount> findBetweenOpenDate(String dateFrom, String dateTo) throws ParseException
    {
        Query query = null;
        List<BankAccount> bankAccounts = new ArrayList<>();

        for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
        {
            if(tmp.isAfterOpenDate(dateFrom) && tmp.isBeforeOpenDate(dateTo))
            {
                bankAccounts.add(tmp);
            }
        }

        if (bankAccounts.isEmpty())
        {
            String hql = "from BankAccount where date_open between '" + dateFrom + "' and '" + dateTo + "' ";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, BankAccount.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception BankAccountDAOImpl findBetweenOpenDate: " + e);
            }
            finally
            {
                bankAccounts.addAll(query.list());
//                HibernateSessionFactoryUtil.getSessionFactory().close();
            }
            if(!bankAccounts.isEmpty())
            Singleton.getInstance().getBankAccountVector().addAll(bankAccounts);

            return bankAccounts;
        }

        return bankAccounts;
    }

    @Override
    public List<BankAccount> findBetweenCloseDate(String dateFrom, String dateTo) throws ParseException
    {
        Query query = null;
        List<BankAccount> bankAccounts = new ArrayList<>();

        for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
        {
            if(tmp.isAfterCloseDate(dateFrom) && tmp.isBeforeCloseDate(dateTo))
                bankAccounts.add(tmp);
        }

        if (bankAccounts.isEmpty())
        {
            String hql = "from BankAccount where date_close between '" + dateFrom.toString() + "' and '" + dateTo.toString() + "' ";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().
                        createQuery(hql, BankAccount.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception BankAccountDAOImpl findBetweenCloseDate: " + e);
            }
            finally
            {
                bankAccounts.addAll(query.list());
//                HibernateSessionFactoryUtil.getSessionFactory().close();
            }
            if(!bankAccounts.isEmpty())
            Singleton.getInstance().getBankAccountVector().addAll(bankAccounts);

            return bankAccounts;
        }

        return bankAccounts;
    }

    @Override
    public void save(BankAccount bankAccount)
    {
        if (bankAccount != null)
        {
            Singleton.getInstance().getBankAccountVector().add(bankAccount);

            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction save_query = session.beginTransaction();
            session.persist(bankAccount);
            save_query.commit();
            session.close();
        }
        else return;
    }

    @Override
    public void update(BankAccount bankAccount)
    {
        if (bankAccount != null)
        {
            Singleton.getInstance().getBankAccountVector().set(Singleton.getInstance().getBankAccountVector().indexOf(bankAccount), bankAccount);

            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction update_query = session.beginTransaction();
            session.merge(bankAccount);
            update_query.commit();
            session.close();
        }
        else
            return;
    }

    @Override
    public void delete(BankAccount bankAccount)
    {
        if (bankAccount != null)
        {
            Singleton.getInstance().getBankAccountVector().remove(bankAccount);

            Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
            Transaction delete_query = session.beginTransaction();
            session.delete(bankAccount);
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
                System.out.println("Exception BankAccountDAOImpl findClientById: " + e);
            }
            finally
            {
                session.close();
            }
            if(buf_client != null)
            Singleton.getInstance().getClientVector().add(buf_client);

            return buf_client;
        }
        return buf_client;
    }

    @Override
    public Deposit findDepositById(int id)
    {
        Session session = null;
        Deposit buf_deposit = null;

        for (Deposit tmp : Singleton.getInstance().getDepositVector())
        {
            if(id == tmp.getId())
            {
                buf_deposit = tmp;
                break;
            }
        }

        if(buf_deposit == null)
        {
            try
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
                buf_deposit = session.get(Deposit.class, id);
            }
            catch (Exception e)
            {
                System.out.println("Exception BankAccountDAOImpl findDepositById: " + e);
            }
            finally
            {
                session.close();
            }
            if(buf_deposit != null)
            Singleton.getInstance().getDepositVector().add(buf_deposit);

            return buf_deposit;
        }
        return buf_deposit;
    }

    @Override
    public List<BankAccount> findAll()
    {
        List<BankAccount> accounts = new ArrayList<>();

        try
        {
            accounts.addAll((List<BankAccount>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("from BankAccount").list());
        }
        catch (Exception e)
        {
            System.out.println("Exception BankAccountDAOImpl findAll: " + e);
        }
        finally
        {
//            HibernateSessionFactoryUtil.getSessionFactory().close();
        }

        return accounts;
    }
}
