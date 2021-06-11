package management;

import management.interfacemanagement.InputOutput;
import model.Validation;
import model.Customer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputOutputCustomer implements InputOutput<Customer> {
    @Override
    public Customer input() {
        Scanner scanner = new Scanner(System.in);
        boolean check = false;
        Customer customer = new Customer();
        System.out.println("Enter customer information");
        System.out.print("Enter full name: ");
        customer.setFullName(scanner.nextLine());
        System.out.print("Enter date of birth (DD-MM-YYYY): ");
        String dateOfBirth;
        do {
            dateOfBirth = scanner.nextLine();
            check = Validation.validate(Validation.DATE_REGEX, dateOfBirth);
            if (!check) {
                System.out.print("Wrong input, re input:");
            }
        } while (!check);
        customer.setDateOfBirth(dateOfBirth);
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
        String email;
        do {
            email = scanner.nextLine();
            check = Validation.validate(Validation.EMAIL_REGEX, email);
            if (!check) {
                System.out.print("Wrong type, re input: ");
            }
        } while (!check);
        customer.setEmail(email);
        System.out.print("Enter phone (0XXX.XXX.XXX): ");
        String phone;
        do {
            phone = scanner.nextLine();
            check = Validation.validate(Validation.PHONE_REGEX, phone);
            if (!check) {
                System.out.print("Wrong input, re input:");
            }
        } while (!check);
        customer.setPhone(phone);
        return customer;
    }

    @Override
    public void output(Customer customer) {
        System.out.println("Name: " + customer.getFullName() + ", age: " + customer.getAge() + ", address: " + customer.getAddress() +
                ", email: " + customer.getEmail() + ", phone: " + customer.getPhone());
    }
}
