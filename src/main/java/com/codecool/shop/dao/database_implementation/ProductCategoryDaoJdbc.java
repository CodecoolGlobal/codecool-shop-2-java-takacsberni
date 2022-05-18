package com.codecool.shop.dao.database_implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJdbc implements ProductCategoryDao {
    DataSource dataSource;

    public ProductCategoryDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(ProductCategory category) {
        try(Connection connection = dataSource.getConnection()){
            String sql = "INSERT INTO category (name, description, department) VALUES (?, ? ,?)";
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, category.getName());
            st.setString(2, category.getDescription());
            st.setString(3, category.getDepartment());
            st.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding product category", throwables);
        }
    }

    @Override
    public ProductCategory find(int id) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT name, description, department FROM category WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return new ProductCategory(rs.getString(1), rs.getString(2), rs.getString(3));
        }
        catch(SQLException e){
            throw new RuntimeException("Error while reading category with id" + id, e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, description, department FROM category";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<ProductCategory> categories = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                ProductCategory category = new ProductCategory(rs.getString(1), rs.getString(2), rs.getString(3));
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all categories", e);
        }
    }
}
