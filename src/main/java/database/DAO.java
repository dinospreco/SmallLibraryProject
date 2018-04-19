package database;

import java.util.Map;

public interface DAO<T> {

    T get(int id);
    Map getAll();
    boolean add(T t);
    boolean update(T t);
    boolean delete(T t);

}
