package management;

import management.interfacemanagement.InputOutput;
import model.Bill;
import model.Customer;
import model.Product;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputOutputBill implements InputOutput<Bill> {
    @Override
    public Bill input() {
        Scanner scanner = new Scanner(System.in);
        Bill bill = new Bill();
        CustomerManagement customerManagement = new CustomerManagement();
        ProductManagement productManagement = new ProductManagement();
        System.out.print("Enter your id: ");
        String cusId = scanner.nextLine();
        int indexCustomer = customerManagement.findById(cusId);
        if (indexCustomer != -1) {
            bill.setCustomer(customerManagement.findAll().get(indexCustomer));
        } else {
            Customer customer = new InputOutputCustomer().input();
            customer.setCusId(cusId);
            bill.setCustomer(customer);
        }
        System.out.print("Enter product id: ");
        String productId = scanner.nextLine();
        int indexProduct = productManagement.findById(productId);
        if (indexProduct != -1) {
            Product product = productManagement.findAll().get(indexProduct);
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            product.setQuantity(quantity);
            bill.setProduct(product);
        } else {
            Product product = new InputOutputProduct().input();
            product.setId(productId);
            bill.setProduct(product);
        }
        return bill;
    }

    @Override
    public void output(Bill bill) {
        System.out.println("Customer: " + bill.getCustomer().getFullName() + ", address: " + bill.getCustomer().getAddress() +
                ", product: " + bill.getProduct().getName() + ", quantity: " + bill.getProduct().getQuantity() + ", price: " + bill.getProduct().getPrice() +
                ", total: " + bill.getTotal());
    }
}
