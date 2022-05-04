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
        productDataStore.add(new Product("Bunny", new BigDecimal("8900"), "HUF", "Funny bunny for sale", smallMammals, smallPetSelect));
        productDataStore.add(new Product("Iguana", new BigDecimal("47900"), "HUF", "Amazing rockstar iguanas waiting for you!", reptiles, petSuppliesPlus));
        productDataStore.add(new Product("Oppossum", new BigDecimal("49.9"), "HUF", "Playful oppossums - good choice for children! ", pocketPets, superPetStore));
    }
}
