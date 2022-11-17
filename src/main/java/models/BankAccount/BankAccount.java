package models.BankAccount;

import models.Client.Client;
import models.Deposit.Deposit;
import jakarta.persistence.*;

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
    private String date_open;

    @Column (name = "date_close")
    private String date_close;

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
        this.date_open = date_open;
        this.date_close = date_close;
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

    public void setDate_open(String date_open) {
        this.date_open = date_open;
    }

    public String getDate_open() {
        return date_open;
    }

    public void setDate_close(String date_close) {
        this.date_close = date_close;
    }

    public String getDate_close() {
        return date_close;
    }

    public void setMoney_sum(int money_sum) {
        this.money_sum = money_sum;
    }

    public int getMoney_sum() {
        return money_sum;
    }

    @Override
    public String toString()
    {
        return "models.BankAccount{" +
                "id=" + id +
                ", number_of_account='" + number_of_account + '\'' +
                ", date_open='" + date_open + '\'' +
                ", date_close='" + date_close + '\'' +
                ", money_sum=" + money_sum +
                "}";
    }
}
