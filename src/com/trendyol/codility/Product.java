package com.trendyol.codility;

public class Product {

    private String Title;
    private Double Price;
    private Category category;

    public Product(String title, double price, Category category){
        this.category = category;
        this.Title = title;
        this.Price = new Double(price);
    }

    public Category getCategory() {
        return category;
    }

    public Double getPrice() {
        return Price;
    }

    public String getTitle() {
        return Title;
    }

    @Override
    public String toString() {
        return "Category: "+ this.getCategory() + ", Title: " + this.getTitle() + ", Price: " + this.getPrice() + " TL";
    }
}
