package com.dioco.groceyapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dioco.groceyapp.Activity.MenuActivity;
import com.dioco.groceyapp.GrocerApplication;
import com.dioco.groceyapp.R;
import com.dioco.groceyapp.adapter.CartListAdapter;
import com.dioco.groceyapp.data.TempListData;
import com.dioco.groceyapp.model.cart.CartlistModel;
import com.dioco.groceyapp.util.Utils;

import java.util.List;


/**
 * *************************************************************************
 *
 * @ClassdName:CartListFragment
 * @CreatedDate:
 * @ModifiedBy: not yet
 * @ModifiedDate: not yet
 * @purpose:This Class use for display cart list items & add and remove item in cartList
 * <p/>
 * *************************************************************************
 */

public class CartListFragment extends BaseFragment {

    //Declaration
    private RecyclerView rvProductList;
    private TextView tvTotalPrice;
    private RelativeLayout rlCheckOut;
    private RelativeLayout rlEmpty;
    private LinearLayoutManager mLayoutManager;
    private CartListAdapter productListAdapter;
    private List<CartlistModel> productListModelArrayList;
    private MenuItem item;
    private AppCompatActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_cart_list, container, false);
        initToolbar();
        initComponents(rootView);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = GrocerApplication.getmInstance().getActivity();
    }


    /**
     * init Components
     */

    @Override
    public void initComponents(View rootView) {


        rvProductList = (RecyclerView) rootView.findViewById(R.id.fragment_order_list_rvProductList);
        rlCheckOut = (RelativeLayout) rootView.findViewById(R.id.fragment_order_list_rlCheckOut);
        rlEmpty = (RelativeLayout) rootView.findViewById(R.id.fragment_order_list_rlEmpty);
        tvTotalPrice = (TextView) rootView.findViewById(R.id.fragment_order_list_tvTotalKg);
        mLayoutManager = new LinearLayoutManager(activity);

        rvProductList.setLayoutManager(mLayoutManager);

        rlCheckOut.setOnClickListener(this);

        getListData();
        tvTotalPrice.setText(activity.getString(R.string.dolar) + getTotalPrice());
    }

    private int getTotalPrice() {
        int total = 0;

        for (int i = 0; i < productListModelArrayList.size(); i++) {
            int totalQty = productListModelArrayList.get(i).getProductQuantity();
            int price = productListModelArrayList.get(i).getProductPrice();
            int totalPrice = totalQty * price;
            total = total + totalPrice;
        }

        return total;
    }

    /**
     * get Cart list data and setUp adapter
     */

    private void getListData() {

        TempListData tempListData = new TempListData();
        productListModelArrayList = tempListData.getCartList();
        productListAdapter = new CartListAdapter(activity, productListModelArrayList, CartListFragment.this);
        rvProductList.setAdapter(productListAdapter);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        item = menu.findItem(R.id.menu_left);
        item.setVisible(false);
    }


    @Override
    public void onClick(View v) {

        if (v == rlCheckOut) {
            SelectAddressFragment cartFragment = new SelectAddressFragment();
            Utils.addNextFragment(activity, cartFragment, CartListFragment.this, true);
        }
    }



    /**
     * SetUp Toolbar & title
     */


    public void initToolbar() {



        if (getTargetFragment() instanceof ProductDetailsFragment) {
            ((MenuActivity) activity).setUpToolbar(activity.getString(R.string.nav_menu_my_cart), true);
        } else if (getTargetFragment() instanceof ProductListFragment) {
            ((MenuActivity) activity).setUpToolbar(activity.getString(R.string.nav_menu_my_cart), true);
        } else if (getTargetFragment() instanceof OrderListFragment) {
            ((MenuActivity) activity).setUpToolbar(activity.getString(R.string.nav_menu_my_cart), true);
        } else {
            ((MenuActivity) activity).setUpToolbar(activity.getString(R.string.nav_menu_my_cart), false);
        }

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            try {
                initToolbar();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }


    /**
     *  Add to cart & refresh adapter
     */


    public void addToCart(boolean addTocart, int position) {
        if (addTocart) {
            int totalKg = productListModelArrayList.get(position).getProductQuantity();
            totalKg = totalKg + 1;
            productListModelArrayList.get(position).setProductQuantity(totalKg);

        } else {
            int totalKg = productListModelArrayList.get(position).getProductQuantity();

            if (totalKg > 1) {
                totalKg = totalKg - 1;
                productListModelArrayList.get(position).setProductQuantity(totalKg);
            }
        }

        productListAdapter.notifyDataSetChanged();
        tvTotalPrice.setText(activity.getString(R.string.dolar) + getTotalPrice());

    }

    /**
     *  Delete item from cart and refresh adapter
     */

    public void deleteItem(int position) {
        productListModelArrayList.remove(position);
        productListAdapter.notifyDataSetChanged();
        tvTotalPrice.setText(activity.getString(R.string.dolar) + getTotalPrice());

        if (productListModelArrayList.size() == 0) {
            rlEmpty.setVisibility(View.VISIBLE);
            rvProductList.setVisibility(View.GONE);
        }
    }
}
