package com.dioco.groceyapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dioco.groceyapp.Activity.MenuActivity;
import com.dioco.groceyapp.GrocerApplication;
import com.dioco.groceyapp.R;
import com.dioco.groceyapp.adapter.AddressListAdapter;
import com.dioco.groceyapp.data.TempListData;
import com.dioco.groceyapp.model.addressBook.AddressBookModel;
import com.dioco.groceyapp.util.Utils;

import java.util.List;

/**
 * *************************************************************************
 *
 * @ClassdName:AddressListFragment
 * @CreatedDate:
 * @ModifiedBy: not yet
 * @ModifiedDate: not yet
 * @purpose:This Class use for display list of shipping address with field fullName,phone,email & address
 * <p/>
 * *************************************************************************
 */



public class AddressListFragment extends BaseFragment implements AddressListAdapter.OnItemClickListener {

    private RecyclerView rvAddressList;
    private LinearLayoutManager mLayoutManager;
    private CardView cvSelectAddress;


    private AddressListAdapter addressListAdapter;
    private List<AddressBookModel> addressBookModelNewList;
    private AppCompatActivity activity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = GrocerApplication.getmInstance().getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_address_list, container, false);

        initToolbar();
        initComponents(rootView);

        return rootView;

    }

    /**
     * SetUp Toolbar & title
     */

    public void initToolbar() {

        ((MenuActivity) activity).setUpToolbar(activity.getString(R.string.nav_menu_my_address), false);
    }


    /**
     * init Components
     */
    @Override
    public void initComponents(View rootView) {

        rvAddressList = rootView.findViewById(R.id.fragment_addresslist_recyclerView);
        cvSelectAddress = rootView.findViewById(R.id.fragment_addresslist_cvAddresss);
        mLayoutManager = new LinearLayoutManager(activity);
        rvAddressList.setLayoutManager(mLayoutManager);

        cvSelectAddress.setOnClickListener(this);
        getListData();


    }

    /**
     * get address list data and setUp adapter
     */


    private void getListData() {
        TempListData tempListData = new TempListData();
        addressBookModelNewList = tempListData.getAddressList();

        addressListAdapter = new AddressListAdapter(activity, addressBookModelNewList);
        addressListAdapter.setOnItemClickListener(this);
        rvAddressList.setAdapter(addressListAdapter);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        if (v == cvSelectAddress) {

            /**
             *  Add addresslist Fragment
             */

            AddAddressFragment addAddressFragment = new AddAddressFragment();
            Utils.addNextFragment(activity, addAddressFragment, AddressListFragment.this, true);
        }
    }


    /**
     *  RecyclerView Item click listener
     */

    @Override
    public void onItemClick(View view, AddressBookModel viewModel, int pos) {
        for (int i = 0; i < addressBookModelNewList.size(); i++) {
            if (i == pos) {
                addressBookModelNewList.get(i).setSelected(true);
            } else {
                addressBookModelNewList.get(i).setSelected(false);
            }
        }

        addressListAdapter.addData(addressBookModelNewList);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            initToolbar();
        }
    }
}
