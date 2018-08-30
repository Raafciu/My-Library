package business.position;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class PositionDAO {

    @PersistenceContext(name = "BiblioPU")
    private EntityManager entityManager;


    public List<Position> getAll() {
        return entityManager.createQuery("FROM Position", Position.class).getResultList();
    }

    public void persist(Position position) {
        entityManager.persist(position);
        entityManager.flush();
    }

    public void merge(Position position) {
        entityManager.merge(position);
        entityManager.flush();
    }

    public void remove(Position position) {
        entityManager.remove(position);
        entityManager.flush();
    }
}
