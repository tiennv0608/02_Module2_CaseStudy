package management;

import management.interfacemanagement.Management;
import model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductManagement implements Management<Product> {
    private List<Product> productList;

    public ProductManagement() {
        productList = new ArrayList<>();
    }

    @Override
    public void add(Product product) {
        this.productList.add(product);
    }

    @Override
    public void delete(String productId) {
        int index = findById(productId);
        this.productList.remove(index);
    }

    @Override
    public void edit(String productId) {

    }

    @Override
    public List<Product> findAll() {
        return this.productList;
    }

    @Override
    public int findById(String productId) {
        for (int i = 0; i < this.productList.size(); i++) {
            if (this.productList.get(i).getId().equals(productId)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void sort() {
        Collections.sort(this.productList, new Comparator<Product>() {
            @Override
            public int compare(Product product1, Product product2) {
                return product1.getName().compareTo(product2.getName());
            }
        });
    }

    @Override
    public boolean checkExistedId(String id) {
        for (Product product : this.productList) {
            if (product.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
