package service;

import model.PhoneBook;
import model.Validate;

import java.util.Scanner;

public class IOPhoneBook {

    public static PhoneBook input() {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        System.out.print("Nhập nhóm: ");
        phoneBook.setGroup(scanner.nextLine());
        System.out.print("Nhập họ tên: ");
        phoneBook.setFullName(scanner.nextLine());
        System.out.print("Nhập giới tính: ");
        phoneBook.setGender(scanner.nextLine());
        System.out.print("Nhập địa chỉ: ");
        phoneBook.setAddress(scanner.nextLine());
        System.out.print("Nhập ngày sinh: ");
        phoneBook.setDateOfBirth(scanner.nextLine());
        System.out.print("Nhập email (VD: abc@gmail.com): ");
        boolean check;
        String email = scanner.nextLine();
        do {
            check = Validate.validate(Validate.EMAIL_REGEX, email);
            if (!check) {
                System.out.print("Nhập sai định dạng, nhập lại (VD: abc@gmail.com):");
                email = scanner.nextLine();
            }
        } while (!check);
        phoneBook.setEmail(email);
        return phoneBook;
    }

    public static void output(PhoneBook phoneBook) {
        System.out.println("Số điện thoại: " + phoneBook.getPhone() + ", nhóm: " + phoneBook.getGroup() + ", họ tên: " +
                phoneBook.getFullName() + ", giới tính: " + phoneBook.getGender() + ", địa chỉ: " + phoneBook.getAddress());
    }
}
