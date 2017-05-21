package com.codecool.shop.model;

/**
 * Created by gabor on 2017.05.10..
 * @author gabor 2002.02.11..
 *  This class creates LineItem  in the ShoppingCart modal when 'Add to Cart'
 *  button clicked
 *
 *
 *
 */
public class LineItem {
    private Product product;
    private int quantity;

    /**
     *
     * @param product
     * @param quantity
     */

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     *
     * @return the product
     */

    public Product getProduct() {
        return product;
    }

    /**
     *
     * @param product set the product
     */

    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     *
     * @return quantity of this product
     */

    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity set quantity of this product
     */

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     *
     * @return the sum price of this product
     */

    public float getPrice() {
        return quantity*product.getDefaultPrice();
    }

    /**
     *
     * @return the name of this product
     */

    public String getName() {
        return product.getName();
    }


}

