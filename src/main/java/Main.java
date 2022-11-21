import DAO.BankAccount.BankAccountDAOImpl;
import Singleton.Singleton;
import models.BankAccount.BankAccount;
import models.Home.Home;
import service.BankAccount.BankAccountService;
import service.Home.HomeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main
{
    public static void main(String[] args) throws ParseException
    {
        BankAccountService bankAccountService = new BankAccountService();

        bankAccountService.findBankAccountByDate_open("2010-07-04");
        bankAccountService.findBankAccountById(1);
        bankAccountService.findBankAccountById(1);
        bankAccountService.findBankAccountById(1);
        bankAccountService.findBankAccountById(26);
//        bankAccountService.deleteBankAccount(account);
        bankAccountService.findBankAccountByDate_open("2002-12-10");

        System.out.println(Singleton.getInstance().getBankAccountVector());

//------------------------------------------------------------------------------------------------------------

//        BankAccount bankAccount = new BankAccountService().findBankAccountById(1);
//
//        System.out.println(bankAccount.isAfterOpenDate("2010-01-01")&&bankAccount.isBeforeOpenDate("2030-01-10"));

//------------------------------------------------------------------------------------------------------------

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