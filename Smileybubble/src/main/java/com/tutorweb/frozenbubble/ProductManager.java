package com.tutorweb.frozenbubble;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Created by andrea on 12.8.2015.
 * This class manages the products the game has to offer: it retrieves information on the products
 * (these are stored in assets/products.txt) and converts the relevant information to a ProductListItem
 * to be used for the product shopping list in Store, or to a Bundle to be used in the fragment product
 * view in the Store.
 */
public class ProductManager {

    ArrayList<Product> availableProducts;
    ArrayList<String> prodIds;
    ArrayList<Double> prodCosts;
    ArrayList<String> prodImgUrls;
    ArrayList<String> prodDescrs;


    public ProductManager(Context context) {
        availableProducts = new ArrayList<Product>();
        prodIds = new ArrayList<String>();
        prodCosts = new ArrayList<Double>();
        prodImgUrls = new ArrayList<String>();
        prodDescrs = new ArrayList<String>();

        // When the product manager is created, immediately retrieve information on the products
        // and store said information in lists:
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(context.getAssets().open("products.txt")));
            String line;
            while((line = br.readLine()) != null) {
                String[] attrs = line.split(Pattern.quote("\t"));
                prodIds.add(attrs[0]);
                prodCosts.add(Double.parseDouble(attrs[1]));
                prodImgUrls.add(attrs[2]);
                prodDescrs.add(attrs[3]);
            }
            br.close();
        } catch(IOException e) {
            Log.e("ProductManager", "Could not find or open the product file.");
        }

        for(int i=0; i<prodIds.size(); i++) {
            availableProducts.add(new Product(prodIds.get(i), prodCosts.get(i), prodImgUrls.get(i), prodDescrs.get(i)));
        }
    }

    public ArrayList<Product> getAvailableProducts() {
        return availableProducts;
    }

    public String[] getAvailableProdIds() {
        String[] allIds = new String[prodIds.size()];
        return prodIds.toArray(allIds);
    }


    public Product findProduct(String prodId) {
        for(Product p : availableProducts) {
            if(p.getProdId().equals(prodId))
                return p;
        }
        return null;
    }

    public class Product {
        private String prodId = "";
        private double prodCost = 0.0;
        private String imgUrl;
        private String prodDescr;

        public Product(String prodId, double prodCost, String imgUrl, String descr) {
            this.prodId = prodId;
            this.prodCost = prodCost;
            this.imgUrl = imgUrl;
            this.prodDescr = descr;
        }

        public String getProdId() {
            return prodId;
        }

        public ProductListItem asListItem() {
            ProductListItem pli = new ProductListItem();
            pli.setProdID(prodId);
            pli.setItemCost(prodCost);
            pli.setItemAmount(1);
            pli.setTotalCost();

            return pli;
        }

        public Bundle asFragmentBundle() {
            Bundle prodArgs = new Bundle();
            prodArgs.putString("ID", prodId);
            prodArgs.putString("COST", Double.toString(prodCost));
            prodArgs.putString("IMG", imgUrl);
            prodArgs.putString("DESCR", prodDescr);

            return prodArgs;
        }
    }
}

