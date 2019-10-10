package com.dioco.groceyapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dioco.groceyapp.Activity.MenuActivity;
import com.dioco.groceyapp.GrocerApplication;
import com.dioco.groceyapp.R;

/**
 * *************************************************************************
 *
 * @ClassdName:ProfileFragment
 * @CreatedDate:
 * @ModifiedBy: not yet
 * @ModifiedDate: not yet
 * @purpose:This Class use for display user profile with userName,Email,Profile image, PhoneNumber
 * <p/>
 * *************************************************************************
 */


public class ProfileFragment extends BaseFragment {

    private TextView tvName;
    private TextView tvPhone;
    private TextView tvEmail;
    private ImageView ivProfile;
    private AppCompatActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        initToolbar();
        setHasOptionsMenu(true);
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

        tvName = (TextView) rootView.findViewById(R.id.fragment_profile_tvName);
        tvEmail = (TextView) rootView.findViewById(R.id.fragment_profile_tvEmail);
        tvPhone = (TextView) rootView.findViewById(R.id.fragment_profile_tvPhone);
        ivProfile = (ImageView) rootView.findViewById(R.id.fragment_profile_ivProfilePic);

        tvName.setText(activity.getString(R.string.leo_jorge));
        tvEmail.setText(activity.getString(R.string.lio_jorge_gmail));
        tvPhone.setText(activity.getString(R.string.temp_number));

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


    /**
     * SetUp Toolbar & title
     */
    public void initToolbar() {


        ((MenuActivity) activity).setUpToolbar(activity.getString(R.string.profile), false);
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
