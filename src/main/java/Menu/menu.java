package Menu;

import java.io.IOException;
import java.util.Scanner;

public class menu
{
    private String clear_console = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    private static Scanner input_data = new Scanner(System.in);

    static private void interaction_with_BankAccount()
    {
        boolean is_actionable_menu = true;

        String choose_menu_point = null;

        while (is_actionable_menu)
        {
            System.out.println("pls? input right data");

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
                case "q":
                {
                    System.out.println("Goodbye...");
                    is_actionable_menu = false;
                    break;
                }
                default:
                {
                    System.out.println("default");
                    break;
                }
            }
        }
    }

    public menu() throws IOException
    {
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
             */
//            Runtime.getRuntime().exec("/usr/bin/clear");
            System.out.println(clear_console);
            System.out.println(output_menu_info);

            choose_menu_point = input_data.nextLine();

            switch (choose_menu_point)
            {
                case "1":
                {
                    System.out.println("case1");
                    input_data.nextLine();
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
                    System.out.println("case4");
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
                    break;
                }
            }
        }
    }
}
