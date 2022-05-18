package com.codecool.shop.service;


import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.LineItem;


import java.math.BigDecimal;
import java.util.List;

public class OrderService{

    private OrderDao orderDao;
    private LineItemDao lineItemDao;
    private int currentOrderId = 1;

    public OrderService(OrderDao orderDao, LineItemDao lineItemDao) {
        this.orderDao = orderDao;
        this.lineItemDao = lineItemDao;
    }

    public List<Order> getAllOrders() {
        return orderDao.getAll();
    }

    public void addLineItem(BigDecimal productPrice, String productName, String productDescription, int orderId, String supplier, int productId){
        int defaultQuantity = 1;
        LineItem item = new LineItem(defaultQuantity, productPrice, productName, productDescription, orderId, supplier, productId);
        lineItemDao.add(item);
    }


    public List<LineItem> getLineItemsByOrder(int orderId){
        return lineItemDao.getLineItems(orderId);
    }

    private LineItem isProductAlreadyInOrder(String productName) {
        for (LineItem item : getLineItemsByOrder(currentOrderId)) {
            if (item.getProductName().equals(productName)) {
                return item;
            }
        }
        return null;
    }

    public void addLineItemOrUpdateQuantity(String productName, BigDecimal productPrice, String productDescription, String supplier, int productId) {
        LineItem itemWithSameProduct = isProductAlreadyInOrder(productName);
        if (itemWithSameProduct == null) {
            addLineItem(productPrice, productName, productDescription, currentOrderId, supplier, productId);
        } else {
            itemWithSameProduct.setQuantity(1); // adds one to quantity
        }

    }

    public BigDecimal getFullPrice(int orderId){
        List<LineItem> items = getLineItemsByOrder(orderId);
        BigDecimal fullPrice = new BigDecimal(0);
        for (LineItem item : items){
            BigDecimal linePrice = item.getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            fullPrice = fullPrice.add(linePrice);
        }

        return fullPrice;
    }

    public int getCurrentOrderId() {
        return currentOrderId;
    }


    public Order getOrderById(int id) {
        return orderDao.find(id);
    }

}
