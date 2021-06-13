package management;

import management.interfacemanagement.InputOutput;
import model.Bill;
import model.Customer;
import model.Product;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class InputOutputBill implements InputOutput<Bill> {
    @Override
    public Bill input() {
        Scanner scanner = new Scanner(System.in);
        Bill bill = new Bill();
        CustomerManagement customerManagement = new CustomerManagement();
        ProductManagement productManagement = new ProductManagement();
        List<Customer> customerList = customerManagement.readFromFile("File\\customer.csv");
        List<Product> productList = productManagement.readFromFile("File\\product.csv");
        System.out.print("Enter id customer: ");
        String cusId;
        do {
            cusId = scanner.nextLine();
            if (!customerManagement.checkExistedId(cusId)) {
                System.out.print("Wrong customer information! Re input: ");
            }
        } while (!customerManagement.checkExistedId(cusId));
        int indexCustomer = customerManagement.findById(cusId);
        Customer customer = customerList.get(indexCustomer);
        bill.setCustomer(customer);
        System.out.println(customer);
        System.out.print("Enter product id: ");
        String productId;
        do {
            productId = scanner.nextLine();
            if (!productManagement.checkExistedId(productId)) {
                System.out.print("Wrong product information! Re input: ");
            }
        } while (!productManagement.checkExistedId(productId));
        int indexProduct = productManagement.findById(productId);
        Product product = productList.get(indexProduct);
        System.out.println(product);
        System.out.print("Enter quantity: ");
        int quantity = -1;
        while (quantity == -1) {
            try {
                quantity = scanner.nextInt();
                while (quantity > product.getQuantity()) {
                    if (quantity > product.getQuantity()) {
                        System.out.print("Too much!! Re input: ");
                        quantity = scanner.nextInt();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.print("Wrong type!! Re input: ");
            } finally {
                scanner.nextLine();
            }
        }
        Product inventoryProduct = new Product(product.getId(), product.getName(), product.getQuantity() - quantity, product.getPrice());
        productList.set(indexProduct, inventoryProduct);
        productManagement.writeToFile("File\\product.csv");
        product.setQuantity(quantity);
        bill.setProduct(product);
        for (Product product1 : productList) {
            System.out.println(product1);
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
