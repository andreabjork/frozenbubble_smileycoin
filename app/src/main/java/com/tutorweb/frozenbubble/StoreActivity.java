package com.tutorweb.frozenbubble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.jfedor.frozenbubble.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 * Created by andrea on 5.8.2015.
 * StoreActivity is the product shop.
 * It includes
 *      - a fragment view of all available products that the user can cycle through and select
 *      products he wishes to buy.
 *      - A list of all products the user has selected that calculates the total cost of all products.
 *      - An option to purchase the items, thereby taking the user to the smileycoin app to pay for them.
 */
public class StoreActivity extends FragmentActivity {

    private ViewPager mPager;
    private ProductPagerAdapter mPagerAdapter;

    private ProductManager PM;
    private ProductList productList;
    private ProductListAdapter listAdapter;
    private Inventory inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        // 1. Instantiate the StoreFragment views for the products using ProductManager, a ViewPager and
        // pager adapter.
        PM = new ProductManager(this);
        ArrayList<ProductManager.Product> availableProducts = PM.getAvailableProducts();

        final List<StoreFragment> fragments = new Vector<StoreFragment>();
        for(ProductManager.Product prod : availableProducts) {
            fragments.add(StoreFragment.newInstance(prod));
        }

        mPager = (ViewPager) findViewById(R.id.product_pager);
        mPager.setOffscreenPageLimit(availableProducts.size() - 1);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_DRAGGING) for(StoreFragment fragment : fragments) fragment.removeHint();
            }
        });
        findViewById(R.id.product_pager).setBackgroundResource(R.drawable.void_panel);
        mPagerAdapter = new ProductPagerAdapter(getSupportFragmentManager(), fragments);
        mPager.setAdapter(mPagerAdapter);

        // 2. Instantiate the ProductList view using ListView and a ListAdapter.
        productList = new ProductList();
        inventory = new Inventory(this);

        final ListView list = (ListView) findViewById(R.id.productList);
        listAdapter = new ProductListAdapter(this, productList);
        list.setAdapter(listAdapter);

        updateButton();
    }

    private void updateButton() {
        Button purchaseButton = (Button)findViewById(R.id.purchase_button);
        if(productList.calcTotalAmount()<=0) {
            purchaseButton.setEnabled(false);
        }else purchaseButton.setEnabled(true);
    }

    // Takes care of updating the counter, the product list and the total amount.
    public void updateProductCounter(View view) {
        View v = (View)view.getParent();
        if(view.getId() == R.id.incButton) incProductCounter(v);
        else if (view.getId() == R.id.decButton) decProductCounter(v);

        updateProductList();
    }

    // 'Help' function that only updates the product list
    public void updateProductList() {
        int n = mPagerAdapter.getCount();
        for(int i=0; i<n; i++) {
            String prodId = mPagerAdapter.getItem(i).getName();
            int amount = mPagerAdapter.getItem(i).getAmount();
            if(!productList.hasProduct(prodId) && amount > 0) addProduct(prodId);
            else if(productList.hasProduct(prodId) && amount <= 0)removeProduct(prodId);

            updateProductAmount(prodId, Integer.toString(amount));
        }

        // Update the total amount:
        updateButton();
        int total = productList.calcTotalAmount();
        ((TextView) findViewById(R.id.totalAmount)).setText(Integer.toString(total) + " SMLY");
    }

    // 'Help' function that only updates the counter
    private void incProductCounter(View counterView)  {
        TextView textField = ((TextView)counterView.findViewById(R.id.editText));
        int currentAm = Integer.parseInt((textField.getText()).toString());
        int newAm = currentAm+1;
        String incByOne = Integer.toString(newAm);
        textField.setText(incByOne);

    }

    // 'Help' function that only updates the counter
    private void decProductCounter(View counterView)  {
        TextView textField = ((TextView)counterView.findViewById(R.id.editText));
        int currentAm = Integer.parseInt((textField.getText()).toString());
        int newAm = currentAm;
        if (currentAm>0) newAm--;
        String decByOne = Integer.toString(newAm);
        textField.setText(decByOne);
    }


    private int getTotalAmount() {
        return productList.calcTotalAmount();
    }

    // Adds product to the list
    private void addProduct(String prodId) {
        ProductManager.Product p = PM.findProduct(prodId);
        productList.addProduct(p.asListItem());
        listAdapter.notifyDataSetChanged();
    }

    // Removes a product from the list
    private void removeProduct(String prodId) {
        productList.removeProduct(prodId);
        listAdapter.notifyDataSetChanged();
        //listAdapter.remove(prodId);
    }

    // Updates the product amount in the list.
    private void updateProductAmount(String prodId, String amount) {
        productList.setProductAmount(prodId, Integer.parseInt(amount));
        listAdapter.notifyDataSetChanged();
    }

    // Starts a purchase through the smileycoin wallet app
    public void purchase(View view) {
        Intent purchaseIntent = new Intent("de.schildbach.wallet.PREPARE_PAYMENT");
        purchaseIntent.putExtra("Address", "BDQnzfNPbu8juiNurY6KTrPtrvUWXNvQjD");
        purchaseIntent.putExtra("AddressLabel", "Tutor Web Frozen Bubble");
        purchaseIntent.putExtra("Amount", getTotalAmount());

        startActivityForResult(purchaseIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                inventory.buyProducts(productList);
                mPagerAdapter.resetAmounts();
                updateProductList();
                this.finish();
            }
            if (resultCode == RESULT_CANCELED) {
                Log.i("Purchase", "Failed to purchase your items.");
            }
        }
    }

    /**
     * A simple pager adapter that represents the products in the store.
     */
    private class ProductPagerAdapter extends FragmentStatePagerAdapter {
        private List<StoreFragment> fragments;

        public ProductPagerAdapter(FragmentManager fm, List<StoreFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public StoreFragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }

        public void resetAmounts() {
            for(StoreFragment sf : fragments) {
                sf.resetAmount();
            }
        }
    }

}

