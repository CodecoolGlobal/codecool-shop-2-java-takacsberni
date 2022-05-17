package com.codecool.shop.dao.database_implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbc implements ProductDao {
    DataSource dataSource;

    public ProductDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(Product product) {

    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        int supplierId = supplier.getId();
        List<Product> products = new ArrayList<>();

        try(Connection connection = dataSource.getConnection()){
            String sql = "SELECT product.name, product.description, product.default_price, product.currency, " +
                                "category.name, category.department, category.description," +
                                "supplier.name, supplier.description" +
                            "FROM product " +
                            "JOIN category ON product.category_id = category.id" +
                            "JOIN supplier ON product.category_id = supplier.id" +
                            "WHERE supplier_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, supplierId);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }

            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                ProductCategory category = new ProductCategory(rs.getString(5), rs.getString(6), rs.getString(7));
                supplier = new Supplier(rs.getString(8), rs.getString(9));
                Product product = new Product(rs.getString(1), rs.getBigDecimal(3), rs.getString(4), rs.getString(2), category, supplier);
                products.add(product);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Error while reading products with supplier: " + supplier, e);
        }
        return products;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        int categoryId = productCategory.getId();
        List<Product> products = new ArrayList<>();

        try(Connection connection = dataSource.getConnection()){
            String sql = "SELECT product.name, product.description, product.default_price, product.currency, " +
                    "category.name, category.department, category.description," +
                    "supplier.name, supplier.description" +
                    "FROM product " +
                    "JOIN category ON product.category_id = category.id" +
                    "JOIN supplier ON product.category_id = supplier.id" +
                    "WHERE supplier_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryId);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }

            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                productCategory = new ProductCategory(rs.getString(5), rs.getString(6), rs.getString(7));
                Supplier supplier = new Supplier(rs.getString(8), rs.getString(9));
                Product product = new Product(rs.getString(1), rs.getBigDecimal(3), rs.getString(4), rs.getString(2), productCategory, supplier);
                products.add(product);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException("Error while reading products with category: " + productCategory, e);
        }
        return products;

    }
}
