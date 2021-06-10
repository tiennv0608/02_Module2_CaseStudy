import java.util.List;

public interface Management<T> {
    void add(T t);
    void delete(T t);
    void edit(T t);
    void show(List<T> list);
    int searchByID(String cusId);
}
