package com.dioco.groceyapp.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;

import com.dioco.groceyapp.fragment.ProductListFragment;
import com.dioco.groceyapp.fragment.VegetablesListFragment;
import com.dioco.groceyapp.v4.MyStatePagerAdapter;


/**
 * *************************************************************************
 *
 * @ClassdName:ImagePagerAdapter
 * @CreatedDate:
 * @ModifiedBy: not yet
 * @ModifiedDate: not yet
 * @purpose:This Class use for category with different fragmnet on viewPager adapter
 * <p/>
 * *************************************************************************
 */

public class SubCategoryPagerAdapter extends MyStatePagerAdapter {

    private Context mContext;
    private String[] categoryTitles;

    public SubCategoryPagerAdapter(Activity context, FragmentManager fm, String[] categoryTitles) {
        super(fm);
        mContext = context;
        this.categoryTitles = categoryTitles;
    }


    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ProductListFragment();
        } else if (position == 1) {
            return new VegetablesListFragment();
        } /*else if (position == 2) {
            return new ProductListFragment();
        } else if (position == 3) {
            return new ProductListFragment();
        } else if (position == 4) {
            return new ProductListFragment();
        }*/
        else {
            return new ProductListFragment();
        }

    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 2;
    }


    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        return  categoryTitles[position];
    }


}