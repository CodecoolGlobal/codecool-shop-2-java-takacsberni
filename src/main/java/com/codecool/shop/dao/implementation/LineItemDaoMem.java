package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class LineItemDaoMem implements LineItemDao {

    private List<LineItem> lineItems = new ArrayList<>();
    private static LineItemDaoMem instance = null;

    public static LineItemDaoMem getInstance() {
        if (instance == null) {
            instance = new LineItemDaoMem();
        }
        return instance;
    }

    @Override
    public void add(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    @Override
    public LineItem find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<LineItem> getAll() {
        return lineItems;
    }

    @Override
    public List<LineItem> getLineItems(int orderId) {
        List<LineItem> result = new ArrayList<>();
        for (LineItem lineItem : lineItems) {
            if (lineItem.getOrderId() == orderId) {
                result.add(lineItem);
            }
        }
        return result;
    }
}

