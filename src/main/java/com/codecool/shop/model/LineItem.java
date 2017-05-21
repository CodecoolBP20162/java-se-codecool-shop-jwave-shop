package com.codecool.shop.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gabor on 2017.05.10..
 */
public class LineItem {
    private Product product;
    private int quantity;

    private static final Logger logger = LoggerFactory.getLogger(LineItem.class);


    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        logger.info("New LineItem created: {} pcs of {}", quantity, product.name);

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        logger.info("ShoppingCart get the {}", product);

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return quantity*product.getDefaultPrice();
    }

    public String getName() {
        return product.getName();
    }


}

