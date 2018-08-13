package business.address;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "adres_seq", sequenceName = "adres_seq", allocationSize = 1)
@Table(name = "adres")
public class Address {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "adres_seq")
    @Column(name = "adres_id", precision = 9, nullable = false, unique = true)
    private long id;

    @Column(name = "miasto", length = 50)
    private String city;

    @Column(name = "ulica", length = 50)
    private String street;

    @Column(name = "nr_budynku", precision = 3)
    private int numberOfBuilding;

    @Column(name = "kraj", length = 50)
    private String country;

    @Column(name = "kod_pocztowy", length = 6)
    private String postalCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumberOfBuilding() {
        return numberOfBuilding;
    }

    public void setNumberOfBuilding(int numberOfBuilding) {
        this.numberOfBuilding = numberOfBuilding;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id &&
                numberOfBuilding == address.numberOfBuilding &&
                Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) &&
                Objects.equals(country, address.country) &&
                Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, street, numberOfBuilding, country, postalCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numberOfBuilding=" + numberOfBuilding +
                ", country='" + country + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
