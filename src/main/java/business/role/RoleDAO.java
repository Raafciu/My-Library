package business.role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class RoleDAO {

    @PersistenceContext(name = "BiblioPU")
    EntityManager entityManager;

    public List<Role> getAll() {
        return entityManager.createQuery("FROM Role", Role.class).getResultList();
    }

    public void persist(Role role){
        entityManager.persist(role);
        entityManager.flush();
    }

    public void merge(Role role){
        entityManager.merge(role);
        entityManager.flush();
    }

    public void remove(Role role){
        entityManager.remove(role);
        entityManager.flush();
    }
}
