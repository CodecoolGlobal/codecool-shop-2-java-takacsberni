package com.codecool.shop.model;

import java.math.BigDecimal;

public class LineItem {

    private int quantity;
    private BigDecimal productPrice;
    private String productName;

    public LineItem(int quantity, BigDecimal productPrice, String productName) {
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
