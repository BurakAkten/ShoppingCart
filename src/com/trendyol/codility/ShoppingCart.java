package com.trendyol.codility;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {

    private final double FıxedCost = 2.99;
    private final double CostPerDelivery = 1.99;
    private final double CostPerProduct = 0.99;


    private ArrayList<Product> Cart;
    private ArrayList<Integer> CartQuantities;
    private boolean IsCouponsApplied;
    private double TotalCost;
    private double CouponDiscount;
    private double CampaignDiscount;


    /**
     * No parameter Constructor, makes all necessary initializations
     */
    public ShoppingCart(){
        this.Cart = new ArrayList<>();
        this.CartQuantities = new ArrayList<>();
        IsCouponsApplied = false;

        TotalCost = 0.0;
        CouponDiscount = 0.0;
        CampaignDiscount = 0.0;
    }

    /**
     * Add a new item into the cart
     * @param newProduct To be added into cart
     * @param Quantity The quantity of the new item
     */
    public void addItem(Product newProduct, int Quantity){

        if(this.Cart.equals(null)){
            this.Cart = new ArrayList<>();
        }
        if(this.Cart.equals(null)){
            this.CartQuantities = new ArrayList<>();
        }

        if(newProduct != null){
            this.Cart.add(newProduct);
            this.CartQuantities.add(Quantity);
            TotalCost += newProduct.getPrice() * Quantity ;
        }
        else{
            //System.err.println("ERROR: " + newProduct.toString());
            System.out.println("\nERROR: A null item can not be added.\n");
        }

    }

    /**
     * Apply the campaign to the product price
     * @param campaign The campaign to be applied
     */
    public void applyDiscounts(Campaign campaign){
        if (!IsCouponsApplied && IsCampaignApplicable(campaign) && TotalCost > 0.0){
            applyDiscount(true, campaign.getDiscountType(), campaign.getDiscount(), getCostForCategory(campaign.getCategory()));
        }
        else{
            if (campaign != null)
                System.out.println("\nWarning: " + campaign.toString());
            System.out.println("\nWarning: The campaign can not be applied.\n");
        }
    }

    /**
     * Apply two campaigns
     * @param campaign1 First one
     * @param campaign2 Second one
     */
    public void applyDiscounts(Campaign campaign1, Campaign campaign2){
        applyDiscounts(campaign1);
        applyDiscounts(campaign2);
    }

    /**
     * Apply three campaigns
     * @param campaign1 First one
     * @param campaign2 Second one
     * @param campaign3 Third one
     */
    public void applyDiscounts(Campaign campaign1, Campaign campaign2, Campaign campaign3){
        applyDiscounts(campaign1, campaign2);
        applyDiscounts(campaign3);
    }

    /**
     * Apply the Cuopon to the cart.
     * @param coupon The Coupon to be applied
     */
    public void applyCoupon(Coupon coupon){

        if(IsCouponApplicable(coupon) && TotalCost > 0.0){
            applyDiscount(false, coupon.getDiscountType(), coupon.getDiscount(), null);
            IsCouponsApplied = true;

        }
        else{
            if (coupon != null)
                System.out.println("\nWarning: " + coupon.toString());
            System.out.println("\nWarning: The coupon could not be applied.\n");
        }
    }

    /**
     * prints the necessary information.
     */
    public void print(){
        System.out.println("Products :\n");
        for (Object category: getCategories()) {
            for (int i = 0; i < Cart.size(); i++){
                if(category.toString().equals(Cart.get(i).getCategory().getTitle())){
                    System.out.print("Category: " + Cart.get(i).getCategory().getTitle() + ", ");
                    System.out.print("Name: " + Cart.get(i).getTitle() + ", ");
                    System.out.print("Quantity: " + CartQuantities.get(i) + ", ");
                    System.out.print("Unit Price: " + Cart.get(i).getPrice()  + " TL, ");
                    System.out.println("Total Price: " + Cart.get(i).getPrice() * CartQuantities.get(i) + " TL, \n");
                }

            }
        }
        System.out.println("Total Amount: " + TotalCost + " TL, Total Discount: " + (getCampaignDiscount() + getCouponDiscount()) + " TL");
        System.out.println("Total Amount After Discount: " + getTotalAmountAfterDiscounts() + " TL, Delivery Cost: " + getDeliveryCost() + " TL");

    }

    /**
     * Getter for the cart
     * @return The cart.
     */
    public ArrayList<Product> getCart() {
        return Cart;
    }

    /**
     *
     * @return The total amount after all discounts
     */
    public double getTotalAmountAfterDiscounts(){
        return TotalCost - (CouponDiscount + CampaignDiscount);
    }

    /**
     *
     * @return The total campaign discounts
     */
    public double getCampaignDiscount() {
        return CampaignDiscount;
    }

    /**
     *
     * @return The total coupon discounts.
     */
    public double getCouponDiscount() {
        return CouponDiscount;
    }

    /**
     *
     * @return The delivery cost for the cart by creating DeliveryCostCalculator instance.
     */
    public double getDeliveryCost() {
        DeliveryCostCalculator deliveryCostCalculator =
                new DeliveryCostCalculator(CostPerDelivery, CostPerProduct, FıxedCost);
        return deliveryCostCalculator.calculateFor(this);
    }

    /**
     *
     * @return The unique categories of the cart.
     */
    private Object[] getCategories(){
        Set<String> categories = new HashSet<>();

        for (Product product:Cart) {
            categories.add(product.getCategory().getTitle());
        }

        return categories.toArray();
    }

    /**
     * Find the item size for the given category
     * @param category The category to control the items.
     * @return The size of items for the given category
     */
    private int getItemsSizeForCategory(Category category){

        int size = 0;

        for (int i = 0 ; i < this.Cart.size(); i++) {
            Product product = this.Cart.get(i);
            size += product.getCategory().getTitle().equals(category.getTitle()) ? CartQuantities.get(i) : 0;
        }
        return size;
    }

    /**
     *  Find the cost for the given category
     * @param category The category to control the cost.
     * @return The cost of items for the given category
     */
    private double getCostForCategory(Category category){

        double cost = 0.0;

        for (Product product:this.Cart) {
            cost += product.getCategory().getTitle().equals(category.getTitle()) ? product.getPrice() : 0.0;
        }
        return cost;
    }

    /**
     * Controls the given category is applicable.
     * @param campaign The category for control.
     * @return True if the category is applicable.
     */
    private boolean IsCampaignApplicable(Campaign campaign){
       return campaign != null && getItemsSizeForCategory(campaign.getCategory())>= campaign.getItemNubers();
    }

    /**
     * Controls the given coupon is applicable.
     * @param coupon The coupon for control.
     * @return True if the coupon is applicable.
     */
    private boolean IsCouponApplicable(Coupon coupon){
        return coupon != null && TotalCost >= coupon.getMinPurchaseAmount();
    }

    /**
     * Apply the discounts (Campaign and Coupon)
     * @param IsCampaign Tells that the discount is campaign or not
     * @param discountType Tells that the discount type (Rate or Amount)
     * @param Discount Discount  amount
     * @param campaignCost Campaign cost for apply the campaign discount. Null when the discount is Coupon
     */
    private void applyDiscount(boolean IsCampaign, int discountType, double Discount, Double campaignCost){
        double discount = Discount;
        if (discountType == DiscountType.Rate){

            if (!IsCampaign){
                discount = TotalCost * (Discount / 100 );
                CouponDiscount += discount;
            }
            else{
                discount = campaignCost * (Discount / 100 ); //  discount = campaignCost * (Discount / 100 );
                CampaignDiscount += discount;
            }
        }
        else if (discountType == DiscountType.Amount){
            if (!IsCampaign){
                CouponDiscount += discount;
            }
            else{
                CampaignDiscount += discount;
            }
        }

    }
}
