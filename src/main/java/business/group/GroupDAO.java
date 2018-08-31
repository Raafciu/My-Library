package business.group;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class GroupDAO {

    @PersistenceContext(name = "biblioPU")
    EntityManager entityManager;

    public List<Group> getAll() {
        return entityManager.createQuery("FROM Group", Group.class).getResultList();
    }

    public void persist(Group group){
        entityManager.persist(group);
        entityManager.flush();
    }

    public void merge(Group group){
        entityManager.merge(group);
        entityManager.flush();
    }

    public void remove(Group group){
        entityManager.remove(group);
        entityManager.flush();
    }
}
