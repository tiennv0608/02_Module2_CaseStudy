import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomerManagement customerManagement = new CustomerManagement();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("--------MENU---------");
            System.out.println("1. Add Customer");
            System.out.println("2. Show customer list");
            System.out.println("3. Search customer");
            System.out.println("4. Edit customer");
            System.out.println("5. Delete customer");
            System.out.println("6. Read from file");
            System.out.println("7. Write to file");
            System.out.println("0. Exit");
            System.out.print("Enter your choice (0-7): ");
            int choice = -1;
            while (choice == -1) {
                try {
                    choice = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.err.print("Wrong type, re iput: ");
                } finally {
                    sc.nextLine();
                }
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter id: ");
                    String id = sc.nextLine();
                    if (customerManagement.checkExistedId(id)) {
                        System.err.println("Duplicated id!!!");
                    } else {
                        Customer customer = InputOutput.inputCustomer();
                        customer.setCusId(id);
                        customerManagement.add(customer);
                    }
                    break;
                case 2:
                    for (int i = 0; i<customerManagement.getList().size();i++){
                        InputOutput.outputCustomer(customerManagement.getList().get(i));
                    }
                    break;
                case 3:
                    System.out.print("Enter Id: ");
                    id = sc.nextLine();
                    int index = customerManagement.searchByID(id);
                    if (index != -1) {
                        InputOutput.outputCustomer(customerManagement.getList().get(index));
                    } else {
                        System.out.println("No customer was found!");
                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    customerManagement.readFromFile("customer.csv");
                    break;
                case 7:
                    customerManagement.writeToFile("newfile.csv");
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid");
            }
        }
    }
}
