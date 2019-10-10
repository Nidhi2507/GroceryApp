package com.dioco.groceyapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dioco.groceyapp.Activity.MenuActivity;
import com.dioco.groceyapp.GrocerApplication;
import com.dioco.groceyapp.R;
import com.dioco.groceyapp.adapter.ProductPagerAdapter;
import com.dioco.groceyapp.model.product.ProductListModel;
import com.dioco.groceyapp.pojo.ResGetProductList;
import com.dioco.groceyapp.util.Utils;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


/**
 * *************************************************************************
 *
 * @ClassdName:ProductDetailsFragment
 * @CreatedDate:
 * @ModifiedBy: not yet
 * @ModifiedDate: not yet
 * @purpose:This Class use for display product details with field Product name, Price,Description & product slider images & add to cart button
 * <p/>
 * *************************************************************************
 */


public class ProductDetailsFragment extends BaseFragment {


    private ViewPager viewPagerImages;
    private CircleIndicator indicator;
    private TextView tvQuantity;
    private TextView tvName;
    private TextView tvPrice;
    private ImageView ivPlus;
    private ImageView ivMins;
    private RelativeLayout rlAddTocart;
    private ProductPagerAdapter imagePagerAdapter;
    private ResGetProductList resGetProductList;

    private List<Integer> pagerImgList;
    private Bundle bundle;
    private int totalKg = 0;
    private AppCompatActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);

        setHasOptionsMenu(true);
        initToolbar();
        initComponents(rootView);

        return rootView;
    }

    public void initToolbar() {


        ((MenuActivity) activity).setUpToolbar(""+resGetProductList.getName(), true);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = GrocerApplication.getmInstance().getActivity();
        bundle = getArguments();

        if (bundle != null) {
            resGetProductList = bundle.getParcelable(activity.getString(R.string.bdl_model));
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        MenuItem item = menu.findItem(R.id.menu_left);
        item.setVisible(true);
        item.setIcon(R.drawable.cart);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Utils.hideKeyboard(activity);
                CartListFragment cartFragment = new CartListFragment();
                cartFragment.setTargetFragment(ProductDetailsFragment.this, 222);
                Utils.addNextFragment(activity, cartFragment, ProductDetailsFragment.this, true);
                return true;
            }
        });
    }



    /**
     * init Components
     */


    @Override
    public void initComponents(View rootView) {

        viewPagerImages = rootView.findViewById(R.id.fragment_product_details_vpSlider);
        indicator = rootView.findViewById(R.id.fragment_product_details_indicator);
        rlAddTocart = rootView.findViewById(R.id.fragment_product_details_rlAddToCart);
        tvQuantity = rootView.findViewById(R.id.fragment_product_details_tvTotalKg);
        tvName = rootView.findViewById(R.id.fragment_product_details_tvTitle);
        tvPrice = rootView.findViewById(R.id.fragment_product_details_tvPrice);
        ivPlus = rootView.findViewById(R.id.fragment_product_details_ivPlus);
        ivMins = rootView.findViewById(R.id.fragment_product_details_ivMins);

        rlAddTocart.setOnClickListener(this);
        ivPlus.setOnClickListener(this);
        ivMins.setOnClickListener(this);

        setUpSliderImages();
        setUpDetails();


    }

    private void setUpDetails() {

        if (resGetProductList != null) {
            tvName.setText(resGetProductList.getName());
            tvQuantity.setText(resGetProductList.getDefault_attributes().get(0).getOption());
            tvPrice.setText(resGetProductList.getPrice());
            //totalKg = Integer.parseInt(resGetProductList.getDefault_attributes().get(0).getOption());
        }
    }

    private void setUpSliderImages() {
        try {
            pagerImgList = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                pagerImgList.add(R.drawable.graps);
                pagerImgList.add(R.drawable.apple);
            }
            imagePagerAdapter = new ProductPagerAdapter(activity, pagerImgList);

            viewPagerImages.setAdapter(imagePagerAdapter);
            indicator.setViewPager(viewPagerImages);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.fragment_product_details_ivPlus:
                addToCart(true);
                break;

            case R.id.fragment_product_details_ivMins:
                addToCart(false);
                break;

            case R.id.fragment_product_details_rlAddToCart:
                CartListFragment cartFragment = new CartListFragment();
                cartFragment.setTargetFragment(ProductDetailsFragment.this, 222);
                Utils.addNextFragment(activity, cartFragment, ProductDetailsFragment.this, true);
                break;
        }
    }

    private void addToCart(boolean addCart) {

        if (addCart) {
            totalKg = totalKg + 1;
            tvQuantity.setText(totalKg +" " +activity.getString(R.string.kg));

        } else {
            if (totalKg < 1) {
                totalKg = totalKg - 1;
                tvQuantity.setText(totalKg +" " +activity.getString(R.string.kg));
            }
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            initToolbar();

        }
    }
}
