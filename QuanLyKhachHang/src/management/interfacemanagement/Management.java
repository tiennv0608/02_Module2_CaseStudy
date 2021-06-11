package management.interfacemanagement;

import java.util.List;

public interface Management<T> {
    void add(T t);
    void delete(String id);
    void edit(String id);
    void show(List<T> list);
    int searchByID(String id);
    void sort();
    boolean checkExistedId(String id);
}
