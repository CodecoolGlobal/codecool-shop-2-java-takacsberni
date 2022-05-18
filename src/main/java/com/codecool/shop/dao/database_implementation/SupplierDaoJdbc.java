package com.codecool.shop.dao.database_implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJdbc implements SupplierDao {
    DataSource dataSource;

    public SupplierDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(Supplier supplier) {
        try(Connection connection = dataSource.getConnection()){
            String sql = "INSERT INTO supplier (name, description) VALUES (?, ?)";
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, supplier.getName());
            st.setString(2, supplier.getDescription());
            st.executeUpdate();
        } catch (SQLException throwables) {
            throw new RuntimeException("Error while adding supplier", throwables);
        }
    }

    @Override
    public Supplier find(int id) {
        try(Connection conn = dataSource.getConnection()){
            String sql = "SELECT name, description FROM supplier WHERE id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return new Supplier(rs.getString(1), rs.getString(2));
        }
        catch(SQLException e){
            throw new RuntimeException("Error while reading supplier with id" + id, e);
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name, description FROM supplier";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            List<Supplier> suppliers = new ArrayList<>();
            while (rs.next()) { // while result set pointer is positioned before or on last row read authors
                Supplier supplier = new Supplier(rs.getString(1), rs.getString(2));
                suppliers.add(supplier);
            }
            return suppliers;
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading all suppliers", e);
        }
    }
}
