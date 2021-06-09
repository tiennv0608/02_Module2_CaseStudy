public class Main {
    public static void main(String[] args) {
        Customer customer = new Customer("CUS001","Nguyen Van Anh","16/09/1999",2,"Nam Dinh","luonson@gmail.com","0987.654.321");
        System.out.println(customer);
        customer.setGender(1);
        System.out.println(customer);
    }
}
