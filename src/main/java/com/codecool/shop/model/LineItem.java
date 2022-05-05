package com.codecool.shop.model;

import java.math.BigDecimal;

public class LineItem {

    private int quantity;
    private BigDecimal productPrice;
    private String productName;
    private String productDescription;

    public LineItem(int quantity, BigDecimal productPrice, String productName, String productDescription) {
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productDescription = productDescription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
