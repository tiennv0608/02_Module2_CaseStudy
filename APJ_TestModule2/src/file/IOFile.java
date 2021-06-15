package file;

import model.PhoneBook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile {
    public static List<PhoneBook> readFromFile(String path) {
        List<PhoneBook> list = null;
        try {
            list = new ArrayList<>();
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] informations = line.split(",");
                list.add(new PhoneBook(informations[0], informations[1], informations[2], informations[3], informations[4], informations[5], informations[6]));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Không tìm thấy file");
        }
        return list;
    }

    public static void writeToFile(String path, List<PhoneBook> list) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String str = "Số điện thoại,Nhóm,Họ tên,Giới tính,Địa chỉ,Ngày sinh,Email\n";
            for (PhoneBook phoneBook : list) {
                str += phoneBook.getPhone() + "," + phoneBook.getGroup() + "," + phoneBook.getFullName() + "," + phoneBook.getGender() +
                        "," + phoneBook.getAddress() + "," + phoneBook.getDateOfBirth() + "," + phoneBook.getEmail() + "\n";
            }
            bufferedWriter.write(str);
            bufferedWriter.flush();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Không tìm thấy file");
        }
    }
}
