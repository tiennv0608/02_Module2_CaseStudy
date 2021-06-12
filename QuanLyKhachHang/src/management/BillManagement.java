package management;

import management.interfacemanagement.Management;
import model.Bill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BillManagement implements Management<Bill> {
    private List<Bill> list;

    public BillManagement() {
        list = new ArrayList<>();
    }

    @Override
    public void add(Bill bill) {
        this.list.add(bill);
    }

    @Override
    public void delete(String id) {
        int index = findById(id);
        this.list.remove(index);
    }

    @Override
    public void edit(String id) {

    }

    @Override
    public List<Bill> findAll() {
        return this.list;
    }

    @Override
    public int findById(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getBillId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void sort() {
        Collections.sort(findAll(), new Comparator<Bill>() {
            @Override
            public int compare(Bill bill1, Bill bill2) {
                return bill1.getCustomer().getFullName().compareTo(bill2.getCustomer().getFullName());
            }
        });
    }

    @Override
    public boolean checkExistedId(String id) {
        for (Bill bill : list) {
            if (bill.getBillId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Bill> readFromFile(String path) {
        return null;
    }

    @Override
    public void writeToFile(String path) {

    }
}
