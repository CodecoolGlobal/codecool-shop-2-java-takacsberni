package com.codecool.shop.model;

import java.math.BigDecimal;

public class LineItem {

    private int quantity;
    private BigDecimal productPrice;
    private String productName;
    private String productDescription;
    private String supplier;
    private int productId;
    private int orderId;


    public LineItem(int quantity, BigDecimal productPrice, String productName, String productDescription, int orderId, String supplier, int productId) {
        this.quantity = quantity;
        this.productPrice = productPrice;
        this.productName = productName;
        this.productDescription = productDescription;
        this.supplier = supplier;
        this.orderId = orderId;
        this.productId = productId;
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

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }
}
