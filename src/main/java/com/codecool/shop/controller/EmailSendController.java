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

        SendEmail sendEmail = new SendEmail(String.valueOf(orderDetails.get("email")));

        OrderDao orderDataStore = OrderDaoMem.getInstance();
        OrderService orderservice = new OrderService(orderDataStore);
        String data = request.getParameterMap().toString() + order.getCustomerData();
        try {
            SendEmail.sendMail(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/confirmation");

        pw.close();
    }

}
