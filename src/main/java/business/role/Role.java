package business.role;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "rola_seq", sequenceName = "rola_seq", allocationSize = 1)
@Table(name = "rola")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rola_seq")
    @Column(name = "rola_id", nullable = false, unique = true, precision = 9)
    private long id;

    @Column(name = "nazwa", length = 50)
    private String roleName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id &&
                Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
