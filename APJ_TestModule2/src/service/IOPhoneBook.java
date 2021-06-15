package service;

import model.PhoneBook;
import model.Validate;

import java.util.Scanner;

public class IOPhoneBook {

    public static PhoneBook input() {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        System.out.print("Nhập nhóm: ");
        String group;
        do {
            group = scanner.nextLine();
            if (group.equals("")) {
                System.out.print("Không được để trống dữ liệu, nhập lại: ");
            }
        } while (group.equals(""));
        phoneBook.setGroup(group);
        System.out.print("Nhập họ tên: ");
        String fullName;
        do {
            fullName = scanner.nextLine();
            if (fullName.equals("")) {
                System.out.print("Không được để trống dữ liệu, nhập lại: ");
            }
        } while (fullName.equals(""));
        phoneBook.setFullName(fullName);
        System.out.print("Nhập giới tính: ");
        String gender;
        do {
            gender = scanner.nextLine();
            if (gender.equals("")) {
                System.out.print("Không được để trống dữ liệu, nhập lại: ");
            }
        } while (gender.equals(""));
        phoneBook.setGender(gender);
        System.out.print("Nhập địa chỉ: ");
        String address;
        do {
            address = scanner.nextLine();
            if (address.equals("")) {
                System.out.print("Không được để trống dữ liệu, nhập lại: ");
            }
        } while (address.equals(""));
        phoneBook.setAddress(address);
        System.out.print("Nhập ngày sinh: ");
        String dateOfBirth;
        do {
            dateOfBirth = scanner.nextLine();
            if (dateOfBirth.equals("")) {
                System.out.print("Không được để trống dữ liệu, nhập lại: ");
            }
        } while (dateOfBirth.equals(""));
        phoneBook.setDateOfBirth(dateOfBirth);
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
