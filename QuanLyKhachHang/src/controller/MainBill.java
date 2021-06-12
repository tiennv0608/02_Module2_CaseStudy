package controller;

import management.BillManagement;
import management.CustomerManagement;
import management.InputOutputBill;
import management.ProductManagement;
import model.Bill;
import model.Customer;
import model.Product;

import java.util.List;
import java.util.Scanner;

public class MainBill {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BillManagement billManagement = new BillManagement();
        InputOutputBill inputOutputBill = new InputOutputBill();
        CustomerManagement customerManagement = new CustomerManagement();
        ProductManagement productManagement = new ProductManagement();
        List<Customer> customerList = customerManagement.readFromFile("File\\new file.csv");
        List<Product> productList = productManagement.readFromFile("File\\product.csv");
        Bill bill = new Bill("Biill001",customerList.get(2),productList.get(2));
        inputOutputBill.output(bill);

    }
}
