import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomerManagement customerManagement = new CustomerManagement();
        Scanner sc = new Scanner(System.in);
        while (true) {
            menu();
            System.out.print("Enter your choice (0-8): ");
            int choice = -1;
            while (choice == -1) {
                try {
                    choice = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.print("Wrong type, re input: ");
                } finally {
                    sc.nextLine();
                }
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter id: ");
                    String id = sc.nextLine();
                    if (customerManagement.checkExistedId(id)) {
                        System.out.println("Duplicated id!!!");
                    } else {
                        Customer customer = InputOutput.inputCustomer();
                        customer.setCusId(id);
                        customerManagement.add(customer);
                    }
                    break;
                case 2:
                    for (int i = 0; i < customerManagement.getList().size(); i++) {
                        InputOutput.outputCustomer(customerManagement.getList().get(i));
                    }
                    break;
                case 3:
                    searchingMenu();
                    System.out.print("Enter option: ");
                    int option = -1;
                    while (option == -1) {
                        try {
                            option = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.print("Wrong type, re input: ");
                        } finally {
                            sc.nextLine();
                        }
                    }
                    switch (option) {
                        case 1:
                            System.out.print("Enter Id: ");
                            id = sc.nextLine();
                            int index = customerManagement.searchByID(id);
                            if (index != -1) {
                                InputOutput.outputCustomer(customerManagement.getList().get(index));
                            } else {
                                System.out.println("No customer was found!");
                            }
                            break;
                        case 2:
                            System.out.print("Enter lower age: ");
                            int lowerAge = -1;
                            int higherAge = -1;
                            while (lowerAge == -1) {
                                try {
                                    lowerAge = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Wrong type");
                                } finally {
                                    sc.nextLine();
                                }
                            }
                            System.out.print("Enter higher age: ");
                            while (higherAge == -1) {
                                try {
                                    higherAge = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Wrong type");
                                } finally {
                                    sc.nextLine();
                                }
                            }
                            List<Customer> searchList = customerManagement.searchByAgeRange(lowerAge, higherAge);
                            if (searchList.size() == 0) {
                                System.out.println("No customer was found!");
                            } else {
                                for (Customer customer : searchList) {
                                    InputOutput.outputCustomer(customer);
                                }
                            }
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Invalid!");
                            break;
                    }
                    break;
                case 4:
                    System.out.print("Enter id you want to edit: ");
                    String idEdit = sc.nextLine();
                    customerManagement.edit(idEdit);
                    break;
                case 5:
                    customerManagement.sort();
                    break;
                case 6:
                    System.out.print("Enter id you want to delete: ");
                    id = sc.nextLine();
                    int index = customerManagement.searchByID(id);
                    if (index != -1) {
                        Customer customer = customerManagement.getList().get(index);
                        InputOutput.outputCustomer(customer);
                        System.out.print("Do you want to delete this customer (Press Y to confirm): ");
                        String confirmation = sc.nextLine();
                        if (confirmation.equals("Y")) {
                            customerManagement.delete(id);
                            System.out.println("Delete success!");
                        } else {
                            System.out.println("Delete failed!");
                        }
                    } else {
                        System.out.println("No customer was found!");
                    }
                    break;
                case 7:
                    customerManagement.readFromFile("CustomerFile\\customer.csv");
                    break;
                case 8:
                    customerManagement.writeToFile("CustomerFile\\new file.csv");
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid");
            }
        }

    }

    static void menu() {
        System.out.println("--------Menu---------");
        System.out.println("1. Add Customer");
        System.out.println("2. Show customer list");
        System.out.println("3. Search customer");
        System.out.println("4. Edit customer");
        System.out.println("5. Sort customer list");
        System.out.println("6. Delete customer");
        System.out.println("7. Read from file");
        System.out.println("8. Write to file");
        System.out.println("0. Exit");
    }

    static void searchingMenu() {
        System.out.println("------Searching menu-------");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by age range");
        System.out.println("3. Search by address");
    }
}