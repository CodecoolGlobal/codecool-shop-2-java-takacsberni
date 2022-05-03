package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier chernobylPetShop = new Supplier("Chernobyl Pet Shop", "Exotic animals from Chernobyl");
        supplierDataStore.add(chernobylPetShop);
        //setting up a new product category
        ProductCategory cat = new ProductCategory("Cat", "Animals", "The cat is a domestic species of small carnivorous mammal.");
        productCategoryDataStore.add(cat);
        //setting up products and printing it
        productDataStore.add(new Product("Bear Cat", new BigDecimal("10000"), "USD", "This bear cat is an authentic animal from Chernobyl, might still radiate.", cat, chernobylPetShop));
        productDataStore.add(new Product("Rabbit Cat", new BigDecimal("3000"), "USD", "The rabbit cat is the nicest little thing caused by the accident. (only if you don't mind that it eats only human meat)", cat, chernobylPetShop));
        productDataStore.add(new Product("Snail Cat", new BigDecimal("1000"), "USD", "The snail cat is the slowest animal, but when it sees a laser pointer it becomes the fastest animal in Chernobyl.", cat, chernobylPetShop));
        productDataStore.add(new Product("Pig Cat", new BigDecimal("6000"), "USD", "Pig cats are just like pigs with just a few differences. Tends to meow and also cleaner.", cat, chernobylPetShop));
    }
}
