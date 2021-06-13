package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TableView<Customer> tableView;
    @FXML
    private TableColumn<Customer, String> id;
    @FXML
    private TableColumn<Customer, String> name;
    @FXML
    private TableColumn<Customer, String> birthday;
    @FXML
    private TableColumn<Customer, String> gender;
    @FXML
    private TableColumn<Customer, String> address;
    @FXML
    private TableColumn<Customer, String> email;
    @FXML
    private TableColumn<Customer, String> phone;
    private ObservableList<Customer> customers;

    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField dobText;
    @FXML
    private TextField genderText;
    @FXML
    private TextField addressText;
    @FXML
    private TextField emailText;
    @FXML
    private TextField phoneText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        customers = FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<Customer, String>("cusId"));
        name.setCellValueFactory(new PropertyValueFactory<Customer, String>("fullName"));
        birthday.setCellValueFactory(new PropertyValueFactory<Customer, String>("dateOfBirth"));
        gender.setCellValueFactory(new PropertyValueFactory<Customer, String>("sex"));
        address.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        email.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        phone.setCellValueFactory(new PropertyValueFactory<Customer, String>("phone"));
        tableView.setItems(customers);
    }

    public void add(ActionEvent e) {
        Customer customer = new Customer();
        customer.setCusId(idText.getText());
        customer.setFullName(nameText.getText());
        customer.setDateOfBirth(dobText.getText());
        customer.setGender(Integer.parseInt(genderText.getText()));
        customer.setAddress(addressText.getText());
        customer.setEmail(emailText.getText());
        customer.setPhone(phoneText.getText());
        customers.add(customer);
    }

    public void delete(ActionEvent e) {
        Customer selected = tableView.getSelectionModel().getSelectedItem();
        customers.remove(selected);
    }

    public void clear(ActionEvent event) {
        idText.clear();
        nameText.clear();
        dobText.clear();
        genderText.clear();
        addressText.clear();
        emailText.clear();
        phoneText.clear();
    }

    public void importList(ActionEvent e){
        CustomerManagement customerManagement = new CustomerManagement();
        List<Customer> customerList = customerManagement.readFromFile("customer.csv");
        customers.addAll(customerList);
    }
}

