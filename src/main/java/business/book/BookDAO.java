package business.book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BookDAO{

    @PersistenceContext(unitName = "biblioPU")
    private EntityManager entityManager;

    public List<Book> getAll() {
        return entityManager.createQuery("FROM Book", Book.class).getResultList();
    }

    public void persist(Book book) {
        entityManager.persist(book);
        entityManager.flush();
    }

    public void merge(Book book) {
        entityManager.merge(book);
        entityManager.flush();
    }

    public void remove(Book book) {
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
        entityManager.flush();
    }
}
