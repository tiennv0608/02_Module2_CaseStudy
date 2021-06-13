package management;

import com.sun.scenario.effect.impl.prism.ps.PPSBlend_ADDPeer;
import file.ReadAndWriteBill;
import management.interfacemanagement.Management;
import model.Bill;
import model.Customer;
import model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BillManagement {
    private List<Bill> billList;

    public BillManagement() {
        billList = new ArrayList<>();
    }

    public void add(Bill bill) {
        this.billList.add(bill);
    }

    public List<Bill> findAll() {
        return this.billList;
    }

    public List<Bill> searchByCustomerName(String name) {
        List<Bill> searchingList = new ArrayList<>();
        for (Bill bill : this.billList) {
            if (bill.getCustomer().getFullName().equals(name)) {
                searchingList.add(bill);
            }
        }
        return searchingList;
    }

    public List<Bill> searchByProductName(String name) {
        List<Bill> searchingList = new ArrayList<>();
        for (Bill bill : this.billList) {
            if (bill.getProduct().getName().equals(name)) {
                searchingList.add(bill);
            }
        }
        return searchingList;
    }

    public void sort() {
        Collections.sort(findAll(), new Comparator<Bill>() {
            public int compare(Bill bill1, Bill bill2) {
                return bill1.getCustomer().getFullName().compareTo(bill2.getCustomer().getFullName());
            }
        });
    }

    public boolean checkExistedId(String id) {
        for (Bill bill : billList) {
            if (bill.getBillId().equals(id)) {
                return true;
            }
        }
        return false;
    }


    public List<Bill> readFromFile(String path) {
        ReadAndWriteBill readAndWriteBill = new ReadAndWriteBill();
        this.billList = readAndWriteBill.readFromFile(path);
        return this.billList;
    }


    public void writeToFile(String path) {
        ReadAndWriteBill readAndWriteBill = new ReadAndWriteBill();
        readAndWriteBill.writeToFile(path, findAll());
    }
}
