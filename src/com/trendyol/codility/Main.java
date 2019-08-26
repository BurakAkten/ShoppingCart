package com.trendyol.codility;

public class Main {

    public static void main(String[] args) {

        Category category = new Category("food");
        Category category1 = new Category("drink");

        Product apple = new Product("Apple" , 100.0 , category);
        Product almond = new Product("Almonds" , 150.0 , category);
        Product water = new Product("Water", 50.0, category1);
        Product juice = new Product("Juice", 75.0, category1);


        ShoppingCart cart = new ShoppingCart();
        cart.addItem(apple, 3);
        cart.addItem(almond, 2);
        cart.addItem(water, 4);
        cart.addItem(juice, 2);
        //cart.addItem(null, 2);



        Campaign campaign1 = new Campaign(category, 20.0, 3, DiscountType.Rate );
        Campaign campaign2 = new Campaign(category1, 40.0, 5, DiscountType.Rate );
        Campaign campaign3 = new Campaign(category, 5.0, 5, DiscountType.Amount );

        cart.applyDiscounts(campaign1, campaign2, campaign3);
        //cart.applyCoupon(null);

        Coupon coupon = new Coupon(100.0, 10, DiscountType.Rate);
        cart.applyCoupon(coupon);

        cart.print();

    }
}
