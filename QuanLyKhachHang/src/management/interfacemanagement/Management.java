package management.interfacemanagement;

import java.util.List;

public interface Management<T> {
    void add(T t);
    void delete(String id);
    void edit(String id);
    List<T> findAll();
    int findById(String id);
    void sort();
    boolean checkExistedId(String id);
    List<T> readFromFile(String path);
    void writeToFile(String path);
}
