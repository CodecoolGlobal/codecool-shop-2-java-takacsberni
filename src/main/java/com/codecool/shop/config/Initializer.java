package com.codecool.shop.config;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.database_implementation.DatabaseManager;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Objects;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        ProductDao productDataStore;
        SupplierDao supplierDataStore;
        ProductCategoryDao productCategoryDataStore;
        OrderDao orderDataStore;

        try {
            databaseManager.setup();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Input/Output error");
        } catch (SQLException e) {
            System.out.println("Can't connect to the database.");
        }


        String daoProperty = databaseManager.getProperties().getProperty("dao");

        if (Objects.equals(daoProperty, "jdbc")){
            productDataStore = databaseManager.getProductDataStore();
            supplierDataStore = databaseManager.getSupplierDataStore();
            productCategoryDataStore = databaseManager.getProductCategoryDataStore();
            orderDataStore = databaseManager.getOrderDataStore();
        }
        else if (Objects.equals(daoProperty, "memory")){ //EZEK A KORÁBBI RÉSZEK VÁLTOZTATÁS NÉLKÜL
            productDataStore = ProductDaoMem.getInstance();
            productCategoryDataStore = ProductCategoryDaoMem.getInstance();
            supplierDataStore = SupplierDaoMem.getInstance();
            orderDataStore = OrderDaoMem.getInstance();

            int orderId = (orderDataStore).getAll().size()+1;
            Order newOrder = new Order(orderId);
            orderDataStore.add(newOrder);

            //setting up a new supplier
            Supplier chernobylPetShop = new Supplier("Chernobyl Pet Shop", "Exotic animals from Chernobyl");
            supplierDataStore.add(chernobylPetShop);
            Supplier petSuppliesPlus = new Supplier("Pet Supplies Plus", "Pet Supplies Plus is your local pet store carrying a wide variety of pets");
            supplierDataStore.add(petSuppliesPlus);
            Supplier superPetStore = new Supplier("Super Pet Store", "Super Pet Store is the best choice for pocket pets!");
            supplierDataStore.add(superPetStore);
            Supplier smallPetSelect = new Supplier("Small Pet Select", "Browse our small animals and discover the joy of bringing home one of the many pets for sale!");
            supplierDataStore.add(smallPetSelect);

            //setting up a new product category
            ProductCategory cat = new ProductCategory("Cat", "Animals", "The cat is a domestic species of small carnivorous mammal.");
            productCategoryDataStore.add(cat);
            ProductCategory reptiles = new ProductCategory("Reptiles", "Animals", "Reptiles are air-breathing vertebrates covered in special skin made up of scales, bony plates, or a combination of both.");
            productCategoryDataStore.add(reptiles);
            ProductCategory pocketPets = new ProductCategory("Pocket Pets", "Animals", "Pocket Pet refers to small animals such as guinea pigs, hamsters, hedgehogs, mice, rats, gerbils, chinchillas, and sugar gliders.");
            productCategoryDataStore.add(pocketPets);
            ProductCategory smallMammals = new ProductCategory("Small Mammals", "Animals", "Small mammals include a few animals that are not so small, such as rabbits and prairie dogs.");
            productCategoryDataStore.add(smallMammals);


            //setting up products and printing it
            productDataStore.add(new Product("Bear Cat", new BigDecimal("10000"), "USD", "This bear cat is an authentic animal from Chernobyl, might still radiate.", cat, chernobylPetShop));
            productDataStore.add(new Product("Rabbit Cat", new BigDecimal("3000"), "USD", "The rabbit cat is the nicest little thing caused by the accident. (only if you don't mind that it eats only human meat)", cat, chernobylPetShop));
            productDataStore.add(new Product("Snail Cat", new BigDecimal("1000"), "USD", "The snail cat is the slowest animal, but when it sees a laser pointer it becomes the fastest animal in Chernobyl.", cat, chernobylPetShop));
            productDataStore.add(new Product("Pig Cat", new BigDecimal("6000"), "USD", "Pig cats are just like pigs with just a few differences. Tends to meow and also cleaner.", cat, chernobylPetShop));
            productDataStore.add(new Product("Bunny", new BigDecimal("890"), "USD", "Funny bunny for sale", smallMammals, smallPetSelect));
            productDataStore.add(new Product("Iguana", new BigDecimal("470"), "USD", "Amazing rockstar iguanas waiting for you!", reptiles, petSuppliesPlus));
            productDataStore.add(new Product("Oppossum", new BigDecimal("49.9"), "USD", "Playful oppossums - good choice for children! ", pocketPets, superPetStore));
        }
    }
}
