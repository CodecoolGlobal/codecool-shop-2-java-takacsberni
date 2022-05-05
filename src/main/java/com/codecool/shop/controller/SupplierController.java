package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Supplier;
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
import java.util.ArrayList;
import java.util.List;

    @WebServlet(urlPatterns = {"/supplier"})
    public class SupplierController extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            ProductDao productDataStore = ProductDaoMem.getInstance();
            ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
            ProductService productService = new ProductService(productDataStore, productCategoryDataStore, supplierDataStore);
            SupplierService supplierService = new SupplierService(supplierDataStore);

            OrderDao orderDataStore = OrderDaoMem.getInstance();
            OrderService orderservice = new OrderService(orderDataStore);
            int currentOrder = orderservice.getCurrentOrderId();
            List<LineItem> lineItems = orderservice.getLineItems(currentOrder);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            int supplierId = Integer.parseInt(req.getParameter("supplier_id"));
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("supplier", productService.getSupplier(supplierId));
            context.setVariable("products", productService.getProductsBySupplier(supplierId));
            context.setVariable("all_categories", productService.getAllCategories());
            context.setVariable("all_suppliers", supplierService.getAllSuppliers());
            context.setVariable("item_number", lineItems.size());
            // // Alternative setting of the template context
            // Map<String, Object> params = new HashMap<>();
            // params.put("category", productCategoryDataStore.find(1));
            // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
            // context.setVariables(params);
            engine.process("product/index.html", context, resp.getWriter());
        }
    }

