package com.codecool.shop.model;

import java.math.BigDecimal;

public class LineItem {

    private int quantity;
    private BigDecimal productPrice;
    private String productName;
    private String productDescription;
    private String supplier;


    public LineItem(int quantity, BigDecimal productPrice, String productName, String productDescription, String supplier) {
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productDescription = productDescription;
        this.supplier = supplier;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }


    public String getSupplier() {
        return supplier;
    }
}
