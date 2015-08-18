package com.tutorweb.frozenbubble;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by andrea on 7.8.2015.
 * A class to keep track of products bought and spent by the user.
 */
public class Inventory {

    public static final String SP_INVENTORY = "productInventory";
    SharedPreferences userInventory = null;

    public Inventory(Context context) {
        this.userInventory = context.getSharedPreferences(SP_INVENTORY, 0);
    }

    public void buyProduct(ProductListItem p) {
        String prodId = p.getName();
        int amount = p.getItemAmount();
        SharedPreferences.Editor spEditor = userInventory.edit();
        int currAmount = userInventory.getInt(prodId, 0);
        int newAmount = currAmount + amount;
        spEditor.putInt(prodId, newAmount);
        spEditor.commit();
    }

    public void buyProducts(ProductList prods) {
        ArrayList<ProductListItem> productList = prods.getList();
        for(ProductListItem productListItem : productList) buyProduct(productListItem);
    }

    public boolean containsProduct(String prodId) {
        return userInventory.getInt(prodId, 0)!=0;
    }


    public int getProductAmount(String prodId) {
        return userInventory.getInt(prodId, 0);
    }

    public Map<String, ?> getAllInventory() {
        return userInventory.getAll();
    }

    public void spendProduct(String prodId) {
        SharedPreferences.Editor spEditor = userInventory.edit();
        int currAmount = userInventory.getInt(prodId, 0);
        if(currAmount > 0) spEditor.putInt(prodId, currAmount - 1);
        spEditor.commit();
    }
}
