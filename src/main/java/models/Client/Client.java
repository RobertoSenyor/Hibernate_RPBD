package models.Client;

import models.BankAccount.BankAccount;
import models.Home.Home;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table (name = "client")
public class Client
{
    /**
     *                                          Table "public.client"
     *       Column       |          Type          | Collation | Nullable |              Default
     * -------------------+------------------------+-----------+----------+------------------------------------
     *  id                | integer                |           | not null | nextval('client_id_seq'::regclass)
     *  name              | character varying(255) |           | not null |
     *  surname           | character varying(255) |           | not null |
     *  fathername        | character varying(255) |           | not null |
     *  serial_of_pasport | numeric(4,0)           |           | not null |
     *  number_of_pasport | numeric(6,0)           |           | not null |
     *  telephone         | character varying(100) |           | not null |
     *  address_id        | integer                |           |          |
     */

    @Id
    @Column (name = "id")
    @SequenceGenerator(name = "client_SEQ", sequenceName = "CLIENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_SEQ")
    private int id;

    @Column (name = "name")
    private String name;

    @Column (name = "surname")
    private String surname;

    @Column (name = "fathername")
    private String fathername;

    @Column (name = "serial_of_pasport")
    private int serial_of_pasport;

    @Column (name = "number_of_pasport")
    private int number_of_pasport;

    @Column (name = "telephone")
    private String telephone;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Home home;

    @OneToMany (mappedBy = "client", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<BankAccount> bankAccounts;

    protected Client(){}

    public Client(
            String name,
            String surname,
            String fathername,
            int serial_of_pasport,
            int number_of_pasport,
            String telephone
    ){
        this.name = name;
        this.surname = surname;
        this.fathername = fathername;
        this.serial_of_pasport = serial_of_pasport;
        this.number_of_pasport = number_of_pasport;
        this.telephone = telephone;
        bankAccounts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getFathername() {
        return fathername;
    }

    public void setSerial_of_pasport(int serial_of_pasport) {
        this.serial_of_pasport = serial_of_pasport;
    }

    public int getSerial_of_pasport() {
        return serial_of_pasport;
    }

    public void setNumber_of_pasport(int number_of_pasport) {
        this.number_of_pasport = number_of_pasport;
    }

    public int getNumber_of_pasport() {
        return number_of_pasport;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public Home getHome() {
        return home;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void addBankAccount(BankAccount bankAccount)
    {
        bankAccount.setClient(this);
        bankAccounts.add(bankAccount);
    }

    public void removeBankAccount(BankAccount bankAccount)
    {
        bankAccounts.remove(bankAccount);
    }

    @Override
    public String toString()
    {
        return  "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", fathername='" + fathername + '\'' +
                ", serial_of_pasport=" + serial_of_pasport +
                ", number_of_pasport=" + number_of_pasport +
                ", telephone" + telephone + '\'';

//        return "models.Client{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", surname='" + surname + '\'' +
//                ", fathername='" + fathername + '\'' +
//                ", serial_of_pasport=" + serial_of_pasport +
//                ", number_of_pasport=" + number_of_pasport +
//                ", telephone" + telephone + '\'' +
//                "}";
    }
}
