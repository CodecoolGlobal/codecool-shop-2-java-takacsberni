package com.codecool.shop.dao.implementation;

import javax.sql.DataSource;
import java.sql.SQLException;
import org.postgresql.ds.PGSimpleDataSource;

public class DatabaseManager {

    private DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        //PSQL_USER_NAME, PSQL_PASSWORD, and PSQL_DB_NAME
        String dbName = System.getenv("PSQL_DB_NAME");
        String user = System.getenv("PSQL_USER_NAME");
        String password = System.getenv("PSQL_PASSWORD");

        dataSource.setDatabaseName(dbName);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        System.out.println("Trying to connect...");
        dataSource.getConnection().close();
        System.out.println("Connection OK");

        return dataSource;
    }
}
