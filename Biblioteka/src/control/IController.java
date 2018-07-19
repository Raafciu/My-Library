package control;

import java.util.List;

public interface IController<T extends Object> {

    List<T> getAll();

    void persist(T object);

    void merge(T object);

    void remove(T object);
}
