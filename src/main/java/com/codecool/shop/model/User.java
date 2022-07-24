package com.codecool.shop.model;

public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String phone_number;
    private String billing_country;
    private String billing_city;
    private String billing_zipcode;
    private String billing_address;
    private String shipping_country;
    private String shipping_city;
    private String shipping_zipcode;
    private String shipping_address;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getBilling_country() {
        return billing_country;
    }

    public String getBilling_city() {
        return billing_city;
    }

    public String getBilling_zipcode() {
        return billing_zipcode;
    }

    public String getBilling_address() {
        return billing_address;
    }

    public String getShipping_country() {
        return shipping_country;
    }

    public String getShipping_city() {
        return shipping_city;
    }

    public String getShipping_zipcode() {
        return shipping_zipcode;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setBilling_country(String billing_country) {
        this.billing_country = billing_country;
    }

    public void setBilling_city(String billing_city) {
        this.billing_city = billing_city;
    }

    public void setBilling_zipcode(String billing_zipcode) {
        this.billing_zipcode = billing_zipcode;
    }

    public void setBilling_address(String billing_address) {
        this.billing_address = billing_address;
    }

    public void setShipping_country(String shipping_country) {
        this.shipping_country = shipping_country;
    }

    public void setShipping_city(String shipping_city) {
        this.shipping_city = shipping_city;
    }

    public void setShipping_zipcode(String shipping_zipcode) {
        this.shipping_zipcode = shipping_zipcode;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }
}
