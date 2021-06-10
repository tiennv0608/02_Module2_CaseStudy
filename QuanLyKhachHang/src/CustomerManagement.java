import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerManagement implements Management<Customer> {
    private List<Customer> list;
    Scanner scanner = new Scanner(System.in);

    public CustomerManagement() {
        list = new ArrayList<>();
    }

    public List<Customer> getList() {
        return list;
    }

    public void setList(List<Customer> list) {
        this.list = list;
    }

    @Override
    public void add(Customer customer) {
        list.add(customer);
    }

    @Override
    public void delete(Customer customer) {
        list.remove(customer);
    }

    public boolean checkById(String cusId) {
        if (searchByID(cusId) != -1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void edit(Customer customer) {

    }

    @Override
    public void show(List<Customer> list) {
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    @Override
    public int searchByID(String cusId) {
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getCusId().equals(cusId)) {
                return i;
            }
        }
        return -1;
    }

    public boolean checkExistedId(String id) {
        for (Customer customer : this.list) {
            if (customer.getCusId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public List<Customer> readFromFile(String path) {
        this.list = ReadAndWriteFile.readFromFile(path);
        System.out.println("Read complete");
        return this.list;
    }

    public void writeToFile(String path){
        ReadAndWriteFile.writeToFile(path,this.list);
        System.out.println("Write complete!");
    }
}
