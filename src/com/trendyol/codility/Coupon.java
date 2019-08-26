package com.trendyol.codility;

public class Coupon {

    private Double MinPurchaseAmount;
    private Double discount;
    private int discountType;

    public Coupon(double minPurchaseAmount, double discount, int discountType){

        this.MinPurchaseAmount = new Double(minPurchaseAmount);
        this.discount = new Double(discount);
        this.discountType = discountType;
    }

    public Double getDiscount() {
        return discount;
    }

    public int getDiscountType() {
        return discountType;
    }

    public Double getMinPurchaseAmount() {
        return MinPurchaseAmount;
    }

    @Override
    public String toString() {
        return "Discount: " + this.getDiscount() + ", Discount Type: " + this.getDiscountType() + ", Minimum Purchase Amount: " + this.getMinPurchaseAmount() + " TL";
    }
}
