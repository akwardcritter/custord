package org.openjfx.dbi.model;

public class Customer extends Person {

    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String customerName;

    public Customer(int customerNumber, String customerName, String contactLastName, String contactFirstName,
            String phone,
            String addressLine1, String city, String country) {
        super(customerNumber, contactLastName, contactFirstName);
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.city = city;
        this.country = country;
        this.customerName = customerName;
    }

    public Customer(String customerName, String contactLastName, String contactFirstName,
            String phone,
            String addressLine1, String city, String country) {
        super(contactLastName, contactFirstName);
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.city = city;
        this.country = country;
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
