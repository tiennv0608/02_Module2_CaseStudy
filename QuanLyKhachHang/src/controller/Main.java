package controller;

import management.*;
import model.Bill;
import model.Customer;
import model.Product;
import model.Validation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String BILL_PATH = "File\\bill.csv";
    public static final String CUSTOMER_PATH = "File\\customer.csv";
    public static final String PRODUCT_PATH = "File\\product.csv";


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BillManagement billManagement = new BillManagement();
        InputOutputBill inputOutputBill = new InputOutputBill();
        CustomerManagement customerManagement = new CustomerManagement();
        InputOutputCustomer inputOutputCustomer = new InputOutputCustomer();
        ProductManagement productManagement = new ProductManagement();
        InputOutputProduct inputOutputProduct = new InputOutputProduct();
        boolean check = false;
        while (true) {
            mainMenu();
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
                    menuBill();
                    System.out.print("Enter your choice: ");
                    choice = -1;
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
                            System.out.print("Enter Bill id (BILLXXX): ");
                            String id;
                            do {
                                id = scanner.nextLine();
                                check = Validation.validate(Validation.BILL_ID_REGEX, id);
                                if (!check) {
                                    System.out.print("Wrong input, re input:");
                                }
                            } while (!check);
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
                        case 3:
                            billManagement.readFromFile(BILL_PATH);
                            break;
                        case 4:
                            billManagement.writeToFile(BILL_PATH);
                            break;
                        default:
                            System.out.println("Invalid!");
                    }
                    break;
                case 2:
                    menuCustomer();
                    System.out.print("Enter your choice (0-8): ");
                    choice = -1;
                    check = false;
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
                            System.out.print("Enter id (CUSxxx): ");
                            String id;
                            do {
                                id = scanner.nextLine();
                                check = Validation.validate(Validation.ID_REGEX, id);
                                if (!check) {
                                    System.out.print("Wrong input, re input:");
                                }
                            } while (!check);
                            if (customerManagement.checkExistedId(id)) {
                                System.out.println("Duplicated id!!!");
                            } else {
                                Customer customer = inputOutputCustomer.input();
                                customer.setCusId(id);
                                customerManagement.add(customer);
                            }
                            break;
                        case 2:
                            for (int i = 0; i < customerManagement.findAll().size(); i++) {
                                inputOutputCustomer.output(customerManagement.findAll().get(i));
                            }
                            break;
                        case 3:
                            searchingCustomerInformation();
                            System.out.print("Enter option: ");
                            int option = -1;
                            while (option == -1) {
                                try {
                                    option = scanner.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.print("Wrong type, re input: ");
                                } finally {
                                    scanner.nextLine();
                                }
                            }
                            switch (option) {
                                case 1:
                                    System.out.print("Enter Id: ");
                                    id = scanner.nextLine();
                                    int index = customerManagement.findById(id);
                                    if (index != -1) {
                                        inputOutputCustomer.output(customerManagement.findAll().get(index));
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
                                            lowerAge = scanner.nextInt();
                                        } catch (InputMismatchException e) {
                                            System.out.println("Wrong type");
                                        } finally {
                                            scanner.nextLine();
                                        }
                                    }
                                    System.out.print("Enter higher age: ");
                                    while (higherAge == -1) {
                                        try {
                                            higherAge = scanner.nextInt();
                                        } catch (InputMismatchException e) {
                                            System.out.println("Wrong type");
                                        } finally {
                                            scanner.nextLine();
                                        }
                                    }
                                    List<Customer> searchList = customerManagement.searchByAgeRange(lowerAge, higherAge);
                                    if (searchList.size() == 0) {
                                        System.out.println("No customer was found!");
                                    } else {
                                        for (Customer customer : searchList) {
                                            inputOutputCustomer.output(customer);
                                        }
                                    }
                                    break;
                                case 3:
                                    System.out.print("Enter address: ");
                                    String address = scanner.nextLine();
                                    searchList = customerManagement.searchByAddress(address);
                                    if (searchList.size() == 0) {
                                        System.out.println("No customer was found!");
                                    } else {
                                        for (Customer customer : searchList) {
                                            inputOutputCustomer.output(customer);
                                        }
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid!");
                                    break;
                            }
                            break;
                        case 4:
                            System.out.print("Enter id you want to edit: ");
                            String idEdit = scanner.nextLine();
                            customerManagement.edit(idEdit);
                            break;
                        case 5:
                            customerManagement.sort();
                            break;
                        case 6:
                            System.out.print("Enter id you want to delete: ");
                            id = scanner.nextLine();
                            int index = customerManagement.findById(id);
                            if (index != -1) {
                                Customer customer = customerManagement.findAll().get(index);
                                inputOutputCustomer.output(customer);
                                System.out.print("Do you want to delete this customer (Press Y to confirm): ");
                                String confirmation = scanner.nextLine();
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
                            customerManagement.readFromFile(CUSTOMER_PATH);
                            System.out.println("Read complete");
                            break;
                        case 8:
                            customerManagement.writeToFile(CUSTOMER_PATH);
                            System.out.println("Write complete");
                            break;
                    }
                    break;
                case 3:
                    check = false;
                    while (true) {
                        menuProduct();
                        System.out.print("Enter your choice (0-8): ");
                        choice = -1;
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
                                System.out.print("Enter id (RICEXXX): ");
                                String id;
                                do {
                                    id = scanner.nextLine();
                                    check = Validation.validate(Validation.PRODUCT_ID_REGEX, id);
                                    if (!check) {
                                        System.out.print("Wrong input, re input:");
                                    }
                                } while (!check);
                                if (productManagement.checkExistedId(id)) {
                                    System.out.println("Duplicated id!!!");
                                } else {
                                    Product product = inputOutputProduct.input();
                                    product.setId(id);
                                    productManagement.add(product);
                                }
                                break;
                            case 2:
                                for (int i = 0; i < productManagement.findAll().size(); i++) {
                                    inputOutputProduct.output(productManagement.findAll().get(i));
                                }
                                break;
                            case 3:
                                searchingProduct();
                                System.out.print("Enter option: ");
                                int option = -1;
                                while (option == -1) {
                                    try {
                                        option = scanner.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.out.print("Wrong type, re input: ");
                                    } finally {
                                        scanner.nextLine();
                                    }
                                }
                                switch (option) {
                                    case 1:
                                        System.out.print("Enter Id: ");
                                        id = scanner.nextLine();
                                        int index = productManagement.findById(id);
                                        if (index != -1) {
                                            inputOutputProduct.output(productManagement.findAll().get(index));
                                        } else {
                                            System.out.println("No product was found!");
                                        }
                                        break;
                                    case 2:
                                        System.out.print("Enter name: ");
                                        String name = scanner.nextLine();
                                        List<Product> searchList = productManagement.searchByName(name);
                                        if (searchList.size() == 0) {
                                            System.out.println("No product was found!");
                                        } else {
                                            for (Product product : searchList) {
                                                inputOutputProduct.output(product);
                                            }
                                        }
                                        break;
                                    case 3:
                                        System.out.print("Enter lower price: ");
                                        int lowPrice = -1;
                                        int highPrice = -1;
                                        while (lowPrice == -1) {
                                            try {
                                                lowPrice = scanner.nextInt();
                                            } catch (InputMismatchException e) {
                                                System.out.println("Wrong type");
                                            } finally {
                                                scanner.nextLine();
                                            }
                                        }
                                        System.out.print("Enter higher price: ");
                                        while (highPrice == -1) {
                                            try {
                                                highPrice = scanner.nextInt();
                                            } catch (InputMismatchException e) {
                                                System.out.println("Wrong type");
                                            } finally {
                                                scanner.nextLine();
                                            }
                                        }
                                        searchList = productManagement.searchByPriceRange(lowPrice, highPrice);
                                        if (searchList.size() == 0) {
                                            System.out.println("No product was found!");
                                        } else {
                                            for (Product product : searchList) {
                                                inputOutputProduct.output(product);
                                            }
                                        }
                                        break;
                                    default:
                                        System.out.println("Invalid!");
                                        break;
                                }
                                break;
                            case 4:
                                System.out.print("Enter id you want to edit: ");
                                String idEdit = scanner.nextLine();
                                productManagement.edit(idEdit);
                                break;
                            case 5:
                                productManagement.sort();
                                break;
                            case 6:
                                System.out.print("Enter id you want to delete: ");
                                id = scanner.nextLine();
                                int indexProduct = productManagement.findById(id);
                                if (indexProduct != -1) {
                                    Product product = productManagement.findAll().get(indexProduct);
                                    inputOutputProduct.output(product);
                                    System.out.print("Do you want to delete this product (Press Y to confirm): ");
                                    String confirmation = scanner.nextLine();
                                    if (confirmation.equals("Y")) {
                                        productManagement.delete(id);
                                        System.out.println("Delete success!");
                                    } else {
                                        System.out.println("Delete failed!");
                                    }
                                } else {
                                    System.out.println("No product was found!");
                                }
                                break;
                            case 7:
                                productManagement.readFromFile(PRODUCT_PATH);
                                System.out.println("Read complete!");
                                break;
                            case 8:
                                productManagement.writeToFile(PRODUCT_PATH);
                                System.out.println("Write complete!");
                                break;
                            default:
                                System.out.println("Invalid");
                        }
                        break;
                    }
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid");

            }
        }
    }

    static void mainMenu() {
        System.out.println("----------Main Menu-------");
        System.out.println("1. Bill");
        System.out.println("2. Customer");
        System.out.println("3. Product");
        System.out.println("0. Exit");
    }

    static void menuBill() {
        System.out.println("--------Menu Bill----------");
        System.out.println("1. Add bill");
        System.out.println("2. Show bill");
        System.out.println("3. Read from file");
        System.out.println("4. Write to file");
    }

    static void menuCustomer() {
        System.out.println("--------Menu Customer---------");
        System.out.println("1. Add new customer");
        System.out.println("2. Show customer list");
        System.out.println("3. Search customer");
        System.out.println("4. Edit customer");
        System.out.println("5. Sort customer list");
        System.out.println("6. Delete customer");
        System.out.println("7. Read from file");
        System.out.println("8. Write to file");
    }

    static void searchingCustomerInformation() {
        System.out.println("------Searching menu-------");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by age range");
        System.out.println("3. Search by address");
    }

    static void menuProduct() {
        System.out.println("--------Menu Product---------");
        System.out.println("1. Add new product");
        System.out.println("2. Show product list");
        System.out.println("3. Search product");
        System.out.println("4. Edit product");
        System.out.println("5. Sort product list");
        System.out.println("6. Delete product");
        System.out.println("7. Read from file");
        System.out.println("8. Write to file");
    }

    static void searchingProduct() {
        System.out.println("------Searching menu-------");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by name");
        System.out.println("3. Search by price range");
    }
}