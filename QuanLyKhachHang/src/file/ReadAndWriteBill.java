package file;

import controller.Main;
import management.CustomerManagement;
import management.ProductManagement;
import model.Bill;
import model.Customer;
import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteBill implements ReadAndWrite<Bill> {
    @Override
    public List<Bill> readFromFile(String path) {
        List<Bill> list = null;
        try {
            CustomerManagement customerManagement = new CustomerManagement();
            ProductManagement productManagement = new ProductManagement();
            List<Customer> customerList = customerManagement.readFromFile(Main.CUSTOMER_PATH);
            List<Product> productList = productManagement.readFromFile(Main.PRODUCT_PATH);
            list = new ArrayList<>();
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                int indexCustomer = customerManagement.findById(values[1]);
                Customer customer = customerList.get(indexCustomer);
                int indexProduct = productManagement.findById(values[5]);
                Product product = productList.get(indexProduct);
                product.setQuantity(Integer.parseInt(values[7]));
                list.add(new Bill(values[0], customer, product));
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return list;
    }

    @Override
    public void writeToFile(String path, List<Bill> list) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String str = "Bill ID,Customer ID,Customer Name,Address,Phone,Product ID,Product Name,Quantity,Price,Total\n";
            for (Bill bill : list) {
                str += bill.getBillId() + "," + bill.getCustomer().getCusId() + "," + bill.getCustomer().getFullName() + "," + bill.getCustomer().getAddress() +
                        "," + bill.getCustomer().getPhone() + "," + bill.getProduct().getId() + "," + bill.getProduct().getName() + "," + bill.getProduct().getQuantity() +
                        "," + bill.getProduct().getPrice() + "," + bill.getTotal() + "\n";
            }
            bufferedWriter.flush();
            bufferedWriter.write(str);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("File not found!");
            ;
        }
    }
}
