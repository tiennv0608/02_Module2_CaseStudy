import java.util.List;

public interface Management<T> {
    void add(T t);
    void delete(String cusId);
    void edit(String cusId);
    void show(List<T> list);
    int searchByID(String cusId);
    void sort();
}
