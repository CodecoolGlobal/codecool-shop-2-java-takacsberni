package com.codecool.shop.dao.database_implementation;

import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.model.LineItem;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LineItemDaoJdbc implements LineItemDao {

    DataSource dataSource;

    public LineItemDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(LineItem lineItem) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO line_item (quantity, order_id, product_id) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, lineItem.getQuantity());
            statement.setInt(2, lineItem.getOrderId());
            statement.setInt(3, lineItem.getProductId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LineItem find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "DELETE FROM line_item WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        }
        catch(SQLException e){
            throw new RuntimeException("Error while deleting line item with id" + id, e);
        }

    }

    @Override
    public List<LineItem> getAll() {
        return null;
    }

    @Override
    public List<LineItem> getLineItems(int orderId) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT line_item.quantity, product.default_price, product.name, product.description, order.id, supplier.name, product.id " +
                    "FROM line_item " +
                    "JOIN product ON line_item.product_id = product.id " +
                    "JOIN order ON line_item.order_id = order.id " +
                    "JOIN supplier on product.supplier_id = supplier.id" +
                    " WHERE order_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, orderId);
            ResultSet rs = st.executeQuery();
            List<LineItem> result = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                LineItem lineItem = new LineItem(rs.getInt(1), rs.getBigDecimal(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7));
                result.add(lineItem);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all LineItems", e);
        }
    }
}
