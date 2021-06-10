import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Collection;
import java.util.Collections;

public class Customer {
    private String cusId;
    private String fullName;
    private String dateOfBirth;
    private int age;
    private int gender;
    private String sex;
    private String address;
    private String email;
    private String phone;

    public Customer() {
    }

    public Customer(String cusId, String fullName, String dateOfBirth, int gender, String address, String email, String phone) {
        this.cusId = cusId;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.sex = setSex();
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.setAge();
    }

    private void setAge() {
        this.age = LocalDateTime.now().getYear() - Integer.parseInt(this.dateOfBirth.substring(6));
    }

    private String setSex() {
        if (getGender() == 1) {
            return "Male";
        } else {
            return "Female";
        }
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.setAge();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
        this.sex = setSex();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cusId: '" + cusId + '\'' +
                ", fullName: '" + fullName + '\'' +
                ", age: " + age +
                ", gender: '" + sex + '\'' +
                ", address: '" + address + '\'' +
                ", email: '" + email + '\'' +
                ", phone: '" + phone + '\'' +
                '}';
    }
}
