package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import com.codecool.shop.service.OrderService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController extends HttpServlet {
    OrderDao orderDataStore = OrderDaoMem.getInstance();
    OrderService orderservice = new OrderService(orderDataStore);

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
        User user = new User(request.getParameter("name"), request.getParameter("email"), request.getParameter("password"));
        if (request.getParameter("address") != null){
            user.setBilling_address(request.getParameter(""));
        }
    }
}
