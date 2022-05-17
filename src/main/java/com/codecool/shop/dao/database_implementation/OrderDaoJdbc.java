package com.codecool.shop.dao.database_implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;

import javax.sql.DataSource;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {

    DataSource dataSource;

    public OrderDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Order order) {

    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }

    @Override
    public List<LineItem> getLineItems(int orderId) {
        return null;
    }
}
