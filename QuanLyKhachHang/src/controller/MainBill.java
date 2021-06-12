package controller;

import management.BillManagement;
import model.Bill;

import java.util.Scanner;

public class MainBill {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BillManagement billManagement = new BillManagement();
        System.out.println("Enter bill id:");
        String id = scanner.nextLine();
        Bill bill = new Bill();

    }
}
