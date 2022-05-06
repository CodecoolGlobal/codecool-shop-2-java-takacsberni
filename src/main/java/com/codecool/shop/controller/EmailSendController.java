package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.send_email.SendEmail;
import com.codecool.shop.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/send_email"})
public class EmailSendController extends HttpServlet {
    OrderDao orderDataStore = OrderDaoMem.getInstance();
    OrderService orderservice = new OrderService(orderDataStore);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Order order = orderservice.getOrderById(1);
        HashMap orderDetails = order.getCustomerData();
        order.setCustomerData(orderDetails);
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter pw=response.getWriter();

        SendEmail sendEmail = new SendEmail(String.valueOf(orderDetails.get("email"))); //email address given by the user / form

        OrderDao orderDataStore = OrderDaoMem.getInstance();
        OrderService orderservice = new OrderService(orderDataStore);
        Map customerDataMap = request.getParameterMap();
        HashMap customerData = order.getCustomerData();
        String emailData = convertMapToHTML(customerDataMap, customerData);
//        String emailData = request.getParameterMap().toString() + order.getCustomerData();
        try {
            SendEmail.sendMail(emailData);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/confirmation");

        pw.close();
    }

    private String convertMapToHTML(Map customerDataMap, HashMap customerData){

        //TODO: akkor itt meg kéne megoldani, hogy HTML formátumot adjon vissza
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(customerDataMap.toString() + customerData.toString());

        return stringBuilder.toString();
    }

}
