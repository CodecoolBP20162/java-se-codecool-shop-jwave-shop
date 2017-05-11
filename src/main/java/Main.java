import com.codecool.shop.controller.ProductController;
import com.codecool.shop.dao.implementation.ProductCategoryDaoJdbc;
import com.codecool.shop.dao.implementation.ProductDaoJdbc;
import com.codecool.shop.dao.implementation.SupplierDaoJdbc;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.UI;
import spark.Request;
import spark.Response;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {

    public static void main(String[] args) {

        // default server settings
        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);

        // populate some data for the memory storage
        populateData();

        // Always add generic routes to the end
        get("/", ProductController::renderProducts, new ThymeleafTemplateEngine());
        // Equivalent with above
        get("/index", (Request req, Response res) -> {
           return new ThymeleafTemplateEngine().render( ProductController.renderProducts(req, res) );
        });
        get("/category/:categoryName", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render( ProductController.renderProducts(req, res) );
        });
        get("/supplier/:supplierName", (Request req, Response res) -> {
            return new ThymeleafTemplateEngine().render(ProductController.renderProducts(req, res));
        });
        get("/addtocart/:id", (Request req, Response res) -> {
                    return new ThymeleafTemplateEngine().render(ProductController.renderShoppingCarts(req, res));
                });
        //get("/cart", ProductController::renderShopCart, new ThymeleafTemplateEngine());



        // Add this line to your project to enable the debug screen
        enableDebugScreen();
        new UI();
    }

    public static void populateData() {

        ProductDaoJdbc productDataStore = new ProductDaoJdbc();
        ProductCategoryDaoJdbc productCategoryDataStore = new ProductCategoryDaoJdbc();
        SupplierDaoJdbc supplierDataStore = new SupplierDaoJdbc();
//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier(1, "Amazon", "Digital content and services");
        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier(2, "Lenovo", "Tablets");
        supplierDataStore.add(lenovo);
        Supplier samsung = new Supplier(3, "Samsung", "Computers");
        supplierDataStore.add(samsung);
        Supplier sony = new Supplier(4, "Sony", "Phones");
        supplierDataStore.add(sony);


        //setting up a new product category

        ProductCategory tablet = new ProductCategory(1, "tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        productCategoryDataStore.add(tablet);
        ProductCategory laptop = new ProductCategory(2, "laptop", "software", "goood");
        productCategoryDataStore.add(laptop);
        ProductCategory phone = new ProductCategory(3, "phone", "phone", "can call everybody");
        productCategoryDataStore.add(phone);


        //setting up products and printing it
        productDataStore.add(new Product(1, "Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product(2, "Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product(3, "Amazon Fire HD 8", 89, "USD", "Amazons latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product(4, "Lenovo", 70, "USD", "Light and fast.", laptop, lenovo));
        productDataStore.add(new Product(5, "Sony Xperia Z3 D6653 White", 110, "USD", "Sonys one of favourite product.", phone, sony));


    }



}
