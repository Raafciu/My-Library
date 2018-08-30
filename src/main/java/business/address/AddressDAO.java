package business.address;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class AddressDAO {

    @PersistenceContext(name = "BiblioPU")
    EntityManager entityManager;

    public List<Address> getAll() {
        return entityManager.createQuery("FROM Address", Address.class).getResultList();
    }

    public void persist(Address address) {
        entityManager.persist(address);
        entityManager.flush();
    }

    public void merge(Address address) {
        entityManager.merge(address);
        entityManager.flush();
    }

    public void remove(Address address) {
        entityManager.remove(address);
        entityManager.flush();
    }
}
