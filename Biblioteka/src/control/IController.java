package control;

import java.util.List;
import java.util.Map;

public interface IController<T extends Object> {

    Map<String,T> getAll();

    void persist(T object);

    void merge(T object);

    void remove(T object);
}
