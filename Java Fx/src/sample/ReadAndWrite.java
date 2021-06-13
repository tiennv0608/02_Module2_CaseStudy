package sample;

import java.util.List;

public interface ReadAndWrite<T> {
    List<T> readFromFile(String path);
    void writeToFile(String path, List<T> list);
}
