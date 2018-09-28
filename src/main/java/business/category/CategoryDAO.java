package business.category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryDAO {

    @PersistenceContext(unitName = "biblioPU")
    private EntityManager entityManager;

    public List<Category> getAll() {
        return entityManager.createQuery("FROM Category", Category.class).getResultList();
    }

    public void persist(Category category) {
        entityManager.persist(category);
        entityManager.flush();
    }

    public void merge(Category category) {
        entityManager.merge(category);
        entityManager.flush();
    }

    public void remove(Category category) {
        entityManager.remove(entityManager.contains(category) ? category : entityManager.merge(category));
        entityManager.flush();
    }
}
