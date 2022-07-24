package com.codecool.shop.controller;

import com.codecool.shop.dao.*;
import com.codecool.shop.dao.database_implementation.DatabaseManager;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.service.OrderService;
import com.codecool.shop.service.ProductService;
import com.codecool.shop.config.TemplateEngineUtil;
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

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        ProductDao productDataStore = databaseManager.getProductDataStore();
        ProductCategoryDao productCategoryDataStore = databaseManager.getProductCategoryDataStore();
        SupplierDao supplierDataStore = databaseManager.getSupplierDataStore();
        OrderDao orderDataStore = databaseManager.getOrderDataStore();
        LineItemDao lineItemDataStore = databaseManager.getLineItemDataStore();
        OrderService orderservice = new OrderService(orderDataStore, lineItemDataStore);

        ProductService productService = new ProductService(productDataStore,productCategoryDataStore, supplierDataStore);
        SupplierService supplierService = new SupplierService(supplierDataStore);

        List<LineItem> items = orderservice.getLineItemsByOrder(orderservice.getCurrentOrderId());
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", productService.getProductCategory(1));
        context.setVariable("all_categories", productService.getAllCategories());
        context.setVariable("all_suppliers", supplierService.getAllSuppliers());
        context.setVariable("products", productService.getProductsForCategory(1));
        context.setVariable("items", items);
        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);
        engine.process("product/index.html", context, resp.getWriter());

    }

}
