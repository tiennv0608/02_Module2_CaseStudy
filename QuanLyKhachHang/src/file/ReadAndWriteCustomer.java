package file;

import model.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteCustomer implements ReadAndWrite<Customer> {
    @Override
    public List<Customer> readFromFile(String path) {
        List<Customer> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] informations = line.split(",");
                int gender;
                if (informations[3].equals("Male")) {
                    gender = 1;
                } else {
                    gender = 2;
                }
                list.add(new Customer(informations[0], informations[1], informations[2], gender, informations[4], informations[5], informations[6]));
            }
            br.close();
            fr.close();
            System.out.println("Read complete");
        } catch (IOException e) {
            System.out.println("File not found or missing!");
        }
        return list;
    }

    @Override
    public void writeToFile(String path, List<Customer> list) {
        try {
            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);
            String str = "CUS ID,Full name,Date of birth,Gender,Address,Email,Phone\n";
            for (Customer customer : list) {
                String gender = "";
                if (customer.getGender() == 1) {
                    gender = "Male";
                } else {
                    gender = "Female";
                }
                str += customer.getCusId() + "," + customer.getFullName() + "," + customer.getDateOfBirth() + "," +
                        gender + "," + customer.getAddress() + "," + customer.getEmail() + "," + customer.getPhone() + "\n";
            }
            bw.write(str);
            bw.close();
            fw.close();
            System.out.println("Write complete!");
        } catch (IOException ex) {
            System.out.println("File not found or missing");
        }
    }
}
