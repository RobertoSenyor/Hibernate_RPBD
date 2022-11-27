package DAO.BankAccount;

import Singleton.Singleton;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import models.BankAccount.BankAccount;
import models.Deposit.Deposit;
import models.Home.Home;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateSessionFactoryUtil;

public class BankAccountDAOImpl implements BankAccountDAO
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
                    size = session.createQuery("from BankAccount").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception BankAccountDAOImpl get_count: " + e);
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
                    size = session.createQuery("from BankAccount").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception BankAccountDAOImpl get_count: " + e);
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
                    size = session.createQuery("from BankAccount").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception BankAccountDAOImpl get_count: " + e);
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
                    size = session.createQuery("from BankAccount").list().size();
                }
                catch (Exception e)
                {
                    System.out.println("Exception BankAccountDAOImpl get_count: " + e);
                }
            }
        }

        return size;
    }

    @Override public BankAccount findById(int id)
    {
        Session session = null;
        BankAccount buf_bankaccounts = null;

        for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
        {
            if (id == tmp.getId())
            {
                buf_bankaccounts = tmp;
                break;
            }
        }

        if (buf_bankaccounts == null)
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
            if (buf_bankaccounts != null)
                Singleton.getInstance().getBankAccountVector().add(buf_bankaccounts);
        }

        return buf_bankaccounts;
    }

    @Override public List<BankAccount> findNoOneById(int id, int step)
    {
        Query query = null;
        List<BankAccount> accounts = new ArrayList<>();

        Singleton.getInstance().getBankAccountVector().clear();

        String hql = "from BankAccount where id >= " + id + " order by id limit " + step;

        Session session = null;

        if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
        {
            session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

            if (session.getTransaction().isActive())
            {
                Transaction transaction = session.getTransaction();

                try
                {
                    query = session.createQuery(hql, BankAccount.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception BankAccountDAOImpl find_no_one: " + e);
                }
                finally
                {
                    accounts.addAll(query.list());
                    transaction.commit();
                }
                if (!accounts.isEmpty())
                    Singleton.getInstance().getBankAccountVector().addAll(accounts);
            }
            else
            {
                Transaction transaction = session.beginTransaction();

                try
                {
                    query = session.createQuery(hql, BankAccount.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception BankAccountDAOImpl find_no_one: " + e);
                }
                finally
                {
                    accounts.addAll(query.list());
                }
                if (!accounts.isEmpty())
                    Singleton.getInstance().getBankAccountVector().addAll(accounts);
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
                    query = session.createQuery(hql, BankAccount.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception BankAccountDAOImpl find_no_one: " + e);
                }
                finally
                {
                    accounts.addAll(query.list());
                    transaction.commit();
                }
                if (!accounts.isEmpty())
                    Singleton.getInstance().getBankAccountVector().addAll(accounts);
            }
            else
            {
                Transaction transaction = session.beginTransaction();

                try
                {
                    query = session.createQuery(hql, BankAccount.class);
                }
                catch (Exception e)
                {
                    System.out.println("Exception BankAccountDAOImpl find_no_one: " + e);
                }
                finally
                {
                    accounts.addAll(query.list());
                    transaction.commit();
                }
                if (!accounts.isEmpty())
                    Singleton.getInstance().getBankAccountVector().addAll(accounts);

            }
        }

        return accounts;
    }

    @Override public List<BankAccount> findByNumber_of_account(String _number_of_account)
    {
        Query query = null;
        List<BankAccount> bankAccounts = new ArrayList<>();
        List<BankAccount> buf_bankAccounts = new ArrayList<>();

        if (bankAccounts.isEmpty())
        {
            String hql =
                "from BankAccount where number_of_account ilike '%" + _number_of_account + "%'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                    hql, BankAccount.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception BankAccountDAOImpl findByNumber_of_account: " + e);
            }
            finally
            {
                buf_bankAccounts = query.list();
                boolean find_contains = false;

                for (BankAccount tmp1 : buf_bankAccounts)
                {
                    find_contains=false;

                    for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
                    {
                        if (tmp.getNumber_of_account().contains(tmp1.getNumber_of_account()))
                            find_contains = true;
                    }

                    if (find_contains == false)
                        bankAccounts.add(tmp1);
                }
            }
            if (!bankAccounts.isEmpty())
                Singleton.getInstance().getBankAccountVector().addAll(bankAccounts);
        }

        return bankAccounts;
    }

    @Override public List<BankAccount> findByDate_open(String _date_open)
    {
        Query query = null;
        List<BankAccount> bankAccounts = new ArrayList<>();
        List<BankAccount> buf_bankAccounts = new ArrayList<>();

        if (bankAccounts.isEmpty())
        {
            String hql = "from BankAccount where date_open = '" + _date_open.toString() + "'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                    hql, BankAccount.class);
            }
            catch (Exception e)
            {
                System.out.println("Exception BankAccountDAOImpl findByDate_open: " + e);
            }
            finally
            {
                buf_bankAccounts = query.list();
                boolean find_contains = false;

                for (BankAccount tmp1 : buf_bankAccounts)
                {
                    find_contains=false;

                    for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
                    {
                        if (tmp.getDate_open().equals(tmp1.getDate_open()))
                            find_contains = true;
                    }

                    if (find_contains == false)
                        bankAccounts.add(tmp1);
                }
            }
            if (!bankAccounts.isEmpty())
                Singleton.getInstance().getBankAccountVector().addAll(bankAccounts);
        }

        return bankAccounts;
    }

    @Override public List<BankAccount> findByDate_close(String _date_close)
    {
        Query query = null;
        List<BankAccount> bankAccounts = new ArrayList<>();

        for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
        {
            if (new SimpleDateFormat("yyyy-MM-dd")
                    .format(tmp.getDate_close())
                    .contains(_date_close))
                bankAccounts.add(tmp);
        }

        if (bankAccounts.isEmpty())
        {
            String hql = "from BankAccount where date_close = '" + _date_close.toString() + "'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                    hql, BankAccount.class);
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
            if (!bankAccounts.isEmpty())
                Singleton.getInstance().getBankAccountVector().addAll(bankAccounts);
        }

        return bankAccounts;
    }

    @Override public List<BankAccount> findByMoney_sum(int money_sum)
    {
        Query query = null;
        List<BankAccount> bankAccounts = new ArrayList<>();

        for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
        {
            if (tmp.getMoney_sum() == money_sum)
                bankAccounts.add(tmp);
        }

        if (bankAccounts.isEmpty())
        {
            String hql = "from BankAccount where money_sum = '" + money_sum + "'";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                    hql, BankAccount.class);
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
            if (!bankAccounts.isEmpty())
                Singleton.getInstance().getBankAccountVector().addAll(bankAccounts);
        }

        return bankAccounts;
    }

    @Override
    public List<BankAccount> findBetweenOpenDate(String dateFrom, String dateTo)
        throws ParseException
    {
        Query query = null;
        List<BankAccount> bankAccounts = new ArrayList<>();

        for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
        {
            if (tmp.isAfterOpenDate(dateFrom) && tmp.isBeforeOpenDate(dateTo))
            {
                bankAccounts.add(tmp);
            }
        }

        if (bankAccounts.isEmpty())
        {
            String hql =
                "from BankAccount where date_open between '" + dateFrom + "' and '" + dateTo + "' ";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                    hql, BankAccount.class);
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
            if (!bankAccounts.isEmpty())
                Singleton.getInstance().getBankAccountVector().addAll(bankAccounts);
        }

        return bankAccounts;
    }

    @Override
    public List<BankAccount> findBetweenCloseDate(String dateFrom, String dateTo)
        throws ParseException
    {
        Query query = null;
        List<BankAccount> bankAccounts = new ArrayList<>();

        for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
        {
            if (tmp.isAfterCloseDate(dateFrom) && tmp.isBeforeCloseDate(dateTo))
                bankAccounts.add(tmp);
        }

        if (bankAccounts.isEmpty())
        {
            String hql = "from BankAccount where date_close between '" + dateFrom.toString() +
                "' and '" + dateTo.toString() + "' ";

            try
            {
                query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                    hql, BankAccount.class);
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
            if (!bankAccounts.isEmpty())
                Singleton.getInstance().getBankAccountVector().addAll(bankAccounts);
        }

        return bankAccounts;
    }

    @Override public void save(BankAccount bankAccount)
    {
        if (bankAccount != null)
        {
            Singleton.getInstance().getBankAccountVector().add(bankAccount);

            Session session = null;

            if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

                if (session.getTransaction().isActive())
                {
                    Transaction save_query = session.getTransaction();

                    try
                    {
                        session.persist(bankAccount);
                    }
                    catch (Exception e)
                    {
                        System.out.println("BankAccountDAOImpl save: " + e);
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
                        session.persist(bankAccount);
                    }
                    catch (Exception e)
                    {
                        System.out.println("BankAccountDAOImpl update: " + e);
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
                        session.persist(bankAccount);
                    }
                    catch (Exception e)
                    {
                        System.out.println("BankAccountDAOImpl save: " + e);
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
                        session.persist(bankAccount);
                    }
                    catch (Exception e)
                    {
                        System.out.println("BankAccountDAOImpl save: " + e);
                    }
                }
            }
        }
        else
            return;
    }

    @Override public void update(BankAccount bankAccount)
    {

        if (bankAccount != null)
        {
            Singleton.getInstance().getBankAccountVector().set(
                    Singleton.getInstance().getBankAccountVector().indexOf(bankAccount), bankAccount);

            Session session = null;

            if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

                if (session.getTransaction().isActive())
                {
                    Transaction update_query = session.getTransaction();

                    try
                    {
                        session.merge(bankAccount);
                    }
                    catch (Exception e)
                    {
                        System.out.println("BankAccountDAOImpl update: " + e);
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
                        session.merge(bankAccount);
                    }
                    catch (Exception e)
                    {
                        System.out.println("BankAccountDAOImpl update: " + e);
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
                        session.merge(bankAccount);
                    }
                    catch (Exception e)
                    {
                        System.out.println("BankAccountDAOImpl update: " + e);
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
                        session.merge(bankAccount);
                    }
                    catch (Exception e)
                    {
                        System.out.println("BankAccountDAOImpl update: " + e);
                    }
                }
            }
        }
        else
            return;
    }

    @Override public void delete(BankAccount bankAccount)
    {
        if (bankAccount != null)
        {
            Singleton.getInstance().getBankAccountVector().remove(bankAccount);

            Session session = null;

            if (HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession().isOpen())
            {
                session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();

                if (session.getTransaction().isActive())
                {
                    Transaction delete_query = session.getTransaction();

                    try
                    {
                        session.delete(bankAccount);
                    }
                    catch (Exception e)
                    {
                        System.out.println("BankAccountDAOImpl delete: " + e);
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
                        session.delete(bankAccount);
                    }
                    catch (Exception e)
                    {
                        System.out.println("BankAccountDAOImpl delete: " + e);
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
                        session.delete(bankAccount);
                    }
                    catch (Exception e)
                    {
                        System.out.println("BankAccountDAOImpl delete: " + e);
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
                        session.delete(bankAccount);
                    }
                    catch (Exception e)
                    {
                        System.out.println("BankAccountDAOImpl delete: " + e);
                    }
                }
            }
        }
        else
            return;
    }

    @Override public List<BankAccount> findAll()
    {
        List<BankAccount> accounts = new ArrayList<>();

        try
        {
            accounts.addAll((List<BankAccount>)HibernateSessionFactoryUtil.getSessionFactory()
                                .openSession()
                                .createQuery("from BankAccount")
                                .list());
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
