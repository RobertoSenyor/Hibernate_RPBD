import DAO.BankAccount.BankAccountDAOImpl;
import Singleton.Singleton;
import models.Home.Home;
import service.Home.HomeService;

public class Main
{
    public static void main(String[] args)
    {

        String str1 = "a";
        String str2 = "ABCD";

        System.out.println(str2.toLowerCase().contains(str1.toLowerCase()));


//        HomeService homeService = new HomeService();
//
//        Home home = homeService.findHomeById(1);
//        double startTime = System.nanoTime();
//        home = homeService.findHomeById(2);
//        double endTime = System.nanoTime();
//        double duration = (endTime - startTime)/1000000;
//
//        System.out.println("select1: " + duration);
//
//        Home home1 = homeService.findHomeById(1);
//        startTime = System.nanoTime();
//        home1 = homeService.findHomeById(2);
//        endTime = System.nanoTime();
//        duration = (endTime - startTime)/1000000;
//
//        System.out.println("select2: " + duration);

    }
}