package sample;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CustomerManagement implements Management<Customer> {
    private List<Customer> list;

    public CustomerManagement() {
        list = new ArrayList<>();
    }

    @Override
    public void add(Customer customer) {
        this.list.add(customer);
    }

    @Override
    public void delete(String cusId) {
        int index = findById(cusId);
        this.list.remove(index);
    }

    @Override
    public void edit(String cusId) {
        InputOutputCustomer inOutCus = new InputOutputCustomer();
        int index = findById(cusId);
        if (index == -1) {
            System.out.println("No customer was found!");
        } else {
            inOutCus.output(this.findAll().get(index));
            Customer customer = inOutCus.input();
            customer.setCusId(cusId);
            this.list.set(index, customer);
            System.out.println("Update successful");
        }
    }

    @Override
    public List<Customer> findAll() {
        return this.list;
    }

    @Override
    public int findById(String cusId) {
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getCusId().equals(cusId)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void sort() {
        Collections.sort(findAll(), new Comparator<Customer>() {
            @Override
            public int compare(Customer customer1, Customer customer2) {
                if (customer1.getAge() == customer2.getAge())
                    return customer1.getFullName().compareTo(customer2.getFullName());
                return customer1.getAge() - customer2.getAge();
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

    public List<Customer> searchByAddress(String address) {
        List<Customer> searchList = new ArrayList<>();
        for (Customer customer : this.list) {
            if (customer.getAddress().equals(address)) {
                searchList.add(customer);
            }
        }
        return searchList;
    }

    @Override
    public boolean checkExistedId(String id) {
        for (Customer customer : this.list) {
            if (customer.getCusId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Customer> readFromFile(String path) {
        ReadAndWriteCustomer readAndWriteCustomer = new ReadAndWriteCustomer();
        this.list = readAndWriteCustomer.readFromFile(path);
        return this.list;
    }

    @Override
    public void writeToFile(String path) {
        ReadAndWriteCustomer readAndWriteCustomer = new ReadAndWriteCustomer();
        readAndWriteCustomer.writeToFile(path, this.list);
    }
}
