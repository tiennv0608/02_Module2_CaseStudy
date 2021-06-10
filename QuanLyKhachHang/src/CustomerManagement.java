import java.util.*;

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
    public void delete(String cusId) {
        int index = searchByID(cusId);
        this.list.remove(index);
    }

    @Override
    public void edit(String cusId) {
        int index = searchByID(cusId);
        if (index == -1) {
            System.out.println("No customer was found!");
        } else {
            InputOutput.outputCustomer(this.getList().get(index));
            Customer customer = InputOutput.inputCustomer();
            this.getList().set(index, customer);
            System.out.println("Update successful");
        }
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
            if (this.getList().get(i).getCusId().equals(cusId)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void sort() {
        Collections.sort(this.list, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o1.getCusId().compareTo(o2.getCusId());
            }
        });
    }

    public List<Customer> searchByAgeRange(int lowerAge, int higherAge) {
        List<Customer> searchList = new ArrayList<>();
        for (Customer customer : this.list) {
            if (customer.getAge() <= higherAge && customer.getAge() >= lowerAge) {
                searchList.add(customer);
            }
        }
        return searchList;
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

    public void writeToFile(String path) {
        ReadAndWriteFile.writeToFile(path, this.list);
        System.out.println("Write complete!");
    }
}
