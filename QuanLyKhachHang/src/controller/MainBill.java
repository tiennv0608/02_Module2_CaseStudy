package controller;

import management.BillManagement;
import management.CustomerManagement;
import management.InputOutputBill;
import management.ProductManagement;
import model.Bill;
import model.Customer;
import model.Product;
import model.Validation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainBill {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BillManagement billManagement = new BillManagement();
        InputOutputBill inputOutputBill = new InputOutputBill();
        while (true) {
            menu();
            System.out.print("Enter your choice: ");
            int choice = -1;
            while (choice == -1) {
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.print("Wrong type, re input: ");
                } finally {
                    scanner.nextLine();
                }
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter bill id: ");
                    String id = scanner.nextLine();
                    if (billManagement.checkExistedId(id)) {
                        System.out.println("Duplicated id!!!");
                    } else {
                        Bill bill = inputOutputBill.input();
                        bill.setBillId(id);
                        billManagement.add(bill);
                    }
                    break;
                case 2:
                    for (Bill bill : billManagement.findAll()) {
                        inputOutputBill.output(bill);
                    }
                    break;
            }
        }
    }

    static void menu() {
        System.out.println("--------Menu----------");
        System.out.println("1. Add bill");
        System.out.println("2. Show bill");
    }
}