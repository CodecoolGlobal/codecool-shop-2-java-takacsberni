package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.database_implementation.DatabaseManager;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.SupplierService;
import com.codecool.shop.service.UserService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/checkout_cart"})

public class CheckoutCartController extends HttpServlet {
    DatabaseManager databaseManager = DatabaseManager.getInstance();
    OrderDao orderDataStore = databaseManager.getOrderDataStore();
    LineItemDao lineItemDataStore = databaseManager.getLineItemDataStore();
    OrderService orderservice = new OrderService(orderDataStore, lineItemDataStore);

    public CheckoutCartController() throws IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = databaseManager.getProductDataStore();
        ProductCategoryDao productCategoryDataStore = databaseManager.getProductCategoryDataStore();
        SupplierDao supplierDataStore = databaseManager.getSupplierDataStore();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDataStore);
        SupplierService supplierService = new SupplierService(supplierDataStore);
        UserDao userDao = DatabaseManager.getInstance().getUserDao();
        UserService userService = new UserService(userDao);
        List<LineItem> items = orderservice.getLineItemsByOrder(orderservice.getCurrentOrderId());
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("all_categories", productService.getAllCategories());
        context.setVariable("all_suppliers", supplierService.getAllSuppliers());
        context.setVariable("products", productService.getProductsForCategory(1));
        context.setVariable("items", items);
        HttpSession session = req.getSession();
        String email = (String) session.getAttribute("user");
        HashMap<String, String> customerData = new HashMap<>();
        try {
            User user = userService.findUserByEmail(email);
            customerData = userService.convertToHashMap(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        context.setVariable("name", customerData.get("name"));
        context.setVariable("email", customerData.get("email"));
        context.setVariable("phone", customerData.get("phone"));
        context.setVariable("billing_address", customerData.get("billingAddress"));
        context.setVariable("billing_city", customerData.get("billingCity"));
        context.setVariable("billing_zipcode", customerData.get("billingZipCode"));
        context.setVariable("billing_country", customerData.get("billingCountry"));
        context.setVariable("shipping_address", customerData.get("shippingAddress"));
        context.setVariable("shipping_city", customerData.get("shippingCity"));
        context.setVariable("shipping_zipcode", customerData.get("shippingZipCode"));
        context.setVariable("shipping_country", customerData.get("shippingCountry"));
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/checkout_cart.html", context, resp.getWriter());

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
        Order order = orderservice.getOrderById(orderservice.getCurrentOrderId());
        order.setCustomerData(parameters);
        response.setContentType("text/html");
        PrintWriter pw=response.getWriter();

        response.sendRedirect("/payment");

        pw.close();
    }

}
