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
import com.dioco.groceyapp.interfaces.GetProductWeightInterface;
import com.dioco.groceyapp.model.product.ProductListModel;
import com.dioco.groceyapp.pojo.ResGetProductList;
import com.dioco.groceyapp.pojo.ResGetProductWeight;
import com.dioco.groceyapp.util.Utils;

import java.io.Serializable;
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


public class ProductListFragment extends BaseFragment implements GetProductListInterface, ProductListAdapter.OnItemClickListener, GetProductWeightInterface {

    //Declaration
    private RecyclerView rvProductList;
    private GridLayoutManager mLayoutManager;
    private ProductListAdapter productListAdapter;
    private MenuItem item;
    private AppCompatActivity activity;
    private Context context;
    private ApiCallHelper apiCallHelper = new ApiCallHelper();
    private List<ResGetProductList> resGetProductLists;
    private List<ResGetProductWeight> resGetProductWeightList;
    private ArrayList<String> KgWeights;
    private String productId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = GrocerApplication.getmInstance().getActivity();
        context = getActivity();
        productListAdapter = new ProductListAdapter(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_product_list, container, false);

        initToolbar();
        setHasOptionsMenu(true);
        executeProductListDataAPI();
        executeProductWeightDataAPI();
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

        KgWeights = new ArrayList<>();
        KgWeights.add("250g");
        KgWeights.add("500g");
        KgWeights.add("1kg");
    }


    /*
    * Get Home data by calling Api
    * */
    private void executeProductListDataAPI() {
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
                                executeProductListDataAPI();
                            }
                        })
                        .show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * Get Product Weight Response from Api
     * */
    private void executeProductWeightDataAPI() {
        try {
            //Log.d("internate-check:", String.valueOf(PopUpHelper.checkInternetConnectionPopup(context)));
            if (PopUpHelper.checkInternetConnection(context)) {
                apiCallHelper.getProductWeightDelegate = this;
                apiCallHelper.getProductWeightVariation(context,productId);

            } else {
                new AlertDialog.Builder(context)
                        .setTitle("Warning")
                        .setMessage("Please Turn On Data..!")
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                executeProductWeightDataAPI();
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
    private void getProductListData() {
        productListAdapter = new ProductListAdapter(activity, resGetProductLists);
        rvProductList.setAdapter(productListAdapter);
        productListAdapter.setOnItemClickListener(this);
    }

    /**
     * Menu item click listener & open fragment
     */

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        item = menu.findItem(R.id.menu_left);
        item.setVisible(true);
        item.setIcon(R.drawable.cart);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
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
     * Add to cart & refresh adapter
     */

    public void addToCart(boolean addTocart, int position, List<ResGetProductList> resGetProductLists) {

        if (addTocart) {
            int totalKg = Integer.parseInt(resGetProductLists.get(position).getDefault_attributes().get(0).getOption()
                    .replaceAll("[kg]", ""));
            totalKg = totalKg + 1;
            resGetProductLists.get(position).getDefault_attributes().get(0).setOption(String.valueOf(totalKg));

        } else {

            int totalKg = Integer.parseInt(resGetProductLists.get(position).getDefault_attributes().get(0).getOption()
                    .replaceAll("[kg]", ""));

            if (totalKg < 1) {
                resGetProductLists.get(position).getDefault_attributes().get(0).setOption("0");
            } else {
                totalKg = totalKg - 1;
                resGetProductLists.get(position).getDefault_attributes().get(0).setOption(String.valueOf(totalKg));
            }
        }
        productListAdapter.notifyDataSetChanged();
    }

    /**
     *  item click listener & open fragment
     */
    @Override
    public void onItemClick(View view, int position) {
        switch (view.getId()) {
            case R.id.row_cartlist_ivPlus:
                addToCart(true, position, resGetProductLists);
                break;

            case R.id.row_cartlist_ivMins:
                addToCart(false, position, resGetProductLists);
                break;

            case R.id.row_produclist_rlTotalCart:
                addToCart(true, position, resGetProductLists);
                break;
            /*default:
                ProductDetailsFragment fragmentProductDetails = new ProductDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable(activity.getString(R.string.bdl_model), (Parcelable) resGetProductLists);
                fragmentProductDetails.setArguments(bundle);
                Utils.addNextFragment(activity, fragmentProductDetails, ProductListFragment.this, false);
                break;*/
        }
    }

    @Override
    public void processFinishGetProductList(List<ResGetProductList> resGetProductList) {
        Log.d("Product_List:", resGetProductList.toString());
        this.resGetProductLists = resGetProductList;
        getProductListData();
        productId = String.valueOf(resGetProductList.get(0).getId());
        Log.d("responseProductId:",productId);

    }

    @Override
    public void processFinishGetProductWeight(List<ResGetProductWeight> resGetProductWeightList) {
        this.resGetProductWeightList = resGetProductWeightList;
        Log.d("responseProduct_wgt:",resGetProductWeightList.toString());
    }
}
