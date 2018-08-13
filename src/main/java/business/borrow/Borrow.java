package business.borrow;

import business.book.Book;
import business.user.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "wypozyczenia_seq", sequenceName = "wypozyczenia_seq", allocationSize = 1)
@Table(name = "wypozyczenia")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wypozyczenia_seq")
    @Column(name = "wypozyczenia_id", nullable = false, unique = true, precision = 9)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrow borrow = (Borrow) o;
        return id == borrow.id &&
                Objects.equals(user, borrow.user) &&
                Objects.equals(book, borrow.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, book);
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                '}';
    }
}
