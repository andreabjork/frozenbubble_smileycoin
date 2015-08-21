package com.tutorweb.frozenbubble;

/**
 * Created by andrea on 6.8.2015.
 * A class to define a single item of the product shopping list.
 */
public class ProductListItem {

    private String itemName = "";
    private int nOfItems = 0;
    private double itemCost = 0.0;
    private double totalCost = 0.0;

    public void setProdID(String itemName) {
        this.itemName = itemName;
    }

    public String getName() {
        return itemName;
    }

    public void setItemAmount(int nOfItems) {
        this.nOfItems = nOfItems;
        this.totalCost = this.itemCost*this.nOfItems;
    }

    public int getItemAmount() {
        return nOfItems;
    }

    public void setItemCost(double itemCost) {
        this.itemCost = itemCost;
        this.totalCost = this.itemCost*this.nOfItems;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void setTotalCost() {
        this.totalCost = itemCost*nOfItems;
    }

    public double getTotalCost() {
        return totalCost;
    }

}