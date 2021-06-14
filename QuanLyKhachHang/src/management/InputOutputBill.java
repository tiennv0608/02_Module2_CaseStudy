package management;

import controller.Main;
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
        List<Customer> customerList = customerManagement.readFromFile(Main.CUSTOMER_PATH);
        List<Product> productList = productManagement.readFromFile(Main.PRODUCT_PATH);
        System.out.print("Enter id customer: ");
        String cusId;
        boolean check;
        do {
            cusId = scanner.nextLine();
            check = customerManagement.checkExistedId(cusId);
            if (!check) {
                System.out.print("Wrong customer information! Re input: ");
            } else {
                int indexCustomer = customerManagement.findById(cusId);
                System.out.print("Are you " + customerList.get(indexCustomer).getFullName() + " " + customerList.get(indexCustomer).getPhone() + ": ");
                String answer = scanner.nextLine();
                if (answer.equals("Y")) {
                    Customer customer = customerList.get(indexCustomer);
                    bill.setCustomer(customer);
                } else {
                    check = false;
                    System.out.print("Enter your id: ");
                }
            }
        } while (!check);


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
        productManagement.writeToFile(Main.PRODUCT_PATH);
        product.setQuantity(quantity);
        bill.setProduct(product);
        return bill;
    }

    @Override
    public void output(Bill bill) {
        System.out.println("Customer: " + bill.getCustomer().getFullName() + ", address: " + bill.getCustomer().getAddress() +
                ", product: " + bill.getProduct().getName() + ", quantity: " + bill.getProduct().getQuantity() + ", price: " + bill.getProduct().getPrice() +
                ", total: " + bill.getTotal());
    }
}
