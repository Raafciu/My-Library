package business.user;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserDAO {

    @PersistenceContext(unitName = "biblioPU")
    private EntityManager entityManager;

    public List<User> getAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    public void persist(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    public void merge(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    public void remove(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
        entityManager.flush();
    }
}
