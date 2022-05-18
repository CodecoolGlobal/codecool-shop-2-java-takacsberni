package com.codecool.shop.dao.database_implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }
}
