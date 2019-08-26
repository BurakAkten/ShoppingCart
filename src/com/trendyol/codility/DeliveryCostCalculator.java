package com.trendyol.codility;

import java.util.HashSet;
import java.util.Set;

public class DeliveryCostCalculator {

    private ShoppingCart Cart;
    private Double CostPerDelivery;
    private Double CostPerProduct;
    private Double FixedCost;

    /**
     * Constructor to make initializations
     * @param costPerDelivery Cost for per delivery
     * @param costPerProduct Cost for per product
     * @param fixedCost The fixed cost for delivery
     */
    public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost){
        this.CostPerDelivery = costPerDelivery;
        this.CostPerProduct = costPerProduct;
        this.FixedCost = fixedCost;
    }

    /**
     * Apply the formula to calculate delivery cost.
     * @param cart The cart to calculate the cost
     * @return The delivery cost
     */
    public Double calculateFor(ShoppingCart cart){
        if(cart != null && cart.getCart() != null && cart.getCart().size() > 0){
            this.Cart = cart;
            return (this.CostPerDelivery * calculateNumberOfDeliveries(cart)) +
                    (this.CostPerProduct * calculateNumberOfProducts(cart)) + this.FixedCost;
        }
        else{
            System.err.println("ERROR: The delivery cost can not be calculated. (ShoppingCart is empty or null) \n");
            System.exit(1);
            return null;
        }

    }

    /**
     * Calculate the number of deliveries
     * @param cart The cart to find deliveries number
     * @return The number of deliveries
     */
    private int calculateNumberOfDeliveries(ShoppingCart cart){
        Set<String> categories = new HashSet<>();

        for (Product product:cart.getCart()) {
            categories.add(product.getCategory().getTitle());
        }

        return categories.size();
    }

    /**
     * Calculate the number of products
     * @param cart The cart to find products number
     * @return The number of products
     */
    private int calculateNumberOfProducts(ShoppingCart cart){
        Set<String> products = new HashSet<>();

        for (Product product:cart.getCart()) {
            products.add(product.getTitle());
        }

        return products.size();
    }

}
