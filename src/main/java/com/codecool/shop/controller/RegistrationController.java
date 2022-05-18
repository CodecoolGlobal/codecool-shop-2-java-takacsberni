package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.database_implementation.DatabaseManager;
import com.codecool.shop.dao.database_implementation.UserDaoJdbc;
import com.codecool.shop.dao.implementation.LineItemDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController extends HttpServlet {
//    OrderDao orderDataStore = OrderDaoMem.getInstance();
//    LineItemDao lineItemDao = LineItemDaoMem.getInstance();
//    OrderService orderservice = new OrderService(orderDataStore, lineItemDao);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("product/registration.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
// //        working with memory:
//        HashMap<String, String> parameters = new HashMap<>();
//        parameters.put("name", request.getParameter("name"));
//        parameters.put("email", request.getParameter("email"));
//        parameters.put("password", request.getParameter("password"));
//
//        Order order = orderservice.getOrderById(orderservice.getCurrentOrderId());
//        order.setCustomerData(parameters);
//        response.setContentType("text/html");
//        PrintWriter pw=response.getWriter();
//
//        response.sendRedirect("product/index.html");
//
//        pw.close();
        User user = new User(request.getParameter("email"), request.getParameter("password"), request.getParameter("name"));
        if (request.getParameter("phone_number") != null){
            user.setPhone_number(request.getParameter("phone_number"));
        }
        if (request.getParameter("billing_country") != null){
            user.setBilling_country(request.getParameter("billing_country"));
        }
        if (request.getParameter("billing_city") != null){
            user.setBilling_city(request.getParameter("billing_city"));
        }
        if (request.getParameter("billing_zip") != null){
            user.setBilling_zipcode(request.getParameter("billing_zip"));
        }
        if (request.getParameter("billing_address") != null){
            user.setBilling_address(request.getParameter("billing_address"));
        }
        if (request.getParameter("shipping_country") != null){
            user.setShipping_country(request.getParameter("shipping_country"));
        }
        if (request.getParameter("shipping_city") != null){
            user.setShipping_city(request.getParameter("shipping_city"));
        }
        if (request.getParameter("shipping_zip") != null){
            user.setShipping_zipcode(request.getParameter("shipping_zip"));
        }
        if (request.getParameter("shipping_address") != null){
            user.setShipping_address(request.getParameter("shipping_address"));
        }
        UserDao userDao = DatabaseManager.getInstance().getUserDao();
        UserService userService = new UserService(userDao);
        try {
            if (userService.registration(user)){
                response.sendRedirect(request.getContextPath()+"/");
            }
            else {
                System.out.println("Already registered.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
