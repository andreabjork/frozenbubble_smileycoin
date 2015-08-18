package com.tutorweb.frozenbubble;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.jfedor.frozenbubble.R;

/**
 * Created by andrea on 6.8.2015.
 * BaseAdapter used for the product shopping list in StoreActivity.
 *
 */
public class ProductListAdapter extends BaseAdapter {
    private static ProductList productList;

    private LayoutInflater mInflater;

    public ProductListAdapter(Context context, ProductList productList) {
        this.productList = productList;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        return productList.getSize();
    }

    public Object getItem(int position) {
        return productList.getProduct(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitem, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.itemName);
            holder.txtNOfItems = (TextView) convertView.findViewById(R.id.nOfItems);
            holder.txtItemCost = (TextView) convertView.findViewById(R.id.itemCost);
            holder.txtTotalCost = (TextView) convertView.findViewById(R.id.totalCost);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ProductListItem currItem = (ProductListItem)this.getItem(position);

        holder.txtName.setText(currItem.getName());
        holder.txtNOfItems.setText("x"+Integer.toString(currItem.getItemAmount()));
        holder.txtItemCost.setText(Double.toString(currItem.getItemCost())+" SMLY");
        holder.txtTotalCost.setText(Double.toString(currItem.getTotalCost())+" SMLY");

        return convertView;
    }

    static class ViewHolder {
        TextView txtName;
        TextView txtNOfItems;
        TextView txtItemCost;
        TextView txtTotalCost;
    }
}