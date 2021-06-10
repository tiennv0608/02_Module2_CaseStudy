import java.util.InputMismatchException;
import java.util.Scanner;

public class InputOutput {
    public static Customer inputCustomer() {
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();
        System.out.println("Enter customer information");
        System.out.print("Enter full name: ");
        customer.setFullName(scanner.nextLine());
        System.out.print("Enter date of birth: ");
        customer.setDateOfBirth(scanner.nextLine());
        int gender = 0;
        System.out.print("Enter gender (1.Male/2.Female): ");
        while (gender == 0) {
            try {
                gender = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Wrong type, re input: ");
            } finally {
                scanner.nextLine();
            }
        }
        customer.setGender(gender);
        System.out.print("Enter address: ");
        customer.setAddress(scanner.nextLine());
        System.out.print("Enter email: ");
        customer.setEmail(scanner.nextLine());
        System.out.print("Enter phone: ");
        customer.setPhone(scanner.nextLine());
        return customer;
    }

    public static void outputCustomer(Customer customer) {
        System.out.println("Name: " + customer.getFullName() + ", age: " + customer.getAge() + ", address: " + customer.getAddress() +
                ", email: " + customer.getEmail() + ", phone: " + customer.getPhone());
    }
}
