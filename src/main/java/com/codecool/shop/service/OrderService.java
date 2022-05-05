package com.codecool.shop.service;


import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.LineItem;


import java.math.BigDecimal;
import java.util.List;

public class OrderService{

    private OrderDao orderDao;
    private int currentOrderId = 1;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> getAllOrders() {
        return orderDao.getAll();
    }

    public void addLineItem(BigDecimal productPrice, String productName, String productDescription, int orderId, String supplier){
        int defaultQuantity = 1;
        LineItem item = new LineItem(defaultQuantity, productPrice, productName, productDescription, supplier);
        Order order = orderDao.find(orderId);
        order.addLineItem(item);
    }

    public List<LineItem> getLineItems(int orderId){
        return orderDao.getLineItems(orderId);
    }

    public BigDecimal getFullPrice(int orderId){
        List<LineItem> items = getLineItems(orderId);
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
