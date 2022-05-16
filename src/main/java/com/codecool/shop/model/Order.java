package com.codecool.shop.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order {
    private int id;
    private List <LineItem> lineItems = new ArrayList<>();

    private HashMap<String, String> customerData;


    public Order(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    // maybe we don't need setter for id
    public void setId(int id) {
        this.id = id;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    // not sure if we need this
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void addLineItem(LineItem lineItem){
        lineItems.add(lineItem);
    }

    public void setCustomerData(HashMap<String, String> customerData) {
        this.customerData = customerData;
    }

    public HashMap<String, String> getCustomerData() {
        return customerData;
    }
}
