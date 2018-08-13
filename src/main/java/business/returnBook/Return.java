package business.returnBook;

import business.borrow.Borrow;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "zwrot_seq", sequenceName = "zwrot_seq", allocationSize = 1)
@Table(name = "zwrot")
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "zwrot_seq")
    @Column(name = "zwrot_id", nullable = false, unique = true, precision = 9)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wypozyczenia_id", referencedColumnName = "wypozyczenia_id")
    private Borrow borrow;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Borrow getBorrow() {
        return borrow;
    }

    public void setBorrow(Borrow borrow) {
        this.borrow = borrow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Return aReturn = (Return) o;
        return id == aReturn.id &&
                Objects.equals(borrow, aReturn.borrow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, borrow);
    }

    @Override
    public String toString() {
        return "Return{" +
                "id=" + id +
                ", borrow=" + borrow +
                '}';
    }
}
