package com.tutorweb.frozenbubble;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jfedor.frozenbubble.R;

/**
 * Created by andrea on 4.8.2015.
 * Fragment for a view of a particular product (image, name, cost) in the Store.
 */
public class StoreFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.product_view_layout, container, false);

        ((TextView)rootView.findViewById(R.id.product_name)).setText(getArguments().getString("ID"));
        ((TextView)rootView.findViewById(R.id.product_info)).setText(getArguments().getString("DESCR"));
        ((TextView)rootView.findViewById(R.id.item_cost)).setText(getArguments().getString("COST") + " SMLY");
        ((ImageView)rootView.findViewById(R.id.product_image)).setImageResource(Integer.valueOf(getImageId(getActivity())));


        final TextView numberField = (TextView)rootView.findViewById(R.id.editText);
        numberField.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String newValue = numberField.getText().toString();
                int newAmount = 0;
                if (!newValue.equals("")) newAmount = Integer.parseInt(newValue);
                getArguments().putInt("AMOUNT", newAmount);
                ((StoreActivity) getActivity()).updateProductList();

            }
        });

        return rootView;
    }

    public static StoreFragment newInstance(ProductManager.Product p) {
        StoreFragment prod = new StoreFragment();
        Bundle prodArgs = p.asFragmentBundle();
        prodArgs.putInt("AMOUNT", 0);
        prod.setArguments(prodArgs);
        return prod;
    }

    private int getImageId(Context context) {
        String imgUrl = getArguments().getString("IMG");
        return context.getResources().getIdentifier("drawable/" + imgUrl, null, context.getPackageName());
    }

    public String getName() {
        return getArguments().getString("ID");
    }

    public int getAmount() {
        return getArguments().getInt("AMOUNT");
    }

    public void resetAmount() {
        if(getView() != null) ((TextView)getView().findViewById(R.id.editText)).setText("0");
    }

    public void removeHint() {
        if(getView() != null) ((TextView)getView().findViewById(R.id.hintText)).setVisibility(View.GONE);
    }
}