package model;

public class Bill {
    private String billId;
    private Customer customer;
    private Product product;
    private int total;

    public Bill() {
    }

    public Bill(String billId, Customer customer, Product product) {
        this.billId = billId;
        this.customer = customer;
        this.product = product;
        this.total = product.getQuantity() * product.getPrice();
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal() {
        this.total = product.getQuantity() * product.getPrice();
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId: " + billId +
                ", " + customer +
                ", " + product +
                ", total: " + total +
                '}';
    }
}

