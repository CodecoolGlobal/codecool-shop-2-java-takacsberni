package com.codecool.shop.service;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.database_implementation.UserDaoJdbc;
import com.codecool.shop.model.User;

import java.sql.SQLException;

public class UserService {
    //TODO: regisztrációnál a post ezen keresztül hívja meg a userDaoJdbc-n az add()-ot
    private static UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean registration(User user) throws SQLException {
//        if (userDao.findByEmail(user.getEmail()) == null){
            userDao.add(user);
            return true;
//        }
//        else {
//
//            return false;
//        }
    }
}
