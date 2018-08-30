package business.user;

import business.address.Address;
import business.group.Group;
import business.position.Position;
import business.role.Role;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "uzytkownik_seq", sequenceName = "uzytkownik_seq", allocationSize = 1)
@Table(name = "uzytkownik")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uzytkownik_seq")
    @Column(name = "user_id", nullable = false, unique = true, precision = 9)
    private long id;

    @Column(name = "pesel", length = 11, unique = true)
    private String pesel;

    @Column(name = "imie", length = 50)
    private String firstName;

    @Column(name = "nazwisko", length = 50)
    private String lastName;

    @Column(name = "wiek", length = 3)
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stanowisko_id")
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rola_id", referencedColumnName = "rola_id")
    private Role role;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "adres_id", referencedColumnName = "adres_id", unique = true)
    private Address address;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "uzytkownik_grupa",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "grupa_id", referencedColumnName = "grupa_id"))
    private List<Group> group;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Group> getGroup() {
        return group;
    }

    public void setGroup(List<Group> group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                age == user.age &&
                Objects.equals(pesel, user.pesel) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(position, user.position) &&
                Objects.equals(role, user.role) &&
                Objects.equals(address, user.address) &&
                Objects.equals(group, user.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pesel, firstName, lastName, age, position, role, address, group);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", pesel='" + pesel + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", position=" + position +
                ", role=" + role +
                ", address=" + address +
                ", group=" + group +
                '}';
    }
}
