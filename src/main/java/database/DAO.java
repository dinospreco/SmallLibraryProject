package database;

import java.util.Map;

public interface DAO<T> {

    T get(String sqlWhereValue); //Example sqlWhereValue = "id = 1"
    Map getAll();
    boolean add(T t);
    boolean update(T t);
    boolean delete(T t);

}
