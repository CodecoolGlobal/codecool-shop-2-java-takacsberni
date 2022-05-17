package com.codecool.shop.dao.database_implementation;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;

import javax.sql.DataSource;
import java.util.List;

public class LineItemDaoJdbc implements LineItemDao {

    DataSource dataSource;

    public LineItemDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(LineItem lineItem) {

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
        return null;
    }
}
