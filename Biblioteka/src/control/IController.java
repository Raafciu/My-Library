package control;

import control.exception.KeyNotFoundException;

import java.util.Map;
import java.util.Optional;

public interface IController<T extends Object> {

    Map<String,T> getAll();

    Optional<T> getById(String id) throws KeyNotFoundException;

    void persist(T object);

    void merge(T object);

    void remove(T object);
}
