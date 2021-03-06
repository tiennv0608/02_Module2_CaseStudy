package controller;

import management.InputOutputProduct;
import management.ProductManagement;
import model.Product;
import model.Validation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainProduct {
    public static void main(String[] args) {
        ProductManagement productManagement = new ProductManagement();
        InputOutputProduct inputOutputProduct = new InputOutputProduct();
        Scanner scanner = new Scanner(System.in);
        boolean check;
        while (true) {
            menuProduct();
            System.out.print("Enter your choice (0-8): ");
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
                    int index = productManagement.findById(id);
                    if (index != -1) {
                        Product product = productManagement.findAll().get(index);
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
                    productManagement.readFromFile("File\\product.csv");
                    System.out.println("Read complete!");
                    break;
                case 8:
                    productManagement.writeToFile("File\\new file product.csv");
                    System.out.println("Write complete!");
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid");
            }
        }
    }

    static void menuProduct() {
        System.out.println("--------Menu---------");
        System.out.println("1. Add new product");
        System.out.println("2. Show product list");
        System.out.println("3. Search product");
        System.out.println("4. Edit product");
        System.out.println("5. Sort product list");
        System.out.println("6. Delete product");
        System.out.println("7. Read from file");
        System.out.println("8. Write to file");
        System.out.println("0. Exit");
    }

    static void searchingProduct() {
        System.out.println("------Searching menu-------");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by name");
        System.out.println("3. Search by price range");
    }
}
