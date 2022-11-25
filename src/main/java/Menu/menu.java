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
import service.BankAccount.BankAccountService;
import service.Client.ClientService;
import service.Deposit.DepositService;
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
                        "%25s%19s%13s%15s\n", "number_of_account", "date_open", "date_close",
                        "money_sum");

                    i = 1;
                    for (BankAccount tmp : Singleton.getInstance().getBankAccountVector())
                    {
                        System.out.println(i + ") " + tmp.toString());
                        i++;
                    }

                    System.out.println("\nany key) Continue INSERT");
                    System.out.println("\nq) To Exit");

                    String choosen_node = input_data.nextLine();

                    if (choosen_node.toLowerCase().contains("q") || choosen_node.isEmpty())
                    {
                        break;
                    }

                    String number_of_account, name, surname, date_open, date_close,
                        name_of_deposit = null;
                    int money_sum;

                    System.out.println("\nEnter number_of_account");
                    number_of_account = input_data.nextLine();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat.setLenient(false);

                    System.out.println("\nEnter date_open (in format yyyy-MM-dd)");
                    date_open = input_data.nextLine();

                    try
                    {
                        dateFormat.parse(date_open);
                    }
                    catch (Exception e)
                    {
                        System.out.println(
                            "Exception menu.java interaction_with_BankAccount (date_open): " + e);
                        System.out.println("Setting define value date_open: 2001-01-01");
                        date_open = "2000-01-01";
                    }

                    System.out.println(
                        "\nEnter date_close (in format yyyy-MM-dd more than date_open)");
                    date_close = input_data.nextLine();

                    try
                    {
                        dateFormat.parse(date_close);
                    }
                    catch (Exception e)
                    {
                        System.out.println(
                            "Exception menu.java interaction_with_BankAccount (date_close): " + e);
                        date_close = "9999-01-01";
                    }

                    BankAccount buf_account =
                        new BankAccount(number_of_account, date_open, date_close, 1);

                    System.out.println("\nEnter money_sum");
                    money_sum = Integer.parseInt(input_data.nextLine());

                    while (!buf_account.setMoney_sum(money_sum))
                    {
                        System.out.println("\nEnter money_sum");
                        money_sum = Integer.parseInt(input_data.nextLine());
                    }

                    System.out.println("\nEnter name client");
                    name = input_data.nextLine();

                    System.out.println("\nEnter surname client");
                    surname = input_data.nextLine();

                    List<Client> buf_clients =
                        clientService.findClientByName_Surname(name, surname);

                    if (buf_clients.size() > 1)
                    {
                        System.out.println("\n Select client, which you want");
                        int j = 1;

                        for (Client tmp : buf_clients)
                        {
                            System.out.println(j + tmp.toString());
                            j++;
                        }

                        System.out.println("Enter logic position");
                        int buf_log_pos = Integer.parseInt(input_data.nextLine());

                        while (buf_log_pos < 0 || buf_log_pos > buf_clients.size())
                        {
                            System.out.println("Enter logic position");
                            buf_log_pos = Integer.parseInt(input_data.nextLine());
                        }

                        buf_account.setClient(buf_clients.get(buf_log_pos - 1));
                    }
                    else
                    {
                        buf_account.setClient(buf_clients.get(buf_clients.size() - 1));
                    }

                    System.out.println("\nEnter name_of_deposit");
                    name_of_deposit = input_data.nextLine();

                    List<Deposit> buf_deposits =
                        depositService.findDepositByName_of_deposit(name_of_deposit);

                    if (buf_deposits.size() > 1)
                    {
                        System.out.println("\n Select deposit, which you want");
                        int j = 1;

                        for (Deposit tmp : buf_deposits)
                        {
                            System.out.println(j + tmp.toString());
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
                    {
                        buf_account.setDeposit(buf_deposits.get(buf_deposits.size()));
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

                    if (choosen_node.toLowerCase().contains("q") || choosen_node.isEmpty())
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
                                System.out.println("\nEnter date_open (in format yyyy-MM-dd)");
                                String date_open = input_data.nextLine();

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
                                System.out.println("\nEnter date_close (in format yyyy-MM-dd)");
                                String date_close = input_data.nextLine();

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
                                        System.out.println(j + tmp.toString());
                                        j++;
                                    }

                                    System.out.println("Enter logic position");
                                    int buf_log_pos = Integer.parseInt(input_data.nextLine());

                                    while (buf_log_pos < 0 || buf_log_pos > buf_clients.size())
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
                                        System.out.println(j + tmp.toString());
                                        j++;
                                    }

                                    System.out.println("Enter logic position");
                                    int buf_log_pos = Integer.parseInt(input_data.nextLine());

                                    while (buf_log_pos < 0 || buf_log_pos > buf_clients.size())
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
                                        System.out.println(j + tmp.toString());
                                        j++;
                                    }

                                    System.out.println("Enter logic position");
                                    buf_log_pos = Integer.parseInt(input_data.nextLine());

                                    while (buf_log_pos < 0 || buf_log_pos > buf_deposits.size())
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

                                System.out.println("\nEnter date_open (in format yyyy-MM-dd)");
                                String date_open = input_data.nextLine();

                                System.out.println("\nEnter date_close (in format yyyy-MM-dd)");
                                String date_close = input_data.nextLine();

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
                                System.out.println("End UPDATE BankAccount nodes");
                                input_data.nextLine();
                                is_update_active = false;
                                break;
                            }
                            default:
                            {
                                System.out.println("Wrong entered value, try again!");
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

                    if (choosen_node.toLowerCase().contains("q") || choosen_node.isEmpty())
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

                    System.out.println("1) Find by number_of_account");
                    System.out.println("2) Find by date_open");
                    System.out.println("3) Find by date_close");
                    System.out.println("4) Find between date_open");
                    System.out.println("5) Find between date_close");
                    System.out.println("6) Find by money_sum");
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

                            for (BankAccount tmp : accountService.findBankAccountByNumber_of_account(number_of_account))
                            {
                                System.out.println(tmp.toString());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "2":
                        {
                            System.out.println("\nEnter date_open (in format yyyy-MM-dd)");
                            String date_open = input_data.nextLine();

                            System.out.println(clear_console);

                            for (BankAccount tmp : accountService.findBankAccountByDate_close(date_open))
                            {
                                System.out.println(tmp.toString());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "3":
                        {
                            System.out.println("\nEnter date_close (in format yyyy-MM-dd)");
                            String date_close = input_data.nextLine();

                            System.out.println(clear_console);

                            for (BankAccount tmp : accountService.findBankAccountByDate_open(date_close))
                            {
                                System.out.println(tmp.toString());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "4":
                        {
                            System.out.println("\nEnter dateFrom and dateTo (in format yyyy-MM-dd)");
                            String dateFrom = input_data.nextLine();
                            String dateTo = input_data.nextLine();

                            System.out.println(clear_console);

                            for (BankAccount tmp : accountService.findBankAccountByBetweenDate_open(dateFrom,dateTo))
                            {
                                System.out.println(tmp.toString());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "5":
                        {
                            System.out.println("\nEnter dateFrom and dateTo (in format yyyy-MM-dd)");
                            String dateFrom = input_data.nextLine();
                            String dateTo = input_data.nextLine();

                            System.out.println(clear_console);

                            for (BankAccount tmp : accountService.findBankAccountByBetweenDate_close(dateFrom,dateTo))
                            {
                                System.out.println(tmp.toString());
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "6":
                        {
                            System.out.println("\nEnter money_sum");
                            int money_sum = Integer.parseInt(input_data.nextLine());

                            System.out.println(clear_console);

                            for (BankAccount tmp : accountService.findBankAccountByMoney_sum(money_sum))
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
                                    System.out.println(j + tmp.toString());
                                    j++;
                                }

                                System.out.println("Enter logic position");
                                int buf_log_pos = Integer.parseInt(input_data.nextLine());

                                while (buf_log_pos < 0 || buf_log_pos > buf_clients.size())
                                {
                                    System.out.println("Enter logic position");
                                    buf_log_pos = Integer.parseInt(input_data.nextLine());
                                }

                                for (BankAccount tmp : buf_clients.get(buf_log_pos - 1).getBankAccounts())
                                {
                                    if (!Singleton.getInstance().getBankAccountVector().contains(tmp))
                                    {
                                        Singleton.getInstance().getBankAccountVector().add(tmp);
                                        System.out.println(tmp.toString() + " " + tmp.getClient().getName() + " " + tmp.getClient().getSurname() + " " + tmp.getDeposit().getName_of_deposit());
                                    }
                                }
                            }
                            else
                            {
                                for (BankAccount tmp : buf_clients.get(buf_clients.size() - 1).getBankAccounts())
                                {
                                    if (!Singleton.getInstance().getBankAccountVector().contains(tmp))
                                    {
                                        Singleton.getInstance().getBankAccountVector().add(tmp);
                                        System.out.println(tmp.toString() + " " + tmp.getClient().getName() + " " + tmp.getClient().getSurname() + " " + tmp.getDeposit().getName_of_deposit());
                                    }
                                }
                            }

                            input_data.nextLine();
                            break;
                        }
                        case "8":
                        {
                            break;
                        }
                        case "9":
                        {
                            break;
                        }
                        case "q":
                        {
                            System.out.println("End find in BankAccount table");
                            input_data.nextLine();
                            is_actionable_menu = false;
                            break;
                        }
                        default:
                        {
                            System.out.println("Wrong entered value, try again!");
                            input_data.nextLine();
                        }
                    }

                    break;
                }
                case "q":
                {
                    System.out.println("End work with BankAccount table");
                    input_data.nextLine();
                    is_actionable_menu = false;
                    break;
                }
                default:
                {
                    System.out.println("Wrong entered value, try again!");
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
                    System.out.println("case1");

                    break;
                }
                case "2":
                {
                    System.out.println("case2");
                    break;
                }
                case "3":
                {
                    System.out.println("case3");
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
                    System.out.println("Wrong entered value, try again!");
                    input_data.nextLine();
                    break;
                }
            }
        }
    }
}
