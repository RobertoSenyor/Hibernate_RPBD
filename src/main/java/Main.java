import Menu.menu;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import models.BankAccount.BankAccount;
import models.Client.Client;
import models.Home.Home;
import service.BankAccount.BankAccountService;
import service.Client.ClientService;
import service.Deposit.DepositService;
import service.Home.HomeService;

public class Main
{
    public static void test_select()
    {
        HomeService homeService = new HomeService();

        Home home = homeService.findHomeById(1);
        double startTime = System.nanoTime();
        home = homeService.findHomeById(2);
        double endTime = System.nanoTime();
        double duration = (endTime - startTime) / 1000000;

        System.out.println("select1: " + duration);

        Home home1 = homeService.findHomeById(1);
        startTime = System.nanoTime();
        home1 = homeService.findHomeById(2);
        endTime = System.nanoTime();
        duration = (endTime - startTime) / 1000000;

        System.out.println("select2: " + duration);
    }

    public static void main(String[] args) throws ParseException, IOException
    {
        new menu();
    }
}