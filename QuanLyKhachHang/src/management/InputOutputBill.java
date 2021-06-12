package management;

import management.interfacemanagement.InputOutput;
import model.Bill;

public class InputOutputBill implements InputOutput<Bill> {
    @Override
    public Bill input() {
        return null;
    }

    @Override
    public void output(Bill bill) {
        System.out.println("Customer: " + bill.getCustomer().getFullName() + ", address: " + bill.getCustomer().getAddress() +
                ", product: " + bill.getProduct().getName() + ", quantity: " + bill.getProduct().getQuantity() + ", price: " + bill.getProduct().getPrice() +
                ", total: " + bill.getTotal());
    }
}
