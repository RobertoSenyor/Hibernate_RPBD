package util;

import models.BankAccount.BankAccount;
import models.Client.Client;
import models.Deposit.Deposit;
import models.Home.Home;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateSessionFactoryUtil
{
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil(){}

    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null)
        {
            try
            {
                File config = new File("target/hibernate.cfg.xml");
                Configuration configuration = new Configuration().configure(config);
                configuration.addAnnotatedClass(BankAccount.class);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(Deposit.class);
                configuration.addAnnotatedClass(Home.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            }
            catch (Exception e)
            {
                System.out.println("Exception" + e);
            }
        }
        return sessionFactory;
    }
}
