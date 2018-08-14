package business.user;

import util.interfaces.ICRUDOperation;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequestScoped
public class UserDAO implements ICRUDOperation<User> {

    @PersistenceContext(unitName = "biblioPU")
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        return entityManager.createQuery("FROM User", User.class)
                .getResultList();
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    public void persist(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    public void merge(User user) {
        entityManager.merge(user);
        entityManager.flush();
    }

    @Override
    public void remove(User user) {
        entityManager.remove(user);
        entityManager.flush();
    }
}
