package com.codecool.shop.dao.database_implementation;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
    private static DatabaseManager databaseManager = null;
    private static DataSource dataSource;
    private static Properties properties;
    private ProductDao productDataStore;
    private SupplierDao supplierDataStore;
    private ProductCategoryDao productCategoryDataStore;
    private OrderDao orderDataStore;
    private UserDao userDao;
    private LineItemDao lineItemDataStore;

    private DatabaseManager() throws IOException {
        properties = initializeProperties();
    }

    public static DatabaseManager getInstance() throws IOException {
        if(databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public Properties getProperties() {
        return properties;
    }

    public ProductDao getProductDataStore() {
        return productDataStore;
    }

    public SupplierDao getSupplierDataStore() {
        return supplierDataStore;
    }

    public ProductCategoryDao getProductCategoryDataStore() {
        return productCategoryDataStore;
    }

    public OrderDao getOrderDataStore() {
        return orderDataStore;
    }

    public LineItemDao getLineItemDataStore() {
        return lineItemDataStore;
    }

    public void setup() throws IOException, SQLException {
        dataSource = connect(properties);
        userDao = new UserDaoJdbc(dataSource);
        productDataStore = new ProductDaoJdbc(dataSource);
        supplierDataStore = new SupplierDaoJdbc(dataSource);
        productCategoryDataStore = new ProductCategoryDaoJdbc(dataSource);
        orderDataStore = new OrderDaoJdbc(dataSource);
        lineItemDataStore = new LineItemDaoJdbc(dataSource);

    }

    private Properties initializeProperties() throws IOException {
        Properties properties = new Properties();
        String root = new File(System.getProperty("user.dir")).getAbsolutePath();
        String configPath = root+"/src/main/resources/connection.properties";
        properties.load(new FileInputStream(configPath));

        return properties;
    }

    private DataSource connect(Properties properties) throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        String url = properties.getProperty("url");
        String database = properties.getProperty("database");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String dao = properties.getProperty("dao");

        dataSource.setDatabaseName(database);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect");
        dataSource.getConnection().close();
        System.out.println("Connection ok.");


        return dataSource;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setupMem(){ //ezt az Initializer setup()-ból hoztuk ki ide (else ágból)
        productDataStore = ProductDaoMem.getInstance();
        productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        supplierDataStore = SupplierDaoMem.getInstance();
        orderDataStore = OrderDaoMem.getInstance();
        lineItemDataStore = LineItemDaoMem.getInstance();
    }
}
