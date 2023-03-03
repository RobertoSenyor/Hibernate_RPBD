package Menu;

import DAO.Client.ClientDAO;
import Singleton.Singleton;
import jakarta.persistence.Table;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import models.BankAccount.BankAccount;
import models.Client.Client;
import models.Deposit.Deposit;
import models.Home.Home;
import org.hibernate.query.Query;
import service.BankAccount.BankAccountService;
import service.Client.ClientService;
import service.Deposit.DepositService;
import service.Home.HomeService;
import util.HibernateSessionFactoryUtil;

public class menu
{
    private static String clear_console =
        "\n\n\n\n-----------------------------------------\n\n\n\n";
    private static Scanner input_data = new Scanner(System.in);

    private static int bankAccount_id = 1;
    private static int bankAccount_id_step = 5;
    private static int bankAccount_page = 1;
    private static BankAccountService accountService = new BankAccountService();

    private static int client_id = 1;
    private static int client_id_step = 5;
    private static int client_page = 1;
    private static ClientService clientService = new ClientService();

    private static int deposit_id = 1;
    private static int deposit_id_step = 5;
    private static int deposit_page = 1;
    private static DepositService depositService = new DepositService();

    private static int home_id = 1;
    private static int home_id_step = 5;
    private static int home_page = 1;
    private static HomeService homeService = new HomeService();

    static private void interaction_with_BankAccount() throws ParseException
    {
        if (Singleton.getInstance().getBankAccountVector().isEmpty())
            accountService.findNoOneBankAccountById(bankAccount_id, bankAccount_id_step);

        boolean is_actionable_menu = true;

        String choose_menu_point = null;

        while (is_actionable_menu)
        {
            System.out.println(clear_console);

            System.out.format(
                "%25s%19s%13s%15s%11s%12s%19s\n", "number_of_account", "date_open", "date_close",
                "money_sum", "name", "surname", "name_of_deposit");

            int i = 1;

            for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
            {
                System.out
                    .format(
                        i + ") %1s%13s%12s%19s\n", tmp.toString(), tmp.getClient().getName(),
                        tmp.getClient().getSurname(), tmp.getDeposit().getName_of_deposit())
                    .toString();
                i++;
            }

            System.out.println("\nBankAccount page {" + bankAccount_page + "};");

            String output_menu_info = "\n\n"
                + "1) Show next " + bankAccount_id_step + " nodes\n"
                + "2) Show prev " + bankAccount_id_step + " nodes\n\n"
                + "3) Insert node\n"
                + "4) Update node\n"
                + "5) Delete node\n\n"
                + "6) Find node\n\n"
                + "q) To Exit\n\n"
                + "Enter number for choosing: ";

            System.out.println(output_menu_info);

            choose_menu_point = input_data.nextLine();

            switch (choose_menu_point)
            {
                /**
                 * next 5
                 */
                case "1":
                {

                    if (bankAccount_id + bankAccount_id_step - 1 <
                        accountService.get_count_nodes_BankAccount())
                    {
                        bankAccount_id += bankAccount_id_step;
                        bankAccount_page++;
                        accountService.findNoOneBankAccountById(
                            bankAccount_id, bankAccount_id_step);
                    }
                    break;
                }
                /**
                 * prev 5
                 */
                case "2":
                {
                    if (bankAccount_id - bankAccount_id_step > 0)
                    {
                        bankAccount_id -= bankAccount_id_step;
                        bankAccount_page--;
                        accountService.findNoOneBankAccountById(
                            bankAccount_id, bankAccount_id_step);
                    }
                    break;
                }
                /**
                 * insert node
                 */
                case "3":
                {
                    System.out.println(clear_console);

                    System.out.format(
                        "%25s%19s%13s%15s%11s%12s%19s\n", "number_of_account", "date_open",
                        "date_close", "money_sum", "name", "surname", "name_of_deposit");

                    i = 1;

                    for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
                    {
                        System.out
                            .format(
                                i + ") %1s%13s%12s%19s\n", tmp.toString(),
                                tmp.getClient().getName(), tmp.getClient().getSurname(),
                                tmp.getDeposit().getName_of_deposit())
                            .toString();
                        i++;
                    }

                    System.out.println("\nany key) Continue INSERT");
                    System.out.println("\nq) To Exit");

                    String choosen_node = input_data.nextLine();

                    if (choosen_node.toLowerCase().contains("q"))
                    {
                        break;
                    }

                    String number_of_account, name, surname;
                    String date_open = "2000-01-01";
                    String date_close = "2000-01-02";
                    String name_of_deposit = null;
                    int money_sum;
                    boolean ok_input = false;

                    System.out.println("\nEnter number_of_account");
                    number_of_account = input_data.nextLine();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat.setLenient(false);

                    while (!ok_input)
                    {
                        System.out.println("\nEnter date_open (in format yyyy-MM-dd)");
                        date_open = input_data.nextLine();

                        try
                        {
                            dateFormat.parse(date_open);
                            ok_input = true;
                        }
                        catch (Exception e)
                        {
                            System.out.println(
                                    "Exception menu.java interaction_with_BankAccount (date_open): " + e);
                            System.out.println("Setting define value date_open: 2000-01-01");
                        }
                    }

                    ok_input = false;
                    while (!ok_input)
                    {
                        System.out.println(
                                "\nEnter date_close (in format yyyy-MM-dd more than date_open)");
                        date_close = input_data.nextLine();

                        try
                        {
                            dateFormat.parse(date_close);
                            ok_input = true;
                        }
                        catch (Exception e)
                        {
                            System.out.println(
                                    "Exception menu.java interaction_with_BankAccount (date_close): " + e);
                            System.out.println("Setting define value date_close: 2000-01-02");
                        }
                    }

                    ok_input = false;
                    while (!ok_input)
                    {
                        System.out.println("\nEnter money_sum");

                        try
                        {
                            money_sum = Integer.parseInt(input_data.nextLine());
                            if (money_sum >= 1)
                                ok_input = true;
                        }
                        catch (Exception e)
                        {
                            System.out.println("Exception menu.java insert BankAccount: " + e);
                        }
                    }

                    BankAccount buf_account =
                        new BankAccount(number_of_account, date_open, date_close, 1);

                    List<Client> buf_clients = null;

                    ok_input = false;
                    while (!ok_input)
                    {
                        System.out.println("\nEnter name client");
                        name = input_data.nextLine();

                        System.out.println("\nEnter surname client");
                        surname = input_data.nextLine();

                        buf_clients =
                                clientService.findClientByName_Surname(name, surname);

                        if (!buf_clients.isEmpty())
                        {
                            ok_input = true;

                            System.out.println("\n Select client, which you want");
                            int j = 1;

                            for (Client tmp : buf_clients)
                            {
                                System.out.println(j + ") " + tmp.toString());
                                j++;
                            }

                            System.out.println("Enter logic position");
                            int buf_log_pos = Integer.parseInt(input_data.nextLine());

                            while (buf_log_pos < 1 || buf_log_pos > buf_clients.size())
                            {
                                System.out.println("Enter logic position");
                                buf_log_pos = Integer.parseInt(input_data.nextLine());
                            }

                            buf_account.setClient(buf_clients.get(buf_log_pos - 1));
                        }
                        else
                            System.out.println("Dont finded nodes have this words");
                    }

                    List<Deposit> buf_deposits = null;

                    ok_input = false;
                    while (!ok_input)
                    {
                        System.out.println("\nEnter name_of_deposit");
                        name_of_deposit = input_data.nextLine();

                        buf_deposits = depositService.findDepositByName_of_deposit(name_of_deposit);

                        if (!buf_deposits.isEmpty())
                        {
                            ok_input = true;

                            System.out.println("\n Select deposit, which you want");
                            int j = 1;

                            for (Deposit tmp : buf_deposits)
                            {
                                System.out.println(j + ") " + tmp.toString());
                                j++;
                            }

                            System.out.println("Enter logic position");
                            int buf_log_pos = Integer.parseInt(input_data.nextLine());

                            while (buf_log_pos < 0 || buf_log_pos > buf_deposits.size())
                            {
                                System.out.println("Enter logic position");
                                buf_log_pos = Integer.parseInt(input_data.nextLine());
                            }

                            buf_account.setDeposit(buf_deposits.get(buf_log_pos - 1));
                        }
                        else
                            System.out.println("Dont finded nodes have this words");

                    }

                    accountService.saveBankAccount(buf_account);

                    break;
                }
                /**
                 * update node
                 */
                case "4":
                {
                    System.out.println(clear_console);

                    System.out.format(
                        "%25s%19s%13s%15s%11s%12s%19s\n", "number_of_account", "date_open",
                        "date_close", "money_sum", "name", "surname", "name_of_deposit");

                    i = 1;

                    for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
                    {
                        System.out
                            .format(
                                i + ") %1s%13s%12s%19s\n", tmp.toString(),
                                tmp.getClient().getName(), tmp.getClient().getSurname(),
                                tmp.getDeposit().getName_of_deposit())
                            .toString();
                        i++;
                    }

                    System.out.println("\nChoose node on # for UPDATE");
                    System.out.println("\nany number) To UPDATE node");
                    System.out.println("\nq) To Exit");

                    String choosen_node = input_data.nextLine();

                    if (choosen_node.toLowerCase().contains("q") || choosen_node.isEmpty() ||
                        Integer.parseInt(choosen_node) > i - 1 ||
                        Integer.parseInt(choosen_node) < 1)
                    {
                        break;
                    }

                    boolean is_update_active = true;

                    while (is_update_active)
                    {
                        System.out.println("1) Update number_of_account");
                        System.out.println("2) Update date_open");
                        System.out.println("3) Update date_close");
                        System.out.println("4) Update money_sum");
                        System.out.println("5) Update name");
                        System.out.println("6) Update surname");
                        System.out.println("7) Update name_of_deposit");
                        System.out.println("8) Update all");
                        System.out.println("\nq) To Exit");

                        String update_interface = input_data.nextLine();

                        switch (update_interface)
                        {
                            case "1":
                            {
                                System.out.println("\nEnter number_of_account");
                                String number_of_account = input_data.nextLine();

                                Singleton.getInstance()
                                    .getBankAccountVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setNumber_of_account(number_of_account);
                                accountService.updateBankAccount(
                                    Singleton.getInstance().getBankAccountVector().get(
                                        Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "2":
                            {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                dateFormat.setLenient(false);
                                String date_open = "2000-01-01";
                                boolean ok_input = false;

                                while (!ok_input)
                                {
                                    System.out.println("\nEnter date_open (in format yyyy-MM-dd)");
                                    date_open = input_data.nextLine();

                                    try
                                    {
                                        dateFormat.parse(date_open);
                                        ok_input = true;
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println(
                                                "Exception menu.java interaction_with_BankAccount (date_open): " + e);
                                        System.out.println("Setting define value date_open: 2000-01-01");
                                    }
                                }

                                Singleton.getInstance()
                                    .getBankAccountVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setDate_open(date_open);
                                accountService.updateBankAccount(
                                    Singleton.getInstance().getBankAccountVector().get(
                                        Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "3":
                            {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                dateFormat.setLenient(false);
                                String date_close = "2000-01-02";
                                boolean ok_input = false;

                                while (!ok_input)
                                {
                                    System.out.println("\nEnter date_close (in format yyyy-MM-dd)");
                                    date_close = input_data.nextLine();

                                    try
                                    {
                                        dateFormat.parse(date_close);
                                        ok_input = true;
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println(
                                                "Exception menu.java interaction_with_BankAccount (date_close): " + e);
                                        System.out.println("Setting define value date_close: 2000-01-02");
                                    }
                                }

                                Singleton.getInstance()
                                        .getBankAccountVector()
                                        .get(Integer.parseInt(choosen_node) - 1)
                                        .setDate_close(date_close);
                                accountService.updateBankAccount(
                                        Singleton.getInstance().getBankAccountVector().get(
                                                Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "4":
                            {
                                int money_sum = 1;
                                boolean ok_input = false;

                                while (!ok_input)
                                {
                                    System.out.println("\nEnter money_sum");

                                    try
                                    {
                                        money_sum = Integer.parseInt(input_data.nextLine());
                                        if (money_sum >= 1)
                                            ok_input = true;
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println("Exception menu.java insert BankAccount: " + e);
                                    }
                                }

                                Singleton.getInstance()
                                    .getBankAccountVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setMoney_sum(money_sum);
                                accountService.updateBankAccount(
                                    Singleton.getInstance().getBankAccountVector().get(
                                        Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "5":
                            {
                                System.out.println("\nEnter name client");
                                String name = input_data.nextLine();

                                List<Client> buf_clients = clientService.findClientByName(name);

                                if (buf_clients.size() > 1)
                                {
                                    System.out.println("\n Select client, which you want");
                                    int j = 1;

                                    for (Client tmp : buf_clients)
                                    {
                                        System.out.println(j + ") " + tmp.toString());
                                        j++;
                                    }

                                    System.out.println("Enter logic position");
                                    int buf_log_pos = Integer.parseInt(input_data.nextLine());

                                    while (buf_log_pos < 1 || buf_log_pos > buf_clients.size())
                                    {
                                        System.out.println("Enter logic position");
                                        buf_log_pos = Integer.parseInt(input_data.nextLine());
                                    }

                                    Singleton.getInstance()
                                        .getBankAccountVector()
                                        .get(Integer.parseInt(choosen_node) - 1)
                                        .setClient(buf_clients.get(buf_log_pos - 1));
                                }
                                else
                                {
                                    Singleton.getInstance()
                                        .getBankAccountVector()
                                        .get(
                                            Singleton.getInstance().getBankAccountVector().size() -
                                            1)
                                        .setClient(buf_clients.get(buf_clients.size() - 1));
                                }
                                break;
                            }
                            case "6":
                            {
                                System.out.println("\nEnter surname client");
                                String surname = input_data.nextLine();

                                List<Client> buf_clients =
                                    clientService.findClientBySurame(surname);

                                if (buf_clients.size() > 1)
                                {
                                    System.out.println("\n Select client, which you want");
                                    int j = 1;

                                    for (Client tmp : buf_clients)
                                    {
                                        System.out.println(j + ") " + tmp.toString());
                                        j++;
                                    }

                                    System.out.println("Enter logic position");
                                    int buf_log_pos = Integer.parseInt(input_data.nextLine());

                                    while (buf_log_pos < 1 || buf_log_pos > buf_clients.size())
                                    {
                                        System.out.println("Enter logic position");
                                        buf_log_pos = Integer.parseInt(input_data.nextLine());
                                    }

                                    Singleton.getInstance()
                                        .getBankAccountVector()
                                        .get(Integer.parseInt(choosen_node) - 1)
                                        .setClient(buf_clients.get(buf_log_pos - 1));
                                }
                                else
                                {
                                    Singleton.getInstance()
                                        .getBankAccountVector()
                                        .get(
                                            Singleton.getInstance().getBankAccountVector().size() -
                                            1)
                                        .setClient(buf_clients.get(buf_clients.size() - 1));
                                }
                                break;
                            }
                            case "7":
                            {
                                System.out.println("\nEnter name_of_deposit");
                                String name_of_deposit = input_data.nextLine();

                                List<Deposit> buf_deposits =
                                    depositService.findDepositByName_of_deposit(name_of_deposit);

                                int buf_log_pos = 0;
                                if (buf_deposits.size() > 1)
                                {
                                    System.out.println("\n Select deposit, which you want");
                                    int j = 1;

                                    for (Deposit tmp : buf_deposits)
                                    {
                                        System.out.println(j + ") " + tmp.toString());
                                        j++;
                                    }

                                    System.out.println("Enter logic position");
                                    buf_log_pos = Integer.parseInt(input_data.nextLine());

                                    while (buf_log_pos < 1 || buf_log_pos > buf_deposits.size())
                                    {
                                        System.out.println("Enter logic position");
                                        buf_log_pos = Integer.parseInt(input_data.nextLine());
                                    }

                                    Singleton.getInstance()
                                        .getBankAccountVector()
                                        .get(Integer.parseInt(choosen_node) - 1)
                                        .setDeposit(buf_deposits.get(buf_log_pos - 1));
                                }
                                else
                                {
                                    Singleton.getInstance()
                                        .getBankAccountVector()
                                        .get(
                                            Singleton.getInstance().getBankAccountVector().size() -
                                            1)
                                        .setDeposit(buf_deposits.get(buf_log_pos - 1));
                                }
                                break;
                            }
                            case "8":
                            {
                                System.out.println("\nEnter number_of_account");
                                String number_of_account = input_data.nextLine();

                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                dateFormat.setLenient(false);
                                boolean ok_input = false;

                                String date_open = "2000-01-01";

                                while (!ok_input)
                                {
                                    System.out.println("\nEnter date_open (in format yyyy-MM-dd)");
                                    date_open = input_data.nextLine();

                                    try
                                    {
                                        dateFormat.parse(date_open);
                                        ok_input = true;
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println(
                                                "Exception menu.java interaction_with_BankAccount (date_open): " + e);
                                        System.out.println("Setting define value date_open: 2000-01-01");
                                    }
                                }

                                String date_close = "2000-01-02";

                                ok_input=false;
                                while (!ok_input)
                                {
                                    System.out.println("\nEnter date_close (in format yyyy-MM-dd)");
                                    date_close = input_data.nextLine();

                                    try
                                    {
                                        dateFormat.parse(date_close);
                                        ok_input = true;
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println(
                                                "Exception menu.java interaction_with_BankAccount (date_close): " + e);
                                        System.out.println("Setting define value date_close: 2000-01-02");
                                    }
                                }

                                System.out.println("\nEnter money_sum");
                                int money_sum = Integer.parseInt(input_data.nextLine());

                                while (!Singleton.getInstance()
                                            .getBankAccountVector()
                                            .get(Integer.parseInt(choosen_node) - 1)
                                            .setMoney_sum(money_sum))
                                {
                                    System.out.println("\nEnter money_sum");
                                    money_sum = Integer.parseInt(input_data.nextLine());
                                }

                                System.out.println("\nEnter name client");
                                String name = input_data.nextLine();

                                List<Client> buf_clients = clientService.findClientByName(name);

                                if (buf_clients.size() > 1)
                                {
                                    System.out.println("\n Select client, which you want");
                                    int j = 1;

                                    for (Client tmp : buf_clients)
                                    {
                                        System.out.println(j + ") " + tmp.toString());
                                        j++;
                                    }

                                    System.out.println("Enter logic position");
                                    int buf_log_pos = Integer.parseInt(input_data.nextLine());

                                    while (buf_log_pos < 1 || buf_log_pos > buf_clients.size())
                                    {
                                        System.out.println("Enter logic position");
                                        buf_log_pos = Integer.parseInt(input_data.nextLine());
                                    }

                                    Singleton.getInstance()
                                        .getBankAccountVector()
                                        .get(Integer.parseInt(choosen_node) - 1)
                                        .setClient(buf_clients.get(buf_log_pos - 1));
                                }
                                else
                                {
                                    Singleton.getInstance()
                                        .getBankAccountVector()
                                        .get(
                                            Singleton.getInstance().getBankAccountVector().size() -
                                            1)
                                        .setClient(buf_clients.get(buf_clients.size() - 1));
                                }

                                System.out.println("\nEnter surname client");
                                String surname = input_data.nextLine();

                                buf_clients.clear();
                                buf_clients = clientService.findClientBySurame(surname);

                                if (buf_clients.size() > 1)
                                {
                                    System.out.println("\n Select client, which you want");
                                    int j = 1;

                                    for (Client tmp : buf_clients)
                                    {
                                        System.out.println(j + ") " + tmp.toString());
                                        j++;
                                    }

                                    System.out.println("Enter logic position");
                                    int buf_log_pos = Integer.parseInt(input_data.nextLine());

                                    while (buf_log_pos < 1 || buf_log_pos > buf_clients.size())
                                    {
                                        System.out.println("Enter logic position");
                                        buf_log_pos = Integer.parseInt(input_data.nextLine());
                                    }

                                    Singleton.getInstance()
                                        .getBankAccountVector()
                                        .get(Integer.parseInt(choosen_node) - 1)
                                        .setClient(buf_clients.get(buf_log_pos - 1));
                                }
                                else
                                {
                                    Singleton.getInstance()
                                        .getBankAccountVector()
                                        .get(
                                            Singleton.getInstance().getBankAccountVector().size() -
                                            1)
                                        .setClient(buf_clients.get(buf_clients.size() - 1));
                                }

                                System.out.println("\nEnter name_of_deposit");
                                String name_of_deposit = input_data.nextLine();

                                List<Deposit> buf_deposits =
                                    depositService.findDepositByName_of_deposit(name_of_deposit);

                                int buf_log_pos = 0;
                                if (buf_deposits.size() > 1)
                                {
                                    System.out.println("\n Select deposit, which you want");
                                    int j = 1;

                                    for (Deposit tmp : buf_deposits)
                                    {
                                        System.out.println(j + ") " + tmp.toString());
                                        j++;
                                    }

                                    System.out.println("Enter logic position");
                                    buf_log_pos = Integer.parseInt(input_data.nextLine());

                                    while (buf_log_pos < 1 || buf_log_pos > buf_deposits.size())
                                    {
                                        System.out.println("Enter logic position");
                                        buf_log_pos = Integer.parseInt(input_data.nextLine());
                                    }

                                    Singleton.getInstance()
                                        .getBankAccountVector()
                                        .get(Integer.parseInt(choosen_node) - 1)
                                        .setDeposit(buf_deposits.get(buf_log_pos - 1));
                                }
                                else
                                {
                                    Singleton.getInstance()
                                        .getBankAccountVector()
                                        .get(
                                            Singleton.getInstance().getBankAccountVector().size() -
                                            1)
                                        .setDeposit(buf_deposits.get(buf_log_pos - 1));
                                }

                                Singleton.getInstance()
                                    .getBankAccountVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setNumber_of_account(number_of_account);
                                Singleton.getInstance()
                                    .getBankAccountVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setDate_open(date_open);
                                Singleton.getInstance()
                                    .getBankAccountVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setDate_close(date_close);

                                accountService.updateBankAccount(
                                    Singleton.getInstance().getBankAccountVector().get(
                                        Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "q":
                            {
                                System.out.println(
                                    "End UPDATE BankAccount nodes! Press [Enter] for continue.");
                                input_data.nextLine();
                                is_update_active = false;
                                break;
                            }
                            default:
                            {
                                System.out.println(
                                    "Wrong entered value, press [Enter] for try again!");
                                input_data.nextLine();
                                break;
                            }
                        }
                    }

                    break;
                }
                /**
                 * delete node
                 */
                case "5":
                {
                    System.out.println(clear_console);

                    System.out.format(
                        "%25s%19s%13s%15s\n", "number_of_account", "date_open", "date_close",
                        "money_sum");

                    i = 1;
                    for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
                    {
                        System.out.println(i + ") " + tmp.toString());
                        i++;
                    }

                    System.out.println("\nChoose node on # for DELETE");
                    System.out.println("\nany number) To DELETE node");
                    System.out.println("\nq) To Exit");

                    String choosen_node = input_data.nextLine();

                    if (choosen_node.toLowerCase().contains("q") || choosen_node.isEmpty() ||
                        Integer.parseInt(choosen_node) > i - 1 ||
                        Integer.parseInt(choosen_node) < 1)
                    {
                        break;
                    }

                    accountService.deleteBankAccount(
                        Singleton.getInstance().getBankAccountVector().get(
                            Integer.parseInt(choosen_node) - 1));
                    break;
                }
                /**
                 * find by {cloumn}
                 */
                case "6":
                {
                    System.out.println(clear_console);

                    System.out.println("1) Find all by number_of_account");
                    System.out.println("2) Find all by date_open");
                    System.out.println("3) Find all by date_close");
                    System.out.println("4) Find between date_open");
                    System.out.println("5) Find between date_close");
                    System.out.println("6) Find all by money_sum");
                    System.out.println("7) Find by client_name");
                    System.out.println("8) Find by client_surname");
                    System.out.println("9) Find by name of_deposit");
                    System.out.println("\nq) To Exit");

                    String find_interface = input_data.nextLine();

                    switch (find_interface)
                    {
                        case "1":
                        {
                            System.out.println("\nEnter number_of_account");
                            String number_of_account = input_data.nextLine();

                            System.out.println(clear_console);

                            System.out.format(
                                "%25s%19s%13s%15s%11s%12s%19s\n", "number_of_account", "date_open",
                                "date_close", "money_sum", "name", "surname", "name_of_deposit");

                            for (BankAccount tmp :
                                 accountService.findBankAccountByNumber_of_account(
                                     number_of_account))
                            {
                                System.out.println(tmp.toString());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "2":
                        {
                            String date_open = "2000-01-01";
                            boolean ok_input = false;
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            dateFormat.setLenient(false);

                            System.out.println(clear_console);

                            while (!ok_input)
                            {
                                System.out.println("\nEnter date_open (in format yyyy-MM-dd)");
                                date_open = input_data.nextLine();

                                try
                                {
                                    dateFormat.parse(date_open);
                                    ok_input = true;
                                }
                                catch (Exception e)
                                {
                                    System.out.println(
                                            "Exception menu.java interaction_with_BankAccount: " + e);
                                }
                            }

                            System.out.println(clear_console);

                            System.out.format(
                                "%25s%19s%13s%15s%11s%12s%19s\n", "number_of_account", "date_open",
                                "date_close", "money_sum", "name", "surname", "name_of_deposit");

                            for (BankAccount tmp :
                                 accountService.findBankAccountByDate_open(date_open))
                            {
                                System.out.println(tmp.toString());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "3":
                        {
                            String date_close = "2000-01-01";
                            boolean ok_input = false;
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            dateFormat.setLenient(false);

                            System.out.println(clear_console);

                            while (!ok_input)
                            {
                                System.out.println("\nEnter date_close (in format yyyy-MM-dd)");
                                date_close = input_data.nextLine();

                                try
                                {
                                    dateFormat.parse(date_close);
                                    ok_input = true;
                                }
                                catch (Exception e)
                                {
                                    System.out.println(
                                            "Exception menu.java interaction_with_BankAccount " + e);
                                }
                            }

                            System.out.println(clear_console);

                            System.out.format(
                                    "%25s%19s%13s%15s%11s%12s%19s\n", "number_of_account", "date_open",
                                    "date_close", "money_sum", "name", "surname", "name_of_deposit");

                            i = 1;

                            for (BankAccount tmp : accountService.findBankAccountByDate_close(date_close))
                            {
                                System.out
                                        .format(
                                                i + ") %1s%13s%12s%19s\n", tmp.toString(), tmp.getClient().getName(),
                                                tmp.getClient().getSurname(), tmp.getDeposit().getName_of_deposit())
                                        .toString();
                                i++;
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "4":
                        {
                            System.out.println(clear_console);

                            System.out.println(
                                "\nEnter dateFrom and dateTo (in format yyyy-MM-dd)");

                            String dateTo = "2000-01-01";
                            String dateFrom = "3000-01-01";
                            boolean ok_input = false;

                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            dateFormat.setLenient(false);

                            while (!ok_input)
                            {
                                System.out.println("\nEnter dateFrom");
                                dateFrom = input_data.nextLine();

                                try
                                {
                                    dateFormat.parse(dateFrom);
                                    ok_input = true;
                                }
                                catch (Exception e)
                                {
                                    System.out.println(
                                            "Exception menu.java interaction_with_BankAccount : " + e);
                                }
                            }

                            ok_input = false;
                            while (!ok_input)
                            {
                                System.out.println(
                                        "\nEnter dateTo");
                                dateTo = input_data.nextLine();

                                try
                                {
                                    dateFormat.parse(dateTo);
                                    ok_input = true;
                                }
                                catch (Exception e)
                                {
                                    System.out.println(
                                            "Exception menu.java interaction_with_BankAccount: " + e);
                                }
                            }

                            System.out.println(clear_console);

                            System.out.format(
                                "%25s%19s%13s%15s%11s%12s%19s\n", "number_of_account", "date_open",
                                "date_close", "money_sum", "name", "surname", "name_of_deposit");

                            accountService.findBankAccountByBetweenDate_open(dateFrom,dateTo);
                            i = 1;

                            for (BankAccount tmp : accountService.findBankAccountByBetweenDate_open(dateFrom,dateTo))
                            {
                                System.out
                                        .format(
                                                i + ") %1s%13s%12s%19s\n", tmp.toString(), tmp.getClient().getName(),
                                                tmp.getClient().getSurname(), tmp.getDeposit().getName_of_deposit())
                                        .toString();
                                i++;
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "5":
                        {
                            System.out.println(clear_console);

                            System.out.println(
                                    "\nEnter dateFrom and dateTo (in format yyyy-MM-dd)");

                            String dateFrom = "2000-01-01";
                            String dateTo = "3000-01-01";
                            boolean ok_input = false;

                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            dateFormat.setLenient(false);

                            while (!ok_input)
                            {
                                System.out.println("\nEnter dateFrom");
                                dateFrom = input_data.nextLine();

                                try
                                {
                                    dateFormat.parse(dateFrom);
                                    ok_input = true;
                                }
                                catch (Exception e)
                                {
                                    System.out.println(
                                            "Exception menu.java interaction_with_BankAccount : " + e);
                                }
                            }

                            ok_input = false;
                            while (!ok_input)
                            {
                                System.out.println(
                                        "\nEnter dateTo");
                                dateTo = input_data.nextLine();

                                try
                                {
                                    dateFormat.parse(dateTo);
                                    ok_input = true;
                                }
                                catch (Exception e)
                                {
                                    System.out.println(
                                            "Exception menu.java interaction_with_BankAccount: " + e);
                                }
                            }

                            System.out.println(clear_console);

                            System.out.format(
                                    "%25s%19s%13s%15s%11s%12s%19s\n", "number_of_account", "date_open",
                                    "date_close", "money_sum", "name", "surname", "name_of_deposit");

                            i = 1;

                            accountService.findBankAccountByBetweenDate_close(dateFrom,dateTo);

                            for (BankAccount tmp : accountService.findBankAccountByBetweenDate_close(dateFrom,dateTo))
                            {
                                System.out
                                        .format(
                                                i + ") %1s%13s%12s%19s\n", tmp.toString(), tmp.getClient().getName(),
                                                tmp.getClient().getSurname(), tmp.getDeposit().getName_of_deposit())
                                        .toString();
                                i++;
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "6":
                        {
                            System.out.println("\nEnter money_sum");
                            int money_sum = Integer.parseInt(input_data.nextLine());

                            System.out.println(clear_console);

                            System.out.format(
                                "%25s%19s%13s%15s%11s%12s%19s\n", "number_of_account", "date_open",
                                "date_close", "money_sum", "name", "surname", "name_of_deposit");

                            for (BankAccount tmp :
                                 accountService.findBankAccountByMoney_sum(money_sum))
                            {
                                System.out.println(tmp.toString());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "7":
                        {
                            System.out.println(clear_console);

                            System.out.println("\nEnter name client");
                            String name = input_data.nextLine();

                            List<Client> buf_clients = clientService.findClientByName(name);

                            if (buf_clients.size() > 1)
                            {
                                System.out.println("\n Select client, which you find");
                                int j = 1;

                                for (Client tmp : buf_clients)
                                {
                                    System.out.println(j + ") " + tmp.toString());
                                    j++;
                                }

                                System.out.println("Enter logic position");
                                int buf_log_pos = Integer.parseInt(input_data.nextLine());

                                while (buf_log_pos < 1 || buf_log_pos > buf_clients.size())
                                {
                                    System.out.println("Enter logic position");
                                    buf_log_pos = Integer.parseInt(input_data.nextLine());
                                }

                                boolean find_in_DB = false;
                                ArrayList<Integer> having_ids = new ArrayList<>();

                                for (BankAccount tmp :
                                     Singleton.getInstance().getBankAccountVector())
                                {
                                    having_ids.add(tmp.getId());
                                }

                                for (BankAccount tmp :
                                     buf_clients.get(buf_log_pos - 1).getBankAccounts())
                                {
                                    if (!having_ids.contains(tmp.getId()))
                                    {
                                        find_in_DB = true;
                                        Singleton.getInstance().getBankAccountVector().add(tmp);

                                        System.out.format(
                                            "%25s%19s%13s%15s%11s%12s%19s\n", "number_of_account",
                                            "date_open", "date_close", "money_sum", "name",
                                            "surname", "name_of_deposit");

                                        System.out.println(
                                            tmp.toString() + "    " + tmp.getClient().getName() +
                                            "    " + tmp.getClient().getSurname() + "    " +
                                            tmp.getDeposit().getName_of_deposit());
                                    }
                                }

                                if (!find_in_DB)
                                    System.out.println(
                                        "Now all nodes, which you want to find is finded and download in");
                            }
                            else
                            {
                                if (!buf_clients.isEmpty())
                                {
                                    boolean find_in_DB = false;
                                    ArrayList<Integer> having_ids = new ArrayList<>();

                                    for (BankAccount tmp :
                                         Singleton.getInstance().getBankAccountVector())
                                    {
                                        having_ids.add(tmp.getId());
                                    }

                                    for (BankAccount tmp :
                                         buf_clients.get(buf_clients.size() - 1).getBankAccounts())
                                    {
                                        if (!having_ids.contains(tmp.getId()))
                                        {
                                            find_in_DB = true;
                                            Singleton.getInstance().getBankAccountVector().add(tmp);

                                            System.out.format(
                                                "%25s%19s%13s%15s%11s%12s%19s\n",
                                                "number_of_account", "date_open", "date_close",
                                                "money_sum", "name", "surname", "name_of_deposit");

                                            System.out.println(
                                                tmp.toString() + "    " +
                                                tmp.getClient().getName() + "    " +
                                                tmp.getClient().getSurname() + "    " +
                                                tmp.getDeposit().getName_of_deposit());
                                        }
                                    }

                                    if (!find_in_DB)
                                        System.out.println(
                                            "Now all nodes, which you want to find is finded and download in");
                                }
                                else
                                    System.out.println("Dont finded nodes have this words");
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "8":
                        {
                            System.out.println(clear_console);

                            System.out.println("\nEnter surname client");
                            String surname = input_data.nextLine();

                            List<Client> buf_clients = clientService.findClientBySurame(surname);

                            if (buf_clients.size() > 1)
                            {
                                System.out.println("\n Select client, which you find");
                                int j = 1;

                                for (Client tmp : buf_clients)
                                {
                                    System.out.println(j + ") " + tmp.toString());
                                    j++;
                                }

                                System.out.println("Enter logic position");
                                int buf_log_pos = Integer.parseInt(input_data.nextLine());

                                while (buf_log_pos < 1 || buf_log_pos > buf_clients.size())
                                {
                                    System.out.println("Enter logic position");
                                    buf_log_pos = Integer.parseInt(input_data.nextLine());
                                }

                                boolean find_in_DB = false;
                                ArrayList<Integer> having_ids = new ArrayList<>();

                                for (BankAccount tmp :
                                     Singleton.getInstance().getBankAccountVector())
                                {
                                    having_ids.add(tmp.getId());
                                }

                                for (BankAccount tmp :
                                     buf_clients.get(buf_log_pos - 1).getBankAccounts())
                                {
                                    if (!having_ids.contains(tmp.getId()))
                                    {
                                        find_in_DB = true;
                                        Singleton.getInstance().getBankAccountVector().add(tmp);

                                        System.out.format(
                                            "%25s%19s%13s%15s%11s%12s%19s\n", "number_of_account",
                                            "date_open", "date_close", "money_sum", "name",
                                            "surname", "name_of_deposit");

                                        System.out.println(
                                            tmp.toString() + "    " + tmp.getClient().getName() +
                                            "    " + tmp.getClient().getSurname() + "    " +
                                            tmp.getDeposit().getName_of_deposit());
                                    }
                                }

                                if (!find_in_DB)
                                    System.out.println(
                                        "Now all nodes, which you want to find is finded and download in");
                            }
                            else
                            {
                                if (!buf_clients.isEmpty())
                                {
                                    boolean find_in_DB = false;
                                    ArrayList<Integer> having_ids = new ArrayList<>();

                                    for (BankAccount tmp :
                                         Singleton.getInstance().getBankAccountVector())
                                    {
                                        having_ids.add(tmp.getId());
                                    }

                                    for (BankAccount tmp :
                                         buf_clients.get(buf_clients.size() - 1).getBankAccounts())
                                    {
                                        if (!having_ids.contains(tmp.getId()))
                                        {
                                            find_in_DB = true;
                                            Singleton.getInstance().getBankAccountVector().add(tmp);

                                            System.out.format(
                                                "%25s%19s%13s%15s%11s%12s%19s\n",
                                                "number_of_account", "date_open", "date_close",
                                                "money_sum", "name", "surname", "name_of_deposit");

                                            System.out.println(
                                                tmp.toString() + "    " +
                                                tmp.getClient().getName() + "    " +
                                                tmp.getClient().getSurname() + "    " +
                                                tmp.getDeposit().getName_of_deposit());
                                        }
                                    }

                                    if (!find_in_DB)
                                        System.out.println(
                                            "Now all nodes, which you want to find is finded and download in");
                                }
                                else
                                    System.out.println("Dont finded nodes have this words");
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "9":
                        {
                            System.out.println(clear_console);

                            System.out.println("\nEnter name_of_deposit");
                            String name_of_deposit = input_data.nextLine();

                            List<Deposit> buf_deposits =
                                depositService.findDepositByName_of_deposit(name_of_deposit);

                            if (buf_deposits.size() > 1)
                            {
                                System.out.println("\n Select deposit, which you find");
                                int j = 1;

                                for (Deposit tmp : buf_deposits)
                                {
                                    System.out.println(j + ") " + tmp.toString());
                                    j++;
                                }

                                System.out.println("Enter logic position");
                                int buf_log_pos = Integer.parseInt(input_data.nextLine());

                                while (buf_log_pos < 0 || buf_log_pos > buf_deposits.size())
                                {
                                    System.out.println("Enter logic position");
                                    buf_log_pos = Integer.parseInt(input_data.nextLine());
                                }

                                boolean find_in_DB = false;
                                ArrayList<Integer> having_ids = new ArrayList<>();

                                for (BankAccount tmp :
                                     Singleton.getInstance().getBankAccountVector())
                                {
                                    having_ids.add(tmp.getId());
                                }

                                for (BankAccount tmp :
                                     buf_deposits.get(buf_log_pos - 1).getBankAccounts())
                                {
                                    if (!having_ids.contains(tmp.getId()))
                                    {
                                        find_in_DB = true;
                                        Singleton.getInstance().getBankAccountVector().add(tmp);

                                        System.out.format(
                                            "%25s%19s%13s%15s%11s%12s%19s\n", "number_of_account",
                                            "date_open", "date_close", "money_sum", "name",
                                            "surname", "name_of_deposit");

                                        System.out.println(
                                            tmp.toString() + "    " + tmp.getClient().getName() +
                                            "    " + tmp.getClient().getSurname() + "    " +
                                            tmp.getDeposit().getName_of_deposit());
                                    }
                                }

                                if (!find_in_DB)
                                    System.out.println(
                                        "Now all nodes, which you want to find is finded and download in");
                            }
                            else
                            {
                                if (!buf_deposits.isEmpty())
                                {
                                    boolean find_in_DB = false;
                                    ArrayList<Integer> having_ids = new ArrayList<>();

                                    for (BankAccount tmp :
                                         Singleton.getInstance().getBankAccountVector())
                                    {
                                        having_ids.add(tmp.getId());
                                    }

                                    for (BankAccount tmp : buf_deposits.get(buf_deposits.size() - 1)
                                                               .getBankAccounts())
                                    {
                                        if (!having_ids.contains(tmp.getId()))
                                        {
                                            find_in_DB = true;
                                            Singleton.getInstance().getBankAccountVector().add(tmp);

                                            System.out.format(
                                                "%25s%19s%13s%15s%11s%12s%19s\n",
                                                "number_of_account", "date_open", "date_close",
                                                "money_sum", "name", "surname", "name_of_deposit");

                                            System.out.println(
                                                tmp.toString() + "    " +
                                                tmp.getClient().getName() + "    " +
                                                tmp.getClient().getSurname() + "    " +
                                                tmp.getDeposit().getName_of_deposit());
                                        }
                                    }

                                    if (!find_in_DB)
                                        System.out.println(
                                            "Now all nodes, which you want to find is finded and download in");
                                }
                                else
                                    System.out.println("Dont finded nodes have this words");
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "q":
                        {
                            System.out.println(
                                "End find in BankAccount table! Press [Enter] for continue.");
                            input_data.nextLine();
                            is_actionable_menu = false;
                            break;
                        }
                        default:
                        {
                            System.out.println("Wrong entered value, press [Enter] for try again!");
                            input_data.nextLine();
                        }
                    }

                    break;
                }
                case "q":
                {
                    System.out.println(
                        "End work with BankAccount table! Press [Enter] for continue.");
                    input_data.nextLine();
                    is_actionable_menu = false;
                    break;
                }
                default:
                {
                    System.out.println("Wrong entered value, press [Enter] for try again!");
                    input_data.nextLine();
                    break;
                }
            }
        }
    }

    static private void interaction_with_Client()
    {
        if (Singleton.getInstance().getClientVector().isEmpty())
            clientService.findNoOneClientById(client_id, client_id_step);

        boolean is_actionable_menu = true;

        String choose_menu_point = null;

        while (is_actionable_menu)
        {
            System.out.println(clear_console);

            System.out.format(
                "%7s%15s%15s%25s%18s%24s\n", "name", "surname", "fathername",
                "serial/number pasport", "telephone", "address");

            int i = 1;

            for (Client tmp : Singleton.getInstance().getClientVector())
            {
                System.out.format(i + ") %1s%-8s\n", tmp.toString(), tmp.getHome().getAddress())
                    .toString();
                i++;
            }

            System.out.println("\nClient page {" + client_page + "};");

            String output_menu_info = "\n\n"
                + "1) Show next " + client_id_step + " nodes\n"
                + "2) Show prev " + client_id_step + " nodes\n\n"
                + "3) Insert node\n"
                + "4) Update node\n"
                + "5) Delete node\n\n"
                + "6) Find node\n\n"
                + "q) To Exit\n\n"
                + "Enter number for choosing: ";

            System.out.println(output_menu_info);

            choose_menu_point = input_data.nextLine();

            switch (choose_menu_point)
            {
                /**
                 * next 5
                 */
                case "1":
                {
                    if (client_id + client_id_step - 1 < clientService.get_count_nodes())
                    {
                        client_id += client_id_step;
                        client_page++;
                        clientService.findNoOneClientById(client_id, client_id_step);
                    }
                    break;
                }
                /**
                 * prev 5
                 */
                case "2":
                {
                    if (client_id - client_id_step > 0)
                    {
                        client_id -= client_id_step;
                        client_page--;
                        clientService.findNoOneClientById(client_id, client_id_step);
                    }
                    break;
                }
                /**
                 * insert node
                 */
                case "3":
                {
                    System.out.println(clear_console);

                    System.out.format(
                        "%7s%15s%15s%25s%18s%24s\n", "name", "surname", "fathername",
                        "serial/number pasport", "telephone", "address");

                    i = 1;

                    for (Client tmp : Singleton.getInstance().getClientVector())
                    {
                        System.out
                            .format(i + ") %1s%-8s\n", tmp.toString(), tmp.getHome().getAddress())
                            .toString();
                        i++;
                    }

                    System.out.println("\nany key) Continue INSERT");
                    System.out.println("\nq) To Exit");

                    String choosen_node = input_data.nextLine();

                    if (choosen_node.toLowerCase().contains("q"))
                    {
                        break;
                    }

                    String name, surname, fathername, telephone, address = null;
                    int serial_of_pasport = 1111;
                    int number_of_pasport = 111111;
                    boolean ok_input = false;

                    System.out.println("\nEnter name");
                    name = input_data.nextLine();

                    System.out.println("\nEnter surname");
                    surname = input_data.nextLine();

                    System.out.println("\nEnter fathername");
                    fathername = input_data.nextLine();

                    while (!ok_input)
                    {
                        System.out.println("\nEnter serial_of_pasport (in format: 9999 > NNNN >= 1111)");

                        try
                        {
                            serial_of_pasport = Integer.parseInt(input_data.nextLine());
                            if (serial_of_pasport <= 9999 && serial_of_pasport >= 1111)
                                ok_input = true;
                        }
                        catch (Exception e)
                        {
                            System.out.println("Exception menu.java insert Client: " + e);
                        }
                    }

                    ok_input = false;
                    while (!ok_input)
                    {
                        System.out.println(
                                "\nEnter number_of_pasport (in format: 100000 > NNNNNN >= 111111)");

                        try
                        {
                            number_of_pasport = Integer.parseInt(input_data.nextLine());
                            if (number_of_pasport <= 999999 && number_of_pasport >= 111111)
                                ok_input = true;
                        }
                        catch (Exception e)
                        {
                            System.out.println("Exception menu.java insert Client: " + e);
                        }
                    }

                    System.out.println("\nEnter telephone (for example +7-(800)-555-35-35)");
                    telephone = input_data.nextLine();

                    Client client = new Client(
                        name, surname, fathername, serial_of_pasport, number_of_pasport, telephone);

                    List<Home> buf_homes = null;

                    ok_input = false;
                    while (!ok_input)
                    {
                        System.out.println("\nEnter address");
                        address = input_data.nextLine();

                        buf_homes = homeService.findHomeByAddress(address);

                        if (!buf_homes.isEmpty())
                        {
                            ok_input = true;

                            System.out.println("\n Select home, which you want");
                            int j = 1;

                            for (Home tmp : buf_homes)
                            {
                                System.out.println(j + ") " + tmp.toString());
                                j++;
                            }

                            System.out.println("Enter logic position");
                            int buf_log_pos = Integer.parseInt(input_data.nextLine());

                            while (buf_log_pos < 0 || buf_log_pos > buf_homes.size())
                            {
                                System.out.println("Enter logic position");
                                buf_log_pos = Integer.parseInt(input_data.nextLine());
                            }

                            client.setHome(buf_homes.get(buf_log_pos - 1));
                        }
                        else
                            System.out.println("Dont finded nodes have this words");
                    }

                    clientService.saveClient(client);

                    break;
                }
                /**
                 * update node
                 */
                case "4":
                {
                    System.out.println(clear_console);

                    System.out.format(
                        "%7s%15s%15s%25s%18s%24s\n", "name", "surname", "fathername",
                        "serial/number pasport", "telephone", "address");

                    i = 1;

                    for (Client tmp : Singleton.getInstance().getClientVector())
                    {
                        System.out
                            .format(i + ") %1s%-8s\n", tmp.toString(), tmp.getHome().getAddress())
                            .toString();
                        i++;
                    }

                    System.out.println("\nChoose node on # for UPDATE");
                    System.out.println("\nany number) To UPDATE node");
                    System.out.println("\nq) To Exit");

                    String choosen_node = input_data.nextLine();

                    if (choosen_node.toLowerCase().contains("q") || choosen_node.isEmpty() ||
                        Integer.parseInt(choosen_node) > i - 1 ||
                        Integer.parseInt(choosen_node) < 1)
                    {
                        break;
                    }

                    boolean is_update_active = true;

                    while (is_update_active)
                    {
                        System.out.println("1) Update name");
                        System.out.println("2) Update surname");
                        System.out.println("3) Update fathername");
                        System.out.println("4) Update serial_of_pasport");
                        System.out.println("5) Update number_of_pasport");
                        System.out.println("6) Update telephone");
                        System.out.println("7) Update address");
                        System.out.println("8) Update all");
                        System.out.println("\nq) To Exit");

                        String update_interface = input_data.nextLine();

                        switch (update_interface)
                        {
                            case "1":
                            {
                                System.out.println("\nEnter name");
                                String name = input_data.nextLine();

                                Singleton.getInstance()
                                    .getClientVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setName(name);
                                clientService.updateClient(
                                    Singleton.getInstance().getClientVector().get(
                                        Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "2":
                            {
                                System.out.println("\nEnter surname");
                                String surname = input_data.nextLine();

                                Singleton.getInstance()
                                    .getClientVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setSurname(surname);
                                clientService.updateClient(
                                    Singleton.getInstance().getClientVector().get(
                                        Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "3":
                            {
                                System.out.println("\nEnter fathername");
                                String fathername = input_data.nextLine();

                                Singleton.getInstance()
                                    .getClientVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setFathername(fathername);
                                clientService.updateClient(
                                    Singleton.getInstance().getClientVector().get(
                                        Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "4":
                            {
                                int serial_of_pasport = 1111;
                                boolean ok_input = false;

                                while (!ok_input)
                                {
                                    System.out.println("\nEnter serial_of_pasport (in format: 9999 > NNNN > 0)");

                                    try
                                    {
                                        serial_of_pasport = Integer.parseInt(input_data.nextLine());
                                        if (serial_of_pasport <= 9999 && serial_of_pasport >= 1111)
                                            ok_input = true;
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println("Exception menu.java insert Client: " + e);
                                    }
                                }

                                Singleton.getInstance()
                                    .getClientVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setSerial_of_pasport(serial_of_pasport);
                                clientService.updateClient(
                                    Singleton.getInstance().getClientVector().get(
                                        Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "5":
                            {
                                int number_of_pasport = 111111;
                                boolean ok_input = false;

                                while (!ok_input)
                                {
                                    System.out.println(
                                            "\nEnter number_of_pasport (in format: 100000 > NNNNNN >= 111111)");

                                    try
                                    {
                                        number_of_pasport = Integer.parseInt(input_data.nextLine());
                                        if (number_of_pasport <= 999999 && number_of_pasport >= 111111)
                                            ok_input = true;
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println("Exception menu.java insert Client: " + e);
                                    }
                                }

                                Singleton.getInstance()
                                    .getClientVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setNumber_of_pasport(number_of_pasport);
                                clientService.updateClient(
                                    Singleton.getInstance().getClientVector().get(
                                        Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "6":
                            {
                                System.out.println(
                                    "\nEnter telephone (for example +7-(800)-555-35-35)");
                                String telephone = input_data.nextLine();

                                Singleton.getInstance()
                                    .getClientVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setTelephone(telephone);
                                clientService.updateClient(
                                    Singleton.getInstance().getClientVector().get(
                                        Integer.parseInt(choosen_node) - 1));
                                break;
                            }
                            case "7":
                            {
                                System.out.println("\nEnter address");
                                String address = input_data.nextLine();

                                List<Home> buf_homes = homeService.findHomeByAddress(address);

                                if (buf_homes.size() > 1)
                                {
                                    System.out.println("\n Select home, which you want");
                                    int j = 1;

                                    for (Home tmp : buf_homes)
                                    {
                                        System.out.println(j + ") " + tmp.toString());
                                        j++;
                                    }

                                    System.out.println("Enter logic position");
                                    int buf_log_pos = Integer.parseInt(input_data.nextLine());

                                    while (buf_log_pos < 0 || buf_log_pos > buf_homes.size())
                                    {
                                        System.out.println("Enter logic position");
                                        buf_log_pos = Integer.parseInt(input_data.nextLine());
                                    }

                                    Singleton.getInstance()
                                        .getClientVector()
                                        .get(Integer.parseInt(choosen_node) - 1)
                                        .setHome(buf_homes.get(buf_log_pos - 1));
                                }
                                else
                                {
                                    if (!buf_homes.isEmpty())
                                        Singleton.getInstance()
                                            .getClientVector()
                                            .get(Integer.parseInt(choosen_node) - 1)
                                            .setHome(buf_homes.get(buf_homes.size() - 1));
                                }

                                clientService.updateClient(
                                    Singleton.getInstance().getClientVector().get(
                                        Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "8":
                            {
                                System.out.println("\nEnter name");
                                String name = input_data.nextLine();

                                Singleton.getInstance()
                                    .getClientVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setName(name);

                                System.out.println("\nEnter surname");
                                String surname = input_data.nextLine();

                                Singleton.getInstance()
                                    .getClientVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setSurname(surname);

                                System.out.println("\nEnter fathername");
                                String fathername = input_data.nextLine();

                                Singleton.getInstance()
                                    .getClientVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setFathername(fathername);

                                System.out.println(
                                    "\nEnter serial_of_pasport (in format: 9999 > NNNN > 1111)");
                                int serial_of_pasport = Integer.parseInt(input_data.nextLine());

                                while (serial_of_pasport > 9999 || serial_of_pasport < 1111)
                                {
                                    System.out.println(
                                        "\nEnter serial_of_pasport (in format: 9999 > NNNN > 1111)");
                                    serial_of_pasport = Integer.parseInt(input_data.nextLine());
                                }

                                Singleton.getInstance()
                                    .getClientVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setSerial_of_pasport(serial_of_pasport);

                                System.out.println(
                                    "\nEnter number_of_pasport (in format: 100000 > NNNNNN > 999999)");
                                int number_of_pasport = Integer.parseInt(input_data.nextLine());

                                while (number_of_pasport > 999999 || number_of_pasport < 100000)
                                {
                                    System.out.println(
                                        "\nEnter number_of_pasport (in format: 100000 > NNNNNN > 999999)");
                                    number_of_pasport = Integer.parseInt(input_data.nextLine());
                                }

                                Singleton.getInstance()
                                    .getClientVector()
                                    .get(Integer.parseInt(choosen_node) - 1)
                                    .setNumber_of_pasport(number_of_pasport);

                                System.out.println("\nEnter address");
                                String address = input_data.nextLine();

                                List<Home> buf_homes = homeService.findHomeByAddress(address);

                                if (buf_homes.size() > 1)
                                {
                                    System.out.println("\n Select home, which you want");
                                    int j = 1;

                                    for (Home tmp : buf_homes)
                                    {
                                        System.out.println(j + ") " + tmp.toString());
                                        j++;
                                    }

                                    System.out.println("Enter logic position");
                                    int buf_log_pos = Integer.parseInt(input_data.nextLine());

                                    while (buf_log_pos < 0 || buf_log_pos > buf_homes.size())
                                    {
                                        System.out.println("Enter logic position");
                                        buf_log_pos = Integer.parseInt(input_data.nextLine());
                                    }

                                    Singleton.getInstance()
                                        .getClientVector()
                                        .get(Integer.parseInt(choosen_node) - 1)
                                        .setHome(buf_homes.get(buf_log_pos - 1));
                                }
                                else
                                {
                                    if (!buf_homes.isEmpty())
                                        Singleton.getInstance()
                                            .getClientVector()
                                            .get(Integer.parseInt(choosen_node) - 1)
                                            .setHome(buf_homes.get(buf_homes.size() - 1));
                                }

                                clientService.updateClient(
                                    Singleton.getInstance().getClientVector().get(
                                        Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "q":
                            {
                                System.out.println(
                                    "End UPDATE Client nodes! Press [Enter] for continue.");
                                input_data.nextLine();
                                is_update_active = false;
                                break;
                            }
                            default:
                            {
                                System.out.println(
                                    "Wrong entered value, press [Enter] for try again!");
                                input_data.nextLine();
                                break;
                            }
                        }
                    }

                    break;
                }
                /**
                 * delete node
                 */
                case "5":
                {
                    System.out.println(clear_console);

                    System.out.format(
                        "%7s%15s%15s%25s%18s%24s\n", "name", "surname", "fathername",
                        "serial/number pasport", "telephone", "address");

                    i = 1;

                    for (Client tmp : Singleton.getInstance().getClientVector())
                    {
                        System.out
                            .format(i + ") %1s%-8s\n", tmp.toString(), tmp.getHome().getAddress())
                            .toString();
                        i++;
                    }

                    System.out.println("\nChoose node on # for DELETE");
                    System.out.println("\nany number) To DELETE node");
                    System.out.println("\nq) To Exit");

                    String choosen_node = input_data.nextLine();

                    if (choosen_node.toLowerCase().contains("q") || choosen_node.isEmpty() ||
                        Integer.parseInt(choosen_node) > i - 1 ||
                        Integer.parseInt(choosen_node) < 1)
                    {
                        break;
                    }

                    clientService.deleteClient(Singleton.getInstance().getClientVector().get(
                        Integer.parseInt(choosen_node) - 1));

                    break;
                }
                /**
                 * find by {cloumn}
                 */
                case "6":
                {
                    System.out.println(clear_console);

                    System.out.println("1) Find all by name");
                    System.out.println("2) Find all by surname");
                    System.out.println("3) Find all by fathername");
                    System.out.println("4) Find all by serial_of_pasport");
                    System.out.println("5) Find all by number_of_pasport");
                    System.out.println("6) Find all by telephone");
                    System.out.println("7) Find by address");
                    System.out.println("\nq) To Exit");

                    String find_interface = input_data.nextLine();

                    switch (find_interface)
                    {
                        case "1":
                        {
                            System.out.println(clear_console);

                            System.out.println("\nEnter name client");
                            String name = input_data.nextLine();

                            List<Client> buf_clients = clientService.findClientByName(name);

                            System.out.format(
                                "%7s%15s%15s%25s%18s%24s\n", "name", "surname", "fathername",
                                "serial/number pasport", "telephone", "address");

                            if (buf_clients.isEmpty())
                            {
                                System.out.println("Dont finded nodes have this words");
                                input_data.nextLine();
                                break;
                            }

                            for (Client tmp : buf_clients)
                            {
                                System.out.println(
                                    "   " + tmp.toString() + tmp.getHome().getAddress());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "2":
                        {
                            System.out.println(clear_console);

                            System.out.println("\nEnter surname client");
                            String surname = input_data.nextLine();

                            List<Client> buf_clients = clientService.findClientBySurame(surname);

                            System.out.format(
                                "%7s%15s%15s%25s%18s%24s\n", "name", "surname", "fathername",
                                "serial/number pasport", "telephone", "address");

                            if (buf_clients.isEmpty())
                            {
                                System.out.println("Dont finded nodes have this words");
                                input_data.nextLine();
                                break;
                            }

                            for (Client tmp : buf_clients)
                            {
                                System.out.println(
                                    "   " + tmp.toString() + tmp.getHome().getAddress());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "3":
                        {
                            System.out.println(clear_console);

                            System.out.println("\nEnter fathername");
                            String fathername = input_data.nextLine();

                            List<Client> buf_clients = clientService.findClientByFathername(fathername);

                            System.out.format(
                                    "%7s%15s%15s%25s%18s%24s\n", "name", "surname", "fathername",
                                    "serial/number pasport", "telephone", "address");

                            if (buf_clients.isEmpty())
                            {
                                System.out.println("Dont finded nodes have this words");
                                input_data.nextLine();
                                break;
                            }

                            for (Client tmp : buf_clients)
                            {
                                System.out.println(
                                        "   " + tmp.toString() + tmp.getHome().getAddress());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "4":
                        {
                            System.out.println(clear_console);

                            System.out.println(
                                    "\nEnter serial_of_pasport (in format: 9999 > NNNN > 0)");
                            int serial_of_pasport = Integer.parseInt(input_data.nextLine());

                            while (serial_of_pasport > 9999 || serial_of_pasport < 1)
                            {
                                System.out.println(
                                        "\nEnter serial_of_pasport (in format: 9999 > NNNN > 0)");
                                serial_of_pasport = Integer.parseInt(input_data.nextLine());
                            }

                            List<Client> buf_clients =
                                    clientService.findClientBySerial_of_pasport(serial_of_pasport);

                            System.out.format(
                                    "%7s%15s%15s%25s%18s%24s\n", "name", "surname", "fathername",
                                    "serial/number pasport", "telephone", "address");

                            if (buf_clients.isEmpty())
                            {
                                System.out.println("Dont finded nodes have this words");
                                input_data.nextLine();
                                break;
                            }

                            for (Client tmp : buf_clients)
                            {
                                System.out.println(
                                        "   " + tmp.toString() + tmp.getHome().getAddress());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "5":
                        {
                            System.out.println(clear_console);

                            System.out.println(
                                    "\nEnter number_of_pasport (in format: 100000 > NNNNNN > 0)");
                            int number_of_pasport = Integer.parseInt(input_data.nextLine());

                            while (number_of_pasport > 999999 || number_of_pasport < 1)
                            {
                                System.out.println(
                                        "\nEnter number_of_pasport (in format: 100000 > NNNNNN > 0)");
                                number_of_pasport = Integer.parseInt(input_data.nextLine());
                            }

                            List<Client> buf_clients =
                                    clientService.findClientByNumber_of_pasport(number_of_pasport);

                            System.out.format(
                                    "%7s%15s%15s%25s%18s%24s\n", "name", "surname", "fathername",
                                    "serial/number pasport", "telephone", "address");

                            if (buf_clients.isEmpty())
                            {
                                System.out.println("Dont finded nodes have this words");
                                input_data.nextLine();
                                break;
                            }

                            for (Client tmp : buf_clients)
                            {
                                System.out.println(
                                        "   " + tmp.toString() + tmp.getHome().getAddress());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "6":
                        {
                            System.out.println(clear_console);

                            System.out.println(
                                "\nEnter telephone (for example +7-(800)-555-35-35)");
                            String telephone = input_data.nextLine();

                            List<Client> buf_clients =
                                clientService.findClientByTelephone(telephone);

                            System.out.format(
                                "%7s%15s%15s%25s%18s%24s\n", "name", "surname", "fathername",
                                "serial/number pasport", "telephone", "address");

                            if (buf_clients.isEmpty())
                            {
                                System.out.println("Dont finded nodes have this words");
                                input_data.nextLine();
                                break;
                            }

                            for (Client tmp : buf_clients)
                            {
                                System.out.println(
                                    "   " + tmp.toString() + tmp.getHome().getAddress());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "7":
                        {
                            System.out.println(clear_console);

                            System.out.println("\nEnter address");
                            String address = input_data.nextLine();

                            List<Home> buf_homes = homeService.findHomeByAddress(address);

                            if (buf_homes.size() > 1)
                            {
                                System.out.println("\n Select home, which you find");
                                int j = 1;

                                for (Home tmp : buf_homes)
                                {
                                    System.out.println(j + ") " + tmp.toString());
                                    j++;
                                }

                                System.out.println("Enter logic position");
                                int buf_log_pos = Integer.parseInt(input_data.nextLine());

                                while (buf_log_pos < 1 || buf_log_pos > buf_homes.size())
                                {
                                    System.out.println("Enter logic position");
                                    buf_log_pos = Integer.parseInt(input_data.nextLine());
                                }

                                boolean find_in_DB = false;
                                ArrayList<Integer> having_ids = new ArrayList<>();

                                for (Client tmp :
                                        Singleton.getInstance().getClientVector())
                                {
                                    having_ids.add(tmp.getId());
                                }

                                for (Client tmp :
                                        buf_homes.get(buf_log_pos - 1).getClients())
                                {
                                    if (!having_ids.contains(tmp.getId()))
                                    {
                                        find_in_DB = true;
                                        Singleton.getInstance().getClientVector().add(tmp);

                                        System.out.format(
                                                "%7s%15s%15s%25s%18s%24s\n", "name", "surname", "fathername",
                                                "serial/number pasport", "telephone", "address");

                                        System.out.println("   " + tmp.toString() + tmp.getHome().getAddress());
                                    }
                                }

                                if (!find_in_DB)
                                    System.out.println(
                                            "Now all nodes, which you want to find is finded and download in");
                            }
                            else
                            {
                                if (!buf_homes.isEmpty())
                                {
                                    boolean find_in_DB = false;
                                    ArrayList<Integer> having_ids = new ArrayList<>();

                                    for (Client tmp :
                                            Singleton.getInstance().getClientVector())
                                    {
                                        having_ids.add(tmp.getId());
                                    }

                                    for (Client tmp :
                                            buf_homes.get(buf_homes.size() - 1).getClients())
                                    {
                                        if (!having_ids.contains(tmp.getId()))
                                        {
                                            find_in_DB = true;
                                            Singleton.getInstance().getClientVector().add(tmp);

                                            System.out.format(
                                                    "%7s%15s%15s%25s%18s%24s\n", "name", "surname", "fathername",
                                                    "serial/number pasport", "telephone", "address");

                                            System.out.println("   " + tmp.toString() + tmp.getHome().getAddress());
                                        }
                                    }

                                    if (!find_in_DB)
                                        System.out.println(
                                                "Now all nodes, which you want to find is finded and download in");
                                }
                                else
                                    System.out.println("Dont finded nodes have this words");
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "q":
                        {
                            System.out.println(
                                "End work with BankAccount table! Press [Enter] for continue.");
                            input_data.nextLine();
                            is_actionable_menu = false;
                            break;
                        }
                        default:
                        {
                            System.out.println("Wrong entered value, press [Enter] for try again!");
                            input_data.nextLine();
                            break;
                        }
                    }
                    break;
                }
                case "q":
                {
                    System.out.println(
                            "End work with Client table! Press [Enter] for continue.");
                    input_data.nextLine();
                    is_actionable_menu = false;
                    break;
                }
                default:
                {
                    System.out.println("Wrong entered value, press [Enter] for try again!");
                    input_data.nextLine();
                    break;
                }
            }
        }
    }

    static private void interaction_with_Deposit()
    {
        if (Singleton.getInstance().getDepositVector().isEmpty())
            depositService.findNoOneDepositById(deposit_id, deposit_id_step);

        boolean is_actionable_menu = true;

        String choose_menu_point = null;

        while (is_actionable_menu)
        {
            System.out.println(clear_console);

            System.out.format(
                    "%17s%15s%15s\n", "name_of_deposit", "storage_time", "interest_rate");

            int i = 1;

            for (Deposit tmp : Singleton.getInstance().getDepositVector())
            {
                System.out.println(i + ") " + tmp.toString());
                i++;
            }

            System.out.println("\nDeposit page {" + deposit_page + "};");

            String output_menu_info = "\n\n"
                    + "1) Show next " + deposit_id_step + " nodes\n"
                    + "2) Show prev " + deposit_id_step + " nodes\n\n"
                    + "3) Insert node\n"
                    + "4) Update node\n"
                    + "5) Delete node\n\n"
                    + "6) Find node\n\n"
                    + "q) To Exit\n\n"
                    + "Enter number for choosing: ";

            System.out.println(output_menu_info);

            choose_menu_point = input_data.nextLine();

            switch (choose_menu_point)
            {
                /**
                 * next 5
                 */
                case "1":
                {
                    if (deposit_id + deposit_id_step - 1 < depositService.get_count_nodes())
                    {
                        deposit_id += deposit_id_step;
                        deposit_page++;
                        depositService.findNoOneDepositById(deposit_id, client_id_step);
                    }
                    break;
                }
                /**
                 * prev 5
                 */
                case "2":
                {
                    if (deposit_id - deposit_id_step > 0)
                    {
                        deposit_id -= deposit_id_step;
                        deposit_page--;
                        depositService.findNoOneDepositById(deposit_id, deposit_id_step);
                    }
                    break;
                }
                /**
                 * insert node
                 */
                case "3":
                {
                    System.out.println(clear_console);

                    System.out.format(
                            "%17s%15s%15s\n", "name_of_deposit", "storage_time", "interest_rate");

                    i = 1;

                    for (Deposit tmp : Singleton.getInstance().getDepositVector())
                    {
                        System.out.println(i + ") " + tmp.toString());
                        i++;
                    }

                    System.out.println("\nany key) Continue INSERT");
                    System.out.println("\nq) To Exit");

                    String choosen_node = input_data.nextLine();

                    if (choosen_node.toLowerCase().contains("q"))
                    {
                        break;
                    }

                    String name_of_deposit = null;
                    int storage_time = 1;
                    int interest_rate = 1;
                    boolean ok_input = false;

                    System.out.println("\nEnter name_of_deposit");
                    name_of_deposit = input_data.nextLine();

                    while (!ok_input)
                    {
                        System.out.println("\nEnter storage_time");

                        try
                        {
                            storage_time = Integer.parseInt(input_data.nextLine());
                            if (storage_time >= 1)
                                ok_input = true;
                        }
                        catch (Exception e)
                        {
                            System.out.println("Exception menu.java insert Home: " + e);
                        }
                    }

                    ok_input = false;
                    while (!ok_input)
                    {
                        System.out.println("\nEnter interest_rate");

                        try
                        {
                            interest_rate = Integer.parseInt(input_data.nextLine());
                            if (interest_rate >= 1)
                                ok_input = true;
                        }
                        catch (Exception e)
                        {
                            System.out.println("Exception menu.java insert Deposit: " + e);
                        }
                    }

                    depositService.saveDeposit(new Deposit(name_of_deposit,storage_time,interest_rate));

                    break;
                }
                /**
                 * update node
                 */
                case "4":
                {
                    System.out.println(clear_console);

                    System.out.format(
                            "%17s%15s%15s\n", "name_of_deposit", "storage_time", "interest_rate");

                    i = 1;

                    for (Deposit tmp : Singleton.getInstance().getDepositVector())
                    {
                        System.out.println(i + ") " + tmp.toString());
                        i++;
                    }

                    System.out.println("\nChoose node on # for UPDATE");
                    System.out.println("\nany number) To UPDATE node");
                    System.out.println("\nq) To Exit");

                    String choosen_node = input_data.nextLine();

                    if (choosen_node.toLowerCase().contains("q") || choosen_node.isEmpty() ||
                            Integer.parseInt(choosen_node) > i - 1 ||
                            Integer.parseInt(choosen_node) < 1)
                    {
                        break;
                    }

                    boolean is_update_active = true;

                    while (is_update_active)
                    {
                        System.out.println("1) Update name_of_deposit");
                        System.out.println("2) Update storage_time");
                        System.out.println("3) Update interest_rate");
                        System.out.println("4) Update all");
                        System.out.println("\nq) To Exit");

                        String update_interface = input_data.nextLine();

                        switch (update_interface)
                        {
                            case "1":
                            {
                                System.out.println("\nEnter name_of_deposit");
                                String name_of_deposit = input_data.nextLine();

                                Singleton.getInstance().getDepositVector().get(Integer.parseInt(choosen_node) - 1).setName_of_deposit(name_of_deposit);
                                depositService.updateDeposit(Singleton.getInstance().getDepositVector().get(Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "2":
                            {
                                boolean ok_input = false;
                                int storage_time = 1;

                                while (!ok_input)
                                {
                                    System.out.println("\nEnter storage_time");

                                    try
                                    {
                                        storage_time = Integer.parseInt(input_data.nextLine());
                                        if (storage_time >= 1)
                                            ok_input = true;
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println("Exception menu.java insert Deposit: " + e);
                                    }
                                }

                                Singleton.getInstance().getDepositVector().get(Integer.parseInt(choosen_node) - 1).setStorage_time(storage_time);
                                depositService.updateDeposit(Singleton.getInstance().getDepositVector().get(Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "3":
                            {
                                boolean ok_input = false;
                                int interest_rate = 1;

                                while (!ok_input)
                                {
                                    System.out.println("\nEnter interest_rate");

                                    try
                                    {
                                        interest_rate = Integer.parseInt(input_data.nextLine());
                                        if (interest_rate >= 1)
                                            ok_input = true;
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println("Exception menu.java insert Deposit: " + e);
                                    }
                                }

                                Singleton.getInstance().getDepositVector().get(Integer.parseInt(choosen_node) - 1).setInterest_rate(interest_rate);
                                depositService.updateDeposit(Singleton.getInstance().getDepositVector().get(Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "4":
                            {
                                System.out.println("\nEnter name_of_deposit");
                                String name_of_deposit = input_data.nextLine();

                                boolean ok_input = false;
                                int storage_time = 1;

                                while (!ok_input)
                                {
                                    System.out.println("\nEnter storage_time");

                                    try
                                    {
                                        storage_time = Integer.parseInt(input_data.nextLine());
                                        if (storage_time >= 1)
                                            ok_input = true;
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println("Exception menu.java insert Deposit: " + e);
                                    }
                                }

                                int interest_rate = 1;

                                ok_input = false;
                                while (!ok_input)
                                {
                                    System.out.println("\nEnter interest_rate");

                                    try
                                    {
                                        interest_rate = Integer.parseInt(input_data.nextLine());
                                        if (interest_rate >= 1)
                                            ok_input = true;
                                    }
                                    catch (Exception e)
                                    {
                                        System.out.println("Exception menu.java insert Deposit: " + e);
                                    }
                                }

                                Singleton.getInstance().getDepositVector().get(Integer.parseInt(choosen_node) - 1).setName_of_deposit(name_of_deposit);
                                Singleton.getInstance().getDepositVector().get(Integer.parseInt(choosen_node) - 1).setStorage_time(storage_time);
                                Singleton.getInstance().getDepositVector().get(Integer.parseInt(choosen_node) - 1).setInterest_rate(interest_rate);
                                depositService.updateDeposit(Singleton.getInstance().getDepositVector().get(Integer.parseInt(choosen_node) - 1));

                                break;
                            }
                            case "q":
                            {
                                System.out.println(
                                        "End UPDATE Client nodes! Press [Enter] for continue.");
                                input_data.nextLine();
                                is_update_active = false;
                                break;
                            }
                            default:
                            {
                                System.out.println(
                                        "Wrong entered value, press [Enter] for try again!");
                                input_data.nextLine();
                                break;
                            }
                        }
                    }

                    break;
                }
                /**
                 * delete node
                 */
                case "5":
                {
                    System.out.println(clear_console);

                    System.out.format(
                            "%17s%15s%15s\n", "name_of_deposit", "storage_time", "interest_rate");

                    i = 1;

                    for (Deposit tmp : Singleton.getInstance().getDepositVector())
                    {
                        System.out.println(i + ") " + tmp.toString());
                        i++;
                    }

                    System.out.println("\nChoose node on # for DELETE");
                    System.out.println("\nany number) To DELETE node");
                    System.out.println("\nq) To Exit");

                    String choosen_node = input_data.nextLine();

                    if (choosen_node.toLowerCase().contains("q") || choosen_node.isEmpty() ||
                            Integer.parseInt(choosen_node) > i - 1 ||
                            Integer.parseInt(choosen_node) < 1)
                    {
                        break;
                    }

                    depositService.deleteDeposit(Singleton.getInstance().getDepositVector().get(Integer.parseInt(choosen_node) - 1));

                    break;
                }
                /**
                 * find by {cloumn}
                 */
                case "6":
                {
                    System.out.println(clear_console);

                    System.out.println("1) Find all by name_of_deposit");
                    System.out.println("2) Find all by storage_tome");
                    System.out.println("3) Find all by interest_rate");
                    System.out.println("\nq) To Exit");

                    String find_interface = input_data.nextLine();

                    switch (find_interface)
                    {
                        case "1":
                        {
                            System.out.println(clear_console);

                            System.out.println("\nEnter name_of_deposit");
                            String name_of_deposit = input_data.nextLine();

                            List<Deposit> buf_deposits = depositService.findDepositByName_of_deposit(name_of_deposit);

                            System.out.format(
                                    "%17s%15s%15s\n", "name_of_deposit", "storage_time", "interest_rate");

                            if (buf_deposits.isEmpty())
                            {
                                System.out.println("Dont finded nodes have this words");
                                input_data.nextLine();
                                break;
                            }

                            for (Deposit tmp : buf_deposits)
                            {
                                System.out.println(tmp.toString());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "2":
                        {
                            System.out.println(clear_console);

                            boolean ok_input = false;
                            int storage_time = 1;

                            while (!ok_input)
                            {
                                System.out.println("\nEnter storage_time");

                                try
                                {
                                    storage_time = Integer.parseInt(input_data.nextLine());
                                    if (storage_time >= 1)
                                        ok_input = true;
                                }
                                catch (Exception e)
                                {
                                    System.out.println("Exception menu.java insert Deposit: " + e);
                                }
                            }

                            List<Deposit> buf_deposits = depositService.findDepositByStorage_time(storage_time);

                            System.out.format(
                                    "%17s%15s%15s\n", "name_of_deposit", "storage_time", "interest_rate");

                            if (buf_deposits.isEmpty())
                            {
                                System.out.println("Dont finded nodes have this words");
                                input_data.nextLine();
                                break;
                            }

                            for (Deposit tmp : buf_deposits)
                            {
                                System.out.println(tmp.toString());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "3":
                        {
                            System.out.println(clear_console);

                            System.out.println("\nEnter fathername");
                            String fathername = input_data.nextLine();

                            List<Client> buf_clients = clientService.findClientByFathername(fathername);

                            System.out.format(
                                    "%7s%15s%15s%25s%18s%24s\n", "name", "surname", "fathername",
                                    "serial/number pasport", "telephone", "address");

                            if (buf_clients.isEmpty())
                            {
                                System.out.println("Dont finded nodes have this words");
                                input_data.nextLine();
                                break;
                            }

                            for (Client tmp : buf_clients)
                            {
                                System.out.println(
                                        "   " + tmp.toString() + tmp.getHome().getAddress());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "q":
                        {
                            System.out.println(
                                    "End work with BankAccount table! Press [Enter] for continue.");
                            input_data.nextLine();
                            is_actionable_menu = false;
                            break;
                        }
                        default:
                        {
                            System.out.println("Wrong entered value, press [Enter] for try again!");
                            input_data.nextLine();
                            break;
                        }
                    }
                    break;
                }
                case "q":
                {
                    System.out.println(
                            "End work with Deposit table! Press [Enter] for continue.");
                    input_data.nextLine();
                    is_actionable_menu = false;
                    break;
                }
                default:
                {
                    System.out.println("Wrong entered value, press [Enter] for try again!");
                    input_data.nextLine();
                    break;
                }
            }
        }
    }

    private static void interaction_with_Home()
    {
        if (Singleton.getInstance().getHomeVector().isEmpty())
            homeService.findNoOneHomeById(home_id, home_id_step);

        boolean is_actionable_menu = true;

        String choose_menu_point = null;

        while (is_actionable_menu)
        {
            System.out.println(clear_console);

            System.out.format(
                    "%10s    %25s\n", "address", "number_of_flat");

            int i = 1;

            for (Home tmp : Singleton.getInstance().getHomeVector())
            {
                System.out.println(i + ") " + tmp.toString());
                i++;
            }

            System.out.println("\nHome page {" + home_page + "};");

            String output_menu_info = "\n\n"
                    + "1) Show next " + home_id_step + " nodes\n"
                    + "2) Show prev " + home_id_step + " nodes\n\n"
                    + "3) Insert node\n"
                    + "4) Update node\n"
                    + "5) Delete node\n\n"
                    + "6) Find node\n\n"
                    + "q) To Exit\n\n"
                    + "Enter number for choosing: ";

            System.out.println(output_menu_info);

            choose_menu_point = input_data.nextLine();

            switch (choose_menu_point)
            {
                /**
                 * next 5
                 */
                case "1":
                {
                    if (home_id + home_id_step - 1 < homeService.get_count_nodes())
                    {
                        home_id += home_id_step;
                        home_page++;
                        homeService.findNoOneHomeById(home_id,home_id_step);
                    }
                    break;
                }
                /**
                 * prev 5
                 */
                case "2":
                {
                    if (home_id - deposit_id_step > 0)
                    {
                        home_id -= home_id_step;
                        home_page--;
                        homeService.findNoOneHomeById(home_id,home_id_step);
                    }
                    break;
                }
                /**
                 * insert node
                 */
                case "3":
                {
                    System.out.println(clear_console);

                    System.out.format(
                            "%10s    %25s\n", "address", "number_of_flat");

                    i = 1;

                    for (Home tmp : Singleton.getInstance().getHomeVector())
                    {
                        System.out.println(i + ") " + tmp.toString());
                        i++;
                    }

                    System.out.println("\nany key) Continue INSERT");
                    System.out.println("\nq) To Exit");

                    String choosen_node = input_data.nextLine();

                    if (choosen_node.toLowerCase().contains("q"))
                    {
                        break;
                    }

                    String address, number_of_flat = null;

                    System.out.println("\nEnter address (for example Gorskij st. N)");
                    address = input_data.nextLine();

                    System.out.println("\nEnter number_of_flat");
                    number_of_flat = input_data.nextLine();

                    homeService.saveHome(new Home(address,number_of_flat));

                    break;
                }
                /**
                 * update node
                 */
                case "4":
                {
                    System.out.println(clear_console);

                    System.out.format(
                            "%10s    %25s\n", "address", "number_of_flat");

                    i = 1;

                    for (Home tmp : Singleton.getInstance().getHomeVector())
                    {
                        System.out.println(i + ") " + tmp.toString());
                        i++;
                    }

                    System.out.println("\nChoose node on # for UPDATE");
                    System.out.println("\nany number) To UPDATE node");
                    System.out.println("\nq) To Exit");

                    String choosen_node = input_data.nextLine();

                    if (choosen_node.toLowerCase().contains("q") || choosen_node.isEmpty() ||
                            Integer.parseInt(choosen_node) > i - 1 ||
                            Integer.parseInt(choosen_node) < 1)
                    {
                        break;
                    }

                    boolean is_update_active = true;

                    while (is_update_active)
                    {
                        System.out.println("1) Update address");
                        System.out.println("2) Update number_of_flat");
                        System.out.println("4) Update all");
                        System.out.println("\nq) To Exit");

                        String update_interface = input_data.nextLine();

                        switch (update_interface)
                        {
                            case "1":
                            {
                                System.out.println("\nEnter address (for example Gorskij st. N)");
                                String address = input_data.nextLine();

                                Singleton.getInstance().getHomeVector().get(Integer.parseInt(choosen_node)-1).setAddress(address);
                                homeService.updateHome(Singleton.getInstance().getHomeVector().get(Integer.parseInt(choosen_node)-1));

                                break;
                            }
                            case "2":
                            {
                                System.out.println("\nEnter number_of_flat");
                                String number_of_flat = input_data.nextLine();

                                Singleton.getInstance().getHomeVector().get(Integer.parseInt(choosen_node)-1).setNumber_of_flat(number_of_flat);
                                homeService.updateHome(Singleton.getInstance().getHomeVector().get(Integer.parseInt(choosen_node)-1));

                                break;
                            }
                            case "3":
                            {
                                System.out.println("\nEnter address (for example Gorskij st. N)");
                                String address = input_data.nextLine();

                                System.out.println("\nEnter number_of_flat");
                                String number_of_flat = input_data.nextLine();

                                Singleton.getInstance().getHomeVector().get(Integer.parseInt(choosen_node)-1).setAddress(address);
                                Singleton.getInstance().getHomeVector().get(Integer.parseInt(choosen_node)-1).setNumber_of_flat(number_of_flat);
                                homeService.updateHome(Singleton.getInstance().getHomeVector().get(Integer.parseInt(choosen_node)-1));

                                break;
                            }
                            case "q":
                            {
                                System.out.println(
                                        "End UPDATE Home nodes! Press [Enter] for continue.");
                                input_data.nextLine();
                                is_update_active = false;
                                break;
                            }
                            default:
                            {
                                System.out.println(
                                        "Wrong entered value, press [Enter] for try again!");
                                input_data.nextLine();
                                break;
                            }
                        }
                    }

                    break;
                }
                /**
                 * delete node
                 */
                case "5":
                {
                    System.out.println(clear_console);

                    System.out.format(
                            "%10s    %25s\n", "address", "number_of_flat");

                    i = 1;

                    for (Home tmp : Singleton.getInstance().getHomeVector())
                    {
                        System.out.println(i + ") " + tmp.toString());
                        i++;
                    }

                    System.out.println("\nChoose node on # for DELETE");
                    System.out.println("\nany number) To DELETE node");
                    System.out.println("\nq) To Exit");

                    String choosen_node = input_data.nextLine();

                    if (choosen_node.toLowerCase().contains("q") || choosen_node.isEmpty() ||
                            Integer.parseInt(choosen_node) > i - 1 ||
                            Integer.parseInt(choosen_node) < 1)
                    {
                        break;
                    }

                    homeService.deleteHome(Singleton.getInstance().getHomeVector().get(Integer.parseInt(choosen_node) - 1));

                    break;
                }
                /**
                 * find by {cloumn}
                 */
                case "6":
                {
                    System.out.println(clear_console);

                    System.out.println("1) Find all by address");
                    System.out.println("2) Find all by number_of_flat");
                    System.out.println("\nq) To Exit");

                    String find_interface = input_data.nextLine();

                    switch (find_interface)
                    {
                        case "1":
                        {
                            System.out.println(clear_console);

                            System.out.println("\nEnter address (for example Gorskij st. N)");
                            String address = input_data.nextLine();

                            List<Home> buf_homes = homeService.findHomeByAddress(address);

                            System.out.format(
                                    "%10s    %25s\n", "address", "number_of_flat");

                            if (buf_homes.isEmpty())
                            {
                                System.out.println("Dont finded nodes have this words");
                                input_data.nextLine();
                                break;
                            }

                            for (Home tmp : buf_homes)
                            {
                                System.out.println(tmp.toString());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "2":
                        {
                            System.out.println(clear_console);

                            System.out.println("\nEnter number_of_flat");
                            String number_of_flat = input_data.nextLine();

                            List<Home> buf_homes = homeService.findHomeByNumber_of_flat(number_of_flat);

                            System.out.format(
                                    "%10s    %25s\n", "address", "number_of_flat");

                            if (buf_homes.isEmpty())
                            {
                                System.out.println("Dont finded nodes have this words");
                                input_data.nextLine();
                                break;
                            }

                            for (Home tmp : buf_homes)
                            {
                                System.out.println(tmp.toString());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "q":
                        {
                            System.out.println(
                                    "End work with Home table! Press [Enter] for continue.");
                            input_data.nextLine();
                            is_actionable_menu = false;
                            break;
                        }
                        default:
                        {
                            System.out.println("Wrong entered value, press [Enter] for try again!");
                            input_data.nextLine();
                            break;
                        }
                    }
                    break;
                }
                case "q":
                {
                    System.out.println(
                            "End work with Home table! Press [Enter] for continue.");
                    input_data.nextLine();
                    is_actionable_menu = false;
                    break;
                }
                default:
                {
                    System.out.println("Wrong entered value, press [Enter] for try again!");
                    input_data.nextLine();
                    break;
                }
            }
        }
    }

    public menu() throws IOException, ParseException
    {
        HibernateSessionFactoryUtil.getSessionFactory().openSession();

        boolean is_actionable_menu = true;

        String output_menu_info = "\nChoose table which you want to get\n\n"
            + "1) Home\n"
            + "2) Deposit\n"
            + "3) Client\n"
            + "4) BankAccount\n\n"
            + "q) To Exit\n\n"
            + "Enter number for choosing: ";

        String choose_menu_point = null;

        while (is_actionable_menu)
        {
            /**
             * /usr/bin/clear
             * /usr/bin/clear_console
             * Runtime.getRuntime().exec("/usr/bin/clear");
             * System.out.flush();
             */

            System.out.println(clear_console);
            System.out.println(output_menu_info);

            choose_menu_point = input_data.nextLine();

            switch (choose_menu_point)
            {
                case "1":
                {
                    interaction_with_Home();
                    break;
                }
                case "2":
                {
                    interaction_with_Deposit();
                    break;
                }
                case "3":
                {
                    interaction_with_Client();
                    break;
                }
                case "4":
                {
                    interaction_with_BankAccount();
                    break;
                }
                case "q":
                {
                    System.out.println("Goodbye...");
                    is_actionable_menu = false;
                    break;
                }
                default:
                {
                    System.out.println("Wrong entered value, press [Enter] for try again!");
                    input_data.nextLine();
                    break;
                }
            }
        }
    }
}