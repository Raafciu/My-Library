package business.category;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "kategoria_ksiazki_seq", sequenceName = "kategoria_ksiazki_seq", allocationSize = 1)
@Table(name = "kategoria_ksiazki")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kategoria_ksiazki_seq")
    @Column(name = "kategoria_id", nullable = false, unique = true, precision = 9)
    private long id;

    @Column(name = "nazwa", length = 50)
    private String categoryName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category that = (Category) o;
        return id == that.id &&
                Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
