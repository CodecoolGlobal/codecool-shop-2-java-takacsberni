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
        SendEmail sendEmail = new SendEmail("takacsberni@gmail.com");

        OrderDao orderDataStore = OrderDaoMem.getInstance();
        OrderService orderservice = new OrderService(orderDataStore);
        List price = orderservice.getAllOrders();

//        StringBuilder buffer = new StringBuilder();
//        BufferedReader reader = req.getReader();
//        String line;
//        while ((line = reader.readLine()) != null) {
//            buffer.append(line);
//            buffer.append(System.lineSeparator());
//        }
        String data = price.toString();
        try {
            SendEmail.sendMail(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("name", request.getParameter("name"));
        parameters.put("email", request.getParameter("email_address"));
        parameters.put("phone", request.getParameter("phone_number"));
        parameters.put("billingCountry", request.getParameter("billing_country"));
        parameters.put("billingCity", request.getParameter("billing_city"));
        parameters.put("billingZipCode", request.getParameter("billing_zipcode"));
        parameters.put("billingAddress", request.getParameter("billing_address"));
        parameters.put("shippingCountry", request.getParameter("shipping_country"));
        parameters.put("shippingCity", request.getParameter("shipping_city"));
        parameters.put("shippingZipCode", request.getParameter("shipping_zipcode"));
        parameters.put("shippingAddress", request.getParameter("shipping_address"));
        Order order = orderservice.getOrderById(1);
        order.setCustomerData(parameters);
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();

        SendEmail sendEmail = new SendEmail("takacsberni@gmail.com");

        OrderDao orderDataStore = OrderDaoMem.getInstance();
        OrderService orderservice = new OrderService(orderDataStore);
        Map price = request.getParameterMap();
        String data = price.toString();
        try {
            SendEmail.sendMail(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("/confirmation");

        pw.close();
    }

}
