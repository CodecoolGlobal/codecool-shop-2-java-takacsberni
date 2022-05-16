package com.codecool.shop.dao.database_implementation;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class DatabaseManager {
    private static DatabaseManager databaseManager = null;
    private static Properties properties;

    public static DatabaseManager getInstance() {
        if(databaseManager == null) {
            databaseManager = new DatabaseManager();
        }
        return databaseManager;
    }

    public void setup() throws IOException {
        properties = initializeProperties();
        DataSource dataSource = connect(properties);

    }

    private Properties initializeProperties() throws IOException {
        Properties properties = new Properties();
        String root = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        String configPath = root+"connection.properties";
        properties.load(new FileInputStream(configPath));

        return properties;
    }

    private DataSource connect(Properties properties){
        DataSource dataSource = new DataSource

        return dataSource;
    }
}
