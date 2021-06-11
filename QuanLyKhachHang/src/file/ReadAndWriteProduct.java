package file;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteProduct implements ReadAndWrite<Product> {
    @Override
    public List<Product> readFromFile(String path) {
        List<Product> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] product = line.split(",");
                list.add(new Product(product[0], product[1], Integer.parseInt(product[2]), Integer.parseInt(product[3])));
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return list;
    }

    @Override
    public void writeToFile(String path, List<Product> list) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String str = "Product ID,Product name,Quantity,Price\n";
            for (Product product : list) {
                str += product.getId() + "," + product.getName() + "," + product.getQuantity() + "," + product.getPrice() + "\n";
            }
            bufferedWriter.write(str);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
