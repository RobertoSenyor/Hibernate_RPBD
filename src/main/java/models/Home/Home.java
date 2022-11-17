package models.Home;
import models.Client.Client;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "home")
public class Home
{
    /**
     *  id             | bigint                 |           | not null | nextval('home_id_seq1'::regclass)
     *  address        | character varying(255) |           | not null | nextval('home_id_seq'::regclass)
     *  number_of_flat | character varying(255) |           | not null |
     */

    @Id
    @Column (name = "id")
    @SequenceGenerator(name = "home_SEQ", sequenceName = "HOME_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "home_SEQ")
    private int id;

    @Column (name = "address")
    private String address;

    @Column (name = "number_of_flat")
    private String number_of_flat;

    @OneToMany (mappedBy = "home", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Client> clients;

    protected Home(){}

    public Home(String address, String number_of_flat)
    {
        this.address = address;
        this.number_of_flat = number_of_flat;
        clients = new ArrayList<>();
    }

    public void addClient(Client client)
    {
        client.setHome(this);
        clients.add(client);
    }

    public void removeClient(Client client)
    {
        clients.remove(client);
    }

    public int getId() {
        return id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setNumber_of_flat(String number_of_flat) {
        this.number_of_flat = number_of_flat;
    }

    public String getNumber_of_flat() {
        return number_of_flat;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getClients() {
        return clients;
    }

    @Override
    public String toString()
    {
        return "models.Home{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", number_of_flat='" + number_of_flat + '\'' +
                "}";
    }
}
