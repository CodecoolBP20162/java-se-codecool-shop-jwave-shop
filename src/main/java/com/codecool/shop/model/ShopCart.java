package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabor on 2017.04.28..
 * <p>
 * Create Shopping Cart
 * If 'Add to Cart' is clicked , it's create a LineItem only if it does not extists.
 * If so it's increase it's quantity
 *
 */
public class ShopCart {

    private Integer id;
    private String status;
    private List<LineItem> shoppingCart;
    private static ShopCart instance;
    float totalPrice = 0;

    /**
     *
     * @param product add to Shopping Cart only if it's does not exist, if so, it's increase it's quantity.
     */

    public void addShoppingCart(Product product) {
        boolean notFound = true;
        for(LineItem lineItem  : shoppingCart) {
            if(lineItem.getProduct().getId() == product.getId()) {
                lineItem.setQuantity(lineItem.getQuantity()+1);
                notFound = false;
            }
        }
        if (notFound) {
            LineItem lineItem = new LineItem(product, 1);
            shoppingCart.add(lineItem);
        }
    }

    public LineItem getShoppingCart(int id) {
        return shoppingCart.get(id);
    }

    /**
     *
     * @return the only one shopping cart
     */
    public static ShopCart getInstance() {
        if (instance == null) {
            instance = new ShopCart();
        }
        return instance;
    }

    /**
     *
     * @return line items in shopping cart
     */
    public List<LineItem> getAllCarts() {
        return shoppingCart;
    }

    public ShopCart() {
        shoppingCart = new ArrayList<>();

    }

    public float getTotalPrice() {
        for (LineItem lineItem: shoppingCart) {
            this.totalPrice += lineItem.getPrice();
        }
        return totalPrice;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }



}