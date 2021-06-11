package management;

import file.ReadAndWriteFile;
import management.interfacemanagement.Management;
import model.Customer;

import java.util.*;

public class CustomerManagement implements Management<Customer> {
    private List<Customer> list;
    private ReadAndWriteFile readAndWriteFile;
    private InputOutputCustomer inOutCus;

    public CustomerManagement() {
        list = new ArrayList<>();
        readAndWriteFile = new ReadAndWriteFile();
        inOutCus = new InputOutputCustomer();
    }

    public InputOutputCustomer getInOutCus() {
        return inOutCus;
    }

    public void setInOutCus(InputOutputCustomer inOutCus) {
        this.inOutCus = inOutCus;
    }

    public ReadAndWriteFile getReadAndWriteFile() {
        return readAndWriteFile;
    }

    public void setReadAndWriteFile(ReadAndWriteFile readAndWriteFile) {
        this.readAndWriteFile = readAndWriteFile;
    }

    public List<Customer> getList() {
        return list;
    }

    public void setList(List<Customer> list) {
        this.list = list;
    }

    @Override
    public void add(Customer customer) {
        this.list.add(customer);
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
            this.inOutCus.output(this.getList().get(index));
            Customer customer = this.inOutCus.input();
            this.list.set(index, customer);
            System.out.println("Update successful");
        }
    }

    @Override
    public void show(List<Customer> list) {
        for (Customer customer : this.list) {
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

    public List<Customer> readFromFile(String path) {
        this.list = this.readAndWriteFile.readFromFile(path);
        return this.list;
    }

    public void writeToFile(String path) {
        this.readAndWriteFile.writeToFile(path, this.list);
    }
}