package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierCategoryStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("category", productCategoryDataStore.find(1));

        List<ProductCategory> categories = productCategoryDataStore.getAll();
        params.put("categories", categories);


        params.put("supplier", supplierCategoryStore.find(1));

        List<Supplier> suppliers = supplierCategoryStore.getAll();
        params.put("suppliers", suppliers);

        String selectedSupplierName = req.params(":supplierName");
        String selectedCategoryName = req.params(":categoryName");

        if (selectedSupplierName != null) {
            Supplier selectedSupplier = supplierCategoryStore.find(selectedSupplierName);
            params.put("products", productDataStore.getBy(selectedSupplier));
        } else if (selectedCategoryName != null){
            ProductCategory selectedCategory = productCategoryDataStore.find(selectedCategoryName);
            params.put("products", productDataStore.getBy(selectedCategory));
        } else {
            params.put("products", productDataStore.getAll());
        }

        return new ModelAndView(params, "product/index");
    }
}







