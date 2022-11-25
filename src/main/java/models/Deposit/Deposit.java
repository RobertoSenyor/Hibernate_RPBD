package models.Deposit;

import models.BankAccount.BankAccount;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "deposit")
public class Deposit
{
    /**
     *                                  Table "public.deposit"
     *      Column      |  Type   | Collation | Nullable |               Default
     * -----------------+---------+-----------+----------+-------------------------------------
     *  id              | integer |           | not null | nextval('deposit_id_seq'::regclass)
     *  name_of_deposit | text    |           |          |
     *  storage_time    | integer |           | not null |
     *  interest_rate   | integer |           | not null |
     */

    @Id
    @Column (name = "id")
    @SequenceGenerator(name = "deposit_SEQ", sequenceName = "DEPOSIT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deposit_SEQ")
    private int id;

    @Column (name = "name_of_deposit")
    private String name_of_deposit;

    @Column (name = "storage_time")
    private int storage_time;

    @Column (name = "interest_rate")
    private int interest_rate;

    @OneToMany (mappedBy = "deposit", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BankAccount> bankAccounts;

    protected Deposit(){}

    public Deposit(
            String name_of_deposit,
            int storage_time,
            int interest_rate
    )
    {
        this.name_of_deposit = name_of_deposit;
        this.storage_time = storage_time;
        this.interest_rate = interest_rate;
        bankAccounts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setName_of_deposit(String name_of_deposit) {
        this.name_of_deposit = name_of_deposit;
    }

    public String getName_of_deposit() {
        return name_of_deposit;
    }

    public void setStorage_time(int storage_time) {
        this.storage_time = storage_time;
    }

    public int getStorage_time() {
        return storage_time;
    }

    public void setInterest_rate(int interest_rate) {
        this.interest_rate = interest_rate;
    }

    public int getInterest_rate() {
        return interest_rate;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void addBankAccount(BankAccount bankAccount)
    {
        bankAccount.setDeposit(this);
        bankAccounts.add(bankAccount);
    }

    public void removeBankAccount(BankAccount bankAccount)
    {
        bankAccounts.remove(bankAccount);
    }

    @Override
    public String toString()
    {
        String out_string_data = String.format("%20s%5d%5d", name_of_deposit,storage_time,interest_rate);

        return out_string_data;

//        return  "name_of_deposit='" + name_of_deposit + '\'' +
//                ", storage_time='" + storage_time + '\'' +
//                ", interest_rate='" + interest_rate + '\'';

//        return "models.Deposit{" +
//                "id=" + id +
//                ",name_of_deposit='" + name_of_deposit + '\'' +
//                ", storage_time=" + storage_time +
//                ", interest_rate=" + interest_rate +
//                "}";
    }
}