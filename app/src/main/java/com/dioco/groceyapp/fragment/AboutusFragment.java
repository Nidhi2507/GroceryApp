package com.dioco.groceyapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dioco.groceyapp.Activity.MenuActivity;
import com.dioco.groceyapp.GrocerApplication;
import com.dioco.groceyapp.R;


/**
 * *************************************************************************
 *
 * @ClassdName:AboutusFragment
 * @CreatedDate:
 * @ModifiedBy: not yet
 * @ModifiedDate: not yet
 * @purpose:This Class use for display about-us content
 * <p/>
 * *************************************************************************
 */


public class AboutusFragment extends BaseFragment
{

    private TextView tvAboutUs;
    private AppCompatActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_aboutus, container, false);

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

        tvAboutUs=(TextView) rootView.findViewById(R.id.fragment_aboutus_tvData);
        tvAboutUs.setText(activity.getString(R.string.description_data));

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    /**
     * SetUp Toolbar & title
     */


    public void initToolbar() {



        if (activity != null) {
            ((MenuActivity) activity).setUpToolbar(activity.getString(R.string.nav_menu_about_us), false);

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


}
