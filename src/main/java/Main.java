import Menu.menu;
import java.io.IOException;
import java.text.ParseException;
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

        //        BankAccountService accountService = new BankAccountService();
        //        ClientService clientService = new ClientService();
        //        DepositService depositService = new DepositService();
        //        HomeService homeService = new HomeService();
        //
        //        Client
        //                client = clientService.findClientById(1);
        //        Home home = homeService.findHomeById(1);
        //
        //        System.out.println(client.toString() + ", " + client.getHome().toString());
        //        System.out.println(home.getClients());

        //        test_select();
    }
}