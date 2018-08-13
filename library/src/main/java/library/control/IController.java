package library.control;

import java.util.Map;
import java.util.Optional;

public interface IController<T extends Object> {

    Map<String, T> getAll();

    Optional<T> getById(String id);

    void persist(T object);

    void merge(T object);

    void remove(T object);
}
