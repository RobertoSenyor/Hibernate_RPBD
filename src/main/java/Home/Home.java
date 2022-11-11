package Home;
import City.*;
import jakarta.persistence.*;

@Entity
@Table (name = "home")
public class Home
{
    /**
     *  id             | bigint                 |           | not null | nextval('home_id_seq1'::regclass)
     *  city_id        | integer                |           |          |
     *  address        | character varying(255) |           | not null | nextval('home_id_seq'::regclass)
     *  number_of_flat | character varying(255) |           | not null |
     */

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private int id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "city_id")
    private City city;

    @Column (name = "address")
    private String address;

    @Column (name = "number_of_flat")
    private String number_of_flat;

    public Home(){}
}
