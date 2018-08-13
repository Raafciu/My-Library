package business.group;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "grupa_seq", sequenceName = "grupa_seq", allocationSize = 1)
@Table(name = "grupa")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grupa_seq")
    @Column(name = "grupa_id", nullable = false, unique = true, precision = 9)
    private long id;

    @Column(name = "nazwa")
    private String groupName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id &&
                Objects.equals(groupName, group.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupName);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
