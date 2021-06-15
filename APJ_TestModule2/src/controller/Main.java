package controller;

import file.IOFile;
import model.PhoneBook;
import model.Validate;
import service.IOPhoneBook;
import service.PhoneBookService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String PATH = "data\\contacts.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBookService phoneBookService = new PhoneBookService();
        while (true) {
            menu();
            int choice = -1;
            while (choice == -1) {
                try {
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.err.print("Nhập sai kiểu dữ liệu, nhập lại: ");
                } finally {
                    scanner.nextLine();
                }
            }
            switch (choice) {
                case 1:
                    phoneBookService.display();
                    break;
                case 2:
                    System.out.println("Thông tin của danh bạ:");
                    System.out.print("Nhập số điện thoại (VD:0987.654.321): ");
                    String phone = scanner.nextLine();
                    int index;
                    boolean check;
                    do {
                        index = phoneBookService.searchByPhone(phone);
                        check = Validate.validate(Validate.PHONE_REGEX, phone);
                        if (!check) {
                            System.out.print("Nhập sai định dạng, nhập lại (VD:0987.654.321): ");
                            phone = scanner.nextLine();
                        } else if (index != -1) {
                            System.out.print("Trùng số điện thoại, nhập lại: ");
                            phone = scanner.nextLine();
                        }
                    } while (!check || index != -1);
                    System.out.println("Nhập thông tin mới");
                    PhoneBook phoneBook = IOPhoneBook.input();
                    phoneBook.setPhone(phone);
                    phoneBookService.add(phoneBook);
                    System.out.println("Thêm thành công");
                    break;
                case 3:
                    System.out.print("Nhập số điện thoại muốn sửa: ");
                    phone = scanner.nextLine();
                    System.out.println("Nhập thông tin cần sửa");
                    phoneBookService.update(phone);
                    break;
                case 4:
                    System.out.print("Nhập số điện thoại muốn xóa: ");
                    phone = scanner.nextLine();
                    phoneBookService.delete(phone);
                    break;
                case 5:
                    searchMenu();
                    int option = -1;
                    while (option == -1) {
                        try {
                            option = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.err.print("Nhập sai kiểu dữ liệu, nhập lại: ");
                        } finally {
                            scanner.nextLine();
                        }
                    }
                    break;
                case 6:
                    System.out.println("Bạn có muốn cập nhật lại danh bạ trong bộ nhớ?");
                    System.out.println("(Thao tác này sẽ xóa toàn bộ danh bạ trong bộ nhớ)");
                    System.out.print("Nhập Y để đồng ý: ");
                    String answer = scanner.nextLine();
                    if (answer.equals("Y")) {
                        List<PhoneBook> phoneBookList = IOFile.readFromFile(PATH);
                        phoneBookService.findAll().addAll(phoneBookList);
                    }
                    break;
                case 7:
                    System.out.println("Bạn có muốn cập nhật lại file danh bạ?");
                    System.out.print("Nhập Y để đồng ý: ");
                    answer = scanner.nextLine();
                    if (answer.equals("Y")) {
                        IOFile.writeToFile(PATH, phoneBookService.findAll());
                    }
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("Sai chức năng");
            }

        }
    }

    static void menu() {
        System.out.println("-----Chương trình quản lý danh bạ----");
        System.out.println("Chọn chức năng theo số (để tiếp tục): ");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xoá");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Đọc từ file");
        System.out.println("7. Ghi vào file");
        System.out.println("8. Thoát");
        System.out.print("Chọn chức năng: ");
    }

    static void searchMenu() {
        System.out.println("----Tìm kiếm-----");
        System.out.println("1. Theo số điện thoại");
        System.out.println("2. Theo tên");
        System.out.print("Chọn chức năng: ");
    }
}
