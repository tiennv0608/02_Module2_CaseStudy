import java.util.Scanner;

public class InputOutput {
    public static Customer inputCustomer(){
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();
        System.out.println("Enter customer information");
        System.out.print("Enter full name: ");
        customer.setFullName(scanner.nextLine());
        System.out.print("Enter date of birth: ");
        customer.setDateOfBirth(scanner.nextLine());
        System.out.print("Enter gender (1.Male/2.Female): ");
        customer.setGender(scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter address: ");
        customer.setAddress(scanner.nextLine());
        System.out.print("Enter email: ");
        customer.setEmail(scanner.nextLine());
        System.out.print("Enter phone: ");
        customer.setPhone(scanner.nextLine());
        return customer;
    }

    public static void outputCustomer(Customer customer){
        System.out.println("Name: "+customer.getFullName()+", age: "+customer.getAge());
    }
}
