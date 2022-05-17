package com.codecool.shop.dao.database_implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.sql.DataSource;

public class UserDaoJdbc implements UserDao {
    /** Kiteljesíti a UserDao-ban meghatározottakat: add, find, findByEmail
    **/

    private DataSource dataSource;

    public UserDaoJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void add(User user){

    }

    @Override
    public User find(int id){
        return null;
    }

    @Override
    public User findByEmail(String email){
        return null;
    }
}
