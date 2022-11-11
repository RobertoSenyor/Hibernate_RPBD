package City;
import Home.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "city")
public class City {

    /**
     * id              | integer                |           | not null |
     * city_name       | character varying(128) |           | not null |
     * num_of_citizens | integer                |           | not null |
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "city_name")
    private String city_name;

    @Column(name = "num_of_citizens")
    private int num_of_citizens;

    @OneToMany(mappedBy = "home", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Home> homes;

    protected City() {
    }

    private City(String _city_name, int _num_of_citizens) {
        this.city_name = _city_name;
        this.num_of_citizens = _num_of_citizens;
        homes = new ArrayList<>();
    }

    public void addHome(Home home) {
        home.setCity(this);
        homes.add(home);
    }

    public void removeHome(Home home) {
        // TODO - работа с кэшем
        homes.remove(home);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setNum_of_citizens(int num_of_citizens) {
        this.num_of_citizens = num_of_citizens;
    }

    public int getNum_of_citizens() {
        return num_of_citizens;
    }

    public List<Home> getHomes() {
        return homes;
    }

    public void setHomes(List<Home> homes) {
        this.homes = homes;
    }

    @Override
    public String toString()
    {
        return "City{" + "id=" + id + ", city_name=" + city_name + ", num_of_citizens=" + num_of_citizens + "}";
    }
}