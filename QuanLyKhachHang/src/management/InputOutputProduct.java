package management;

import management.interfacemanagement.InputOutput;
import model.Product;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputOutputProduct implements InputOutput<Product> {
    @Override
    public Product input() {
        Scanner scanner = new Scanner(System.in);
        Product product = new Product();
        System.out.println("Enter product information");
        System.out.print("Enter product name: ");
        product.setName(scanner.nextLine());
        System.out.print("Enter product quantity: ");
        int quantity = -1;
        while (quantity == -1) {
            try {
                quantity = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Wrong input, re input: ");
            } finally {
                scanner.nextLine();
            }
        }
        product.setQuantity(quantity);
        System.out.print("Enter price: ");
        int price = -1;
        while (price == -1) {
            try {
                price = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Wrong type, re input: ");
            } finally {
                scanner.nextLine();
            }
        }
        product.setPrice(price);
        return product;
    }

    @Override
    public void output(Product product) {
        System.out.println("Product name: " + product.getName() + ", quantity: " + product.getQuantity() + ", price: " + product.getPrice());
    }
}
