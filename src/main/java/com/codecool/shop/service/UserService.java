package com.codecool.shop.service;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.database_implementation.UserDaoJdbc;
import com.codecool.shop.model.User;

public class UserService {
    //TODO: regisztrációnál a post ezen keresztül hívja meg a userDaoJdbc-n az add()-ot
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void registration(User user){
        if (userDao.findByEmail(user.getEmail()) == null){
            userDao.add(user);
        }
        else {
            //TODO - létező user
        }
    }
}
