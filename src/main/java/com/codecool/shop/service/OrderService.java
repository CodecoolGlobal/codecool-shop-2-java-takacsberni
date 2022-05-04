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

    public void addNewOrder(BigDecimal productPrice, String productName){
        int defaultQuantity = 1;
        LineItem item = new LineItem(defaultQuantity, productPrice, productName);
        int orderId = getAllOrders().size() + 1;
        Order newOrder = new Order(orderId, item);
        orderDao.add(newOrder);
    }

}
