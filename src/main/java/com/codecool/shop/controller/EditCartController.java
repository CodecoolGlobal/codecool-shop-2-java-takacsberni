package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.*;
import com.codecool.shop.dao.database_implementation.DatabaseManager;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.service.SupplierService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/edit_cart"})
public class EditCartController extends HttpServlet {
    DatabaseManager databaseManager = DatabaseManager.getInstance();
    OrderDao orderDataStore = databaseManager.getOrderDataStore();
    LineItemDao lineItemDataStore = databaseManager.getLineItemDataStore();
    OrderService orderservice = new OrderService(orderDataStore, lineItemDataStore);

    public EditCartController() throws IOException {
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = databaseManager.getProductDataStore();
        ProductCategoryDao productCategoryDataStore = databaseManager.getProductCategoryDataStore();
        SupplierDao supplierDataStore = databaseManager.getSupplierDataStore();
        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDataStore);
        SupplierService supplierService = new SupplierService(supplierDataStore);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());

        List<LineItem> items = orderservice.getLineItemsByOrder(1);

        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("all_categories", productService.getAllCategories());
        context.setVariable("all_suppliers", supplierService.getAllSuppliers());
        context.setVariable("items", items);
        context.setVariable("full_price", orderservice.getFullPrice(1));
        engine.process("product/edit_cart.html", context, resp.getWriter());

    }
}
