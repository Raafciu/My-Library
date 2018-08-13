package business.book;

import business.category.Category;
import util.converter.YesNoEnumConverter;
import util.enums.YesNoEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "ksiazka_seq", sequenceName = "ksiazka_seq", allocationSize = 1)
@Table(name = "ksiazka")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ksiazka_seq")
    @Column(name = "book_id", nullable = false, unique = true, precision = 9)
    private long id;

    @Column(name = "isbn", length = 13, nullable = false)
    private String isbn;

    @Column(name = "tytul", length = 50)
    private String title;

    @Column(name = "autor", length = 50)
    private String author;

    @Column(name = "ilosc_stron", precision = 4)
    private int pages;

    @Column(name = "cena", precision = 9, scale = 2)
    private BigDecimal price;

    @Column(name = "dostepnosc", length = 1)
    @Convert(converter = YesNoEnumConverter.class)
    private YesNoEnum isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kategoria_id", referencedColumnName = "kategoria_id")
    private Category bookCategory;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public YesNoEnum getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(YesNoEnum isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Category getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(Category bookCategory) {
        this.bookCategory = bookCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                pages == book.pages &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author) &&
                Objects.equals(price, book.price) &&
                isAvailable == book.isAvailable &&
                Objects.equals(bookCategory, book.bookCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn, title, author, pages, price, isAvailable, bookCategory);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", bookCategory=" + bookCategory +
                '}';
    }
}
