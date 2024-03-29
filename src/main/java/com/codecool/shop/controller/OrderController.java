package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.LineItemDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.database_implementation.DatabaseManager;
import com.codecool.shop.dao.implementation.LineItemDaoMem;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = {"/order"})
public class OrderController extends HttpServlet {

    DatabaseManager databaseManager = DatabaseManager.getInstance();
    OrderDao orderDataStore = databaseManager.getOrderDataStore();
    LineItemDao lineItemDataStore = databaseManager.getLineItemDataStore();
    OrderService orderservice = new OrderService(orderDataStore, lineItemDataStore);

    public OrderController() throws IOException {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String strProductPrice = request.getParameter("prod_price");
        strProductPrice = strProductPrice.replaceAll("[^\\d.]", "");
        BigDecimal productPrice = new BigDecimal(strProductPrice);
        String productDescription = request.getParameter("desc");
        String productName = request.getParameter("prod_name");
        String supplier = request.getParameter("supplier");
        int productId = Integer.parseInt(request.getParameter("prod_id"));
        orderservice.addLineItemOrUpdateQuantity(productName, productPrice, productDescription, supplier, productId);
        List<LineItem> lineItems = orderservice.getLineItemsByOrder(orderservice.getCurrentOrderId());
        int lineItemNumber = lineItems.size();
        PrintWriter out = response.getWriter();
        out.println(lineItemNumber);

    }


}

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        String body = request.getReader().lines()
//                .reduce("", (accumulator, actual) -> accumulator + actual);
//        HashMap<String,String> map = new Gson().fromJson(body, new TypeToken<HashMap<String, String>>(){}.getType());
//
//        String productName = map.get("prod_name");
//        String str = map.get("prod_price");
//        str = str.replaceAll("[^\\d.]", "");
//        BigDecimal productPrice = new BigDecimal(str);
//        orderservice.addNewOrder(productPrice, productName);
//        int orderId = 1;
//        /* js: fetch ami most a hrefben van, response szerverről: az item numbert visszaküldeni és js-sel berakni*/
//        Order order = orderservice.getOrderById(orderId);
//        Gson gson = new Gson();
//        String orderJsonString = gson.toJson(order);
//
//        PrintWriter out = response.getWriter();
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        out.print(orderJsonString);
//        out.flush();
//    }
