package models.BankAccount;

import models.Client.Client;
import models.Deposit.Deposit;
import jakarta.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table (name = "bankaccount")
public class BankAccount
{
    /**
     *                                          Table "public.bankaccount"
     *       Column       |         Type          | Collation | Nullable |                 Default
     * -------------------+-----------------------+-----------+----------+-----------------------------------------
     *  id                | integer               |           | not null | nextval('bankaccount_id_seq'::regclass)
     *  client_id         | integer               |           |          |
     *  deposit_id        | integer               |           |          |
     *  number_of_account | character varying(34) |           |          |
     *  date_open         | date                  |           |          |
     *  date_close        | date                  |           |          |
     *  money_sum         | integer               |           |          |
     */

    @Id
    @Column (name = "id")
    @SequenceGenerator(name = "bankaccount_SEQ", sequenceName = "BANKACCOUNT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankaccount_SEQ")
    private int id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "client_id")
    private Client client;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "deposit_id")
    private Deposit deposit;

    @Column (name = "number_of_account")
    private String number_of_account;

    @Column (name = "date_open")
    private Date date_open;

    @Column (name = "date_close")
    private Date date_close;

    @Column (name = "money_sum")
    private int money_sum;

    private BankAccount(){}

    public BankAccount(
            String number_of_account,
            String date_open,
            String date_close,
            int money_sum
    )
    {
        this.number_of_account = number_of_account;

        String[] parts = date_open.split("-");

        this.date_open = new Date(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));

        String[] parts1 = date_close.split("-");

        this.date_close = new Date(Integer.parseInt(parts1[0]),Integer.parseInt(parts1[1]),Integer.parseInt(parts1[2]));

        this.money_sum = money_sum;
    }

    public int getId() {
        return id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setDeposit(Deposit depoosit) {
        this.deposit = deposit;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setNumber_of_account(String number_of_account) {
        this.number_of_account = number_of_account;
    }

    public String getNumber_of_account() {
        return number_of_account;
    }

    public void setDate_open(String date_open)
    {
        String[] parts = date_open.split("-");

        this.date_open = new Date(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
    }

    public Date getDate_open() {
        return date_open;
    }

    public void setDate_close(String date_close)
    {
        String[] parts1 = date_close.split("-");

        this.date_close = new Date(Integer.parseInt(parts1[0]),Integer.parseInt(parts1[1]),Integer.parseInt(parts1[2]));
    }

    public Date getDate_close() {
        return date_close;
    }

    public void setMoney_sum(int money_sum) {
        this.money_sum = money_sum;
    }

    public int getMoney_sum() {
        return money_sum;
    }

    public boolean isAfterOpenDate(String dateFrom) throws ParseException
    {
        return (date_open.after(new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom)));
    }

    public boolean isBeforeOpenDate(String dateTo) throws ParseException
    {
        return (date_open.before(new SimpleDateFormat("yyyy-MM-dd").parse(dateTo)));
    }

    public boolean isAfterCloseDate(String dateFrom) throws ParseException
    {
        return (date_close.after(new SimpleDateFormat("yyyy-MM-dd").parse(dateFrom)));
    }

    public boolean isBeforeCloseDate(String dateTo) throws ParseException
    {
        return (date_close.before(new SimpleDateFormat("yyyy-MM-dd").parse(dateTo)));
    }

    @Override
    public String toString()
    {
        return "models.BankAccount{" +
                "id=" + id +
                ", number_of_account='" + number_of_account + '\'' +
                ", date_open='" + new SimpleDateFormat("yyyy-MM-dd").format(date_open) + '\'' +
                ", date_close='" + new SimpleDateFormat("yyyy-MM-dd").format(date_close) + '\'' +
                ", money_sum=" + money_sum +
                "}";
    }
}
