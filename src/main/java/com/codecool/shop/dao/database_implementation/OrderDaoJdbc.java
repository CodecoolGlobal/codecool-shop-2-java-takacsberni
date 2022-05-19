package com.codecool.shop.dao.database_implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoJdbc implements OrderDao {

    DataSource dataSource;

    public OrderDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Order order) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO \"order\" (status, date, user_id) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, order.getStatus());
            statement.setDate(2, new java.sql.Date(order.getDate().getTime()));
            statement.setInt(3, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order find(int id) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM \"order\" WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) { // first row was not found == no data was returned by the query
                return null;
            }
            Order order = new Order(rs.getInt(1), rs.getInt(4), rs.getDate(3), rs.getString(2));
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM \"order\"";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Order> result = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                Order order = new Order(rs.getInt(1), rs.getInt(4), rs.getDate(3), rs.getString(2));
                result.add(order);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all GameStates", e);
        }
    }

}
