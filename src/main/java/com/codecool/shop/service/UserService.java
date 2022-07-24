package com.codecool.shop.service;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.database_implementation.UserDaoJdbc;
import com.codecool.shop.model.User;

import java.sql.SQLException;
import java.util.HashMap;

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

    public User findUserByEmail(String email) throws SQLException {
         return userDao.findByEmail(email);
    }

    public HashMap<String, String> convertToHashMap(User user) {
        HashMap<String, String> customerData = new HashMap<>();
        customerData.put("name", user.getName());
        customerData.put("email", user.getEmail());
        customerData.put("phone", user.getPhone_number());
        customerData.put("billingAddress", user.getBilling_address());
        customerData.put("billingCity", user.getBilling_city());
        customerData.put("billingZipCode", user.getBilling_zipcode());
        customerData.put("billingCountry", user.getBilling_country());
        customerData.put("shippingAddress", user.getShipping_address());
        customerData.put("shippingCity", user.getShipping_city());
        customerData.put("shippingZipCode", user.getShipping_zipcode());
        customerData.put("shippingCountry", user.getShipping_country());
        return customerData;
    }
}
