package com.codecool.shop.service;


import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.LineItem;


import java.math.BigDecimal;
import java.util.List;

public class OrderService{
    private OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> getAllOrders() {
        return orderDao.getAll();
    }

    public void addLineItem(BigDecimal productPrice, String productName, String productDescription, int orderId){
        int defaultQuantity = 1;
        LineItem item = new LineItem(defaultQuantity, productPrice, productName, productDescription);
        Order order = orderDao.find(orderId);
        order.addLineItem(item);
    }

    public List<LineItem> getLineItems(int orderId){
        return orderDao.getLineItems(orderId);
    }


    public Order getOrderById(int id) {
        return orderDao.find(id);
    }

}
