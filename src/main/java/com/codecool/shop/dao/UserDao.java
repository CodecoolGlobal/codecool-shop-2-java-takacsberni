package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    void add(User user) throws SQLException;
    User find(int id);
    User findByEmail(String email) throws SQLException;
}
