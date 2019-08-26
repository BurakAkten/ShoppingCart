package com.trendyol.codility;

public class Campaign {

    private Category category;
    private Double discount;
    private int ItemNubers;
    private int discountType;

    public Campaign(Category category, double discount, int itemNubers, int discountType){
        this.category = category;
        this.discount = new Double(discount);
        this.ItemNubers = itemNubers;
        this.discountType = discountType;
    }

    public Category getCategory() {
        return category;
    }

    public int getDiscountType() {
        return discountType;
    }

    public Double getDiscount() {
        return discount;
    }

    public int getItemNubers() {
        return ItemNubers;
    }

    @Override
    public String toString() {
        return "Category: "+ this.getCategory() + ", Discount: " + this.getDiscount() + ", Discount Type: " + this.getDiscountType() + ", Item Numbers: " + this.ItemNubers;
    }
}
