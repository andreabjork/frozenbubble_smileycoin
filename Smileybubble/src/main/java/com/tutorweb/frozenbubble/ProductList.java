package com.tutorweb.frozenbubble;

import java.util.ArrayList;

/**
 * Created by andrea on 6.8.2015.
 * A class used that defines the product shopping list in the StoreActivity.
 * Keeps track of products added to or removed from the shopping list.
 */
public class ProductList {

    ArrayList<ProductListItem> allProducts;

    public ProductList() {
        allProducts = new ArrayList<ProductListItem>();
    }

    public void addProduct(ProductListItem p) {
        allProducts.add(p);
    }


    public void removeProduct(String prodId) {
        if(hasProduct(prodId)) removeProduct(findProduct(prodId));
    }

    public int getSize() {
        return allProducts.size();
    }

    public boolean hasProduct(String prodId) {
        return (findProductIndex(prodId) >= 0);
    }

    public ProductListItem getProduct(int i) {
        return allProducts.get(i);
    }

    public void setProductAmount(String prodId, int prodAmount) {
        if (hasProduct(prodId)) {
            ProductListItem productListItem = findProduct(prodId);
            productListItem.setItemAmount(prodAmount);
            productListItem.setTotalCost();
        }
    }

    public int calcTotalAmount() {
        int total = 0;
        for(ProductListItem p : allProducts) total+=p.getTotalCost();
        return total;
    }

    public ArrayList<ProductListItem> getList() {
        return allProducts;
    }

    private void removeProduct(ProductListItem p) {
        allProducts.remove(p);
    }

    private int findProductIndex(String prodId) {
        for(ProductListItem p : allProducts) {
            if(p.getName().equals(prodId))
                return allProducts.indexOf(p);
        }
        return -1;
    }

    private ProductListItem findProduct(String prodId) {
        if(hasProduct(prodId))
            return allProducts.get(findProductIndex(prodId));
        else return null;
    }
}
