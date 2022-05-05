package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem implements OrderDao {


    private List<Order> orders = new ArrayList<>();
    private static OrderDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public void add(Order order) {
        orders.add(order);
    }

    @Override
    public Order find(int id) {

        for(Order order : orders){
            if(order.getId() == id){
                return order;
            }
        }
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public List<LineItem> getLineItems(int orderId) {
        Order order = this.find(orderId);
        return order.getLineItems();
    }
}
