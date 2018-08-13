package business.book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//@Stateless
public class BookDAO{

//    @PersistenceContext(unitName = "biblioPU")
    private EntityManager entityManager;

    public void persist(Book book) {
        entityManager.persist(book);
        entityManager.flush();
    }

    public List<Book> getAll() {
        return entityManager.createQuery("From Book", Book.class).getResultList();
    }

    public void remove(Book book) {
        entityManager.remove(book);
    }

    public void merge(Book book) {
        entityManager.merge(book);
    }
}
