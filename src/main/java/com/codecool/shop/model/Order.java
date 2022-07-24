package com.codecool.shop.model;

import com.google.gson.Gson;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    private int id;
    private Date date;
    private int userId;
    private String status;

    private HashMap<String, String> customerData;


    public Order(int id) {
        this.id = id;
        this.date = new Date();
        this.status = "checked";
    }

    public Order(int id, int userId, Date date, String status) {
        this.id = id;
        this.date = date;
        this.userId = userId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    // maybe we don't need setter for id
    public void setId(int id) {
        this.id = id;
    }

    public void setCustomerData(HashMap<String, String> customerData) {
        this.customerData = customerData;
    }

    public HashMap<String, String> getCustomerData() {
        return customerData;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public int getUserId() {
        return userId;
    }
}
