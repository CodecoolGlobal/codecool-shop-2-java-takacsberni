package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet(urlPatterns = {"/order"})
public class OrderController extends HttpServlet{

    OrderDao orderDataStore = OrderDaoMem.getInstance();
    OrderService orderservice = new OrderService(orderDataStore);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BigDecimal productPrice = BigDecimal.valueOf(1200);//new BigDecimal(request.getParameter("prod_price"));
        String productName = request.getParameter("prod_name");
        orderservice.addNewOrder(productPrice, productName);
        int lineItemNumber = orderservice.getLineItems(1).size();
        /* js: fetch ami most a hrefben van, response szerverről: az item numbert visszaküldeni és js-sel berakni*/
        PrintWriter out = response.getWriter();
        out.println(lineItemNumber);

    }

    private boolean IsOrderListEmpty() {
        if(orderservice.getAllOrders() == null){
            return true;
        }
        else{
            return false;
        }
    }
}
