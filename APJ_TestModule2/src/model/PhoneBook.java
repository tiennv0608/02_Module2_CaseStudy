package model;

public class PhoneBook {
    private String phone;
    private String group;
    private String fullName;
    private String gender;
    private String address;
    private String dateOfBirth;
    private String email;

    public PhoneBook(String phone, String group, String fullName, String gender, String address, String dateOfBirth, String email) {
        this.phone = phone;
        this.group = group;
        this.fullName = fullName;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public PhoneBook() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "phone='" + phone + '\'' +
                ", group='" + group + '\'' +
                ", fullName='" + fullName + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
