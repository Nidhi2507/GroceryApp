package com.dioco.groceyapp.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dioco.groceyapp.Activity.MenuActivity;
import com.dioco.groceyapp.GrocerApplication;
import com.dioco.groceyapp.R;
import com.dioco.groceyapp.adapter.ProductListAdapter;
import com.dioco.groceyapp.data.TempListData;
import com.dioco.groceyapp.helpers.ApiCallHelper;
import com.dioco.groceyapp.helpers.PopUpHelper;
import com.dioco.groceyapp.interfaces.GetProductListInterface;
import com.dioco.groceyapp.model.product.ProductListModel;
import com.dioco.groceyapp.pojo.ResGetProductList;
import com.dioco.groceyapp.util.Utils;

import java.util.ArrayList;
import java.util.List;


/**
 * *************************************************************************
 *
 * @ClassdName:ProductListFragment
 * @CreatedDate:
 * @ModifiedBy: not yet
 * @ModifiedDate: not yet
 * @purpose:This Class use for display product list with field Product name, Price, product images & add and remove item on Cart
 * <p/>
 * *************************************************************************
 */


public class ProductListFragment extends BaseFragment implements ProductListAdapter.OnItemClickListener, GetProductListInterface {

    //Declaration
    private RecyclerView rvProductList;
    private GridLayoutManager mLayoutManager;
    private ProductListAdapter productListAdapter;
    private MenuItem item;
    private AppCompatActivity activity;
    private Context context;
    private ApiCallHelper apiCallHelper = new ApiCallHelper();
    private List<ResGetProductList> resGetProductLists;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = GrocerApplication.getmInstance().getActivity();
        context = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_product_list, container, false);

        initToolbar();
        setHasOptionsMenu(true);
        executeProductListData();
        initComponents(rootView);

        return rootView;
    }

    /**
     * init Components
     */

    @Override
    public void initComponents(View rootView) {
        rvProductList = (RecyclerView) rootView.findViewById(R.id.fragment_productlist_rvProductList);
        mLayoutManager = new GridLayoutManager(activity, 2);
        rvProductList.setLayoutManager(mLayoutManager);
    }
    /**
     * SetUp Toolbar & title
     */

    //Get Home data by calling Api
    private void executeProductListData() {
        try {
            //Log.d("internate-check:", String.valueOf(PopUpHelper.checkInternetConnectionPopup(context)));
            if (PopUpHelper.checkInternetConnection(context)) {
                apiCallHelper.getProductDelegate = this;
                apiCallHelper.getProductList(context);

            } else {
                new AlertDialog.Builder(context)
                        .setTitle("Warning")
                        .setMessage("Please Turn On Data..!")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                executeProductListData();
                            }
                        })
                        .show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initToolbar() {

        ((MenuActivity) activity).setUpToolbar(activity.getString(R.string.nav_menu_home), false);
    }

    /**
     * get Product list data and setUp adapter
     */
    private void getProductListData()
    {
        productListAdapter = new ProductListAdapter(activity, resGetProductLists);
        rvProductList.setAdapter(productListAdapter);
        productListAdapter.setOnItemClickListener(this);
    }

    /**
     *  Menu item click listener & open fragment
     */

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        item = menu.findItem(R.id.menu_left);
        item.setVisible(true);
        item.setIcon(R.drawable.cart);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Utils.hideKeyboard(activity);
                CartListFragment fragmentProductDetails = new CartListFragment();
                fragmentProductDetails.setTargetFragment(ProductListFragment.this, 222);
                Utils.addNextFragment(activity, fragmentProductDetails, ProductListFragment.this, false);

                return true;
            }
        });
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            try {
                initToolbar();
            } catch (Exception e) {

            }
        }
    }

    /**
     *  item click listener & open fragment
     */

    @Override
    public void onItemClick(View view, ResGetProductList resGetProductList) {

        ProductDetailsFragment fragmentProductDetails = new ProductDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(activity.getString(R.string.bdl_model), (Parcelable) resGetProductList);
        fragmentProductDetails.setArguments(bundle);
        Utils.addNextFragment(activity, fragmentProductDetails, ProductListFragment.this, false);
       /* int id = view.getId();
        //Log.d("view_Id:", String.valueOf(id));
        Log.d("view_Id:", String.valueOf(R.id.row_productlist_ivPlus));
        switch (id) {
            case R.id.row_productlist_ivPlus:
                Toast.makeText(activity, "hiii", Toast.LENGTH_SHORT).show();
                addToCart(true,rvProductList.getChildAdapterPosition(view));
                break;
           *//* default:
                ProductDetailsFragment fragmentProductDetails = new ProductDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(activity.getString(R.string.bdl_model), (Parcelable) resGetProductList);
                fragmentProductDetails.setArguments(bundle);
                Utils.addNextFragment(activity, fragmentProductDetails, ProductListFragment.this, false);
                break;*//*
        }*/

    }

    /**
     *  Add to cart & refresh adapter
     */

    public void addToCart(boolean addTocart, int position, List<ResGetProductList> resGetProductLists) {

        if (addTocart) {

            int totalKg = Integer.parseInt(resGetProductLists.get(position).getDefault_attributes().get(position).getOption());
            totalKg = totalKg + 1;
            resGetProductLists.get(position).getDefault_attributes().get(position).setOption(String.valueOf(totalKg));

        } else {

            int totalKg = Integer.parseInt(resGetProductLists.get(position).getDefault_attributes().get(position).getOption());

            if (totalKg < 1) {
                resGetProductLists.get(position).getDefault_attributes().get(position).setOption("0");
            } else {
                totalKg = totalKg - 1;
                resGetProductLists.get(position).getDefault_attributes().get(position).setOption(String.valueOf(totalKg));
            }
        }
        productListAdapter.notifyDataSetChanged();


    }

    @Override
    public void processFinishGetProductList(List<ResGetProductList> resGetProductList) {
        Log.d("Product_List:",resGetProductList.toString());
        this.resGetProductLists = resGetProductList;
        getProductListData();
    }
}
