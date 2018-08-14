package util.interfaces;

import java.util.List;

public interface ICRUDOperation<T extends Object> {

    List<T> getAll();

    void persist(T object);

    void merge(T object);

    void remove(T object);
}
