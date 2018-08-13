package business.position;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "stanowisko_seq", sequenceName = "stanowisko_seq", allocationSize = 1)
@Table(name = "stanowisko")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stanowisko_seq")
    @Column(name = "stanowisko_id", nullable = false, unique = true, precision = 9)
    private long id;

    @Column(name = "nazwa", length = 50)
    private String positionName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return id == position.id &&
                Objects.equals(positionName, position.positionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, positionName);
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", positionName='" + positionName + '\'' +
                '}';
    }
}
