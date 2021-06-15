package service;

import model.PhoneBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBookService {
    private List<PhoneBook> phoneBookList;

    public PhoneBookService() {
        phoneBookList = new ArrayList<>();
    }

    public void add(PhoneBook phoneBook) {
        phoneBookList.add(phoneBook);
    }

    public List<PhoneBook> findAll() {
        return phoneBookList;
    }

    public void display() {
        for (PhoneBook phoneBook : phoneBookList) {
            IOPhoneBook.output(phoneBook);
        }
    }

    public void update(String phone) {
        int index = searchByPhone(phone);
        if (index != -1) {
            PhoneBook phoneBook = IOPhoneBook.input();
            phoneBook.setPhone(phone);
            phoneBookList.set(index, phoneBook);
            System.out.println("Cập nhật thành công");
        } else {
            System.out.println("Không tìm được danh bạ với số điện thoại trên.");
        }
    }

    public void delete(String phone) {
        Scanner scanner = new Scanner(System.in);
        int index = searchByPhone(phone);
        if (index == -1) {
            System.out.println("Không tìm được danh bạ với số điện thoại trên.");
        } else {
            IOPhoneBook.output(phoneBookList.get(index));
            System.out.print("Bạn có muốn xóa danh bạ này?");
            String answer = scanner.nextLine();
            if (answer.equals("Y")) {
                phoneBookList.remove(index);
                System.out.println("Xóa thành công");
            } else {
                System.out.println("Xóa thất bại");
            }
        }
    }

    public int searchByPhone(String phone) {
        for (int i = 0; i < phoneBookList.size(); i++) {
            if (phoneBookList.get(i).getPhone().equals(phone)) {
                return i;
            }
        }
        return -1;
    }


}
