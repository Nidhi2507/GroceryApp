package com.dioco.groceyapp.fragment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.dioco.groceyapp.Activity.MenuActivity;
import com.dioco.groceyapp.GrocerApplication;
import com.dioco.groceyapp.R;
import com.dioco.groceyapp.adapter.AddressListAdapter;
import com.dioco.groceyapp.data.TempListData;
import com.dioco.groceyapp.model.addressBook.AddressBookModel;
import com.dioco.groceyapp.util.Utils;

import java.util.Calendar;
import java.util.List;

/**
 * *************************************************************************
 *
 * @ClassdName:SelectAddressFragment
 * @CreatedDate:
 * @ModifiedBy: not yet
 * @ModifiedDate: not yet
 * @purpose:This Class use for select Delivery DateTime & Dilevery Address & total Items & Amount
 * <p/>
 * *************************************************************************
 */


public class SelectAddressFragment extends BaseFragment implements AddressListAdapter.OnItemClickListener {

    private RecyclerView rvAddressList;
    private TextView tvDate;
    private TextView tvTime;
    private RelativeLayout rlContinue;
    private LinearLayoutManager mLayoutManager;
    private CardView cvSelectAddress;
    private AppCompatActivity activity;

    private AddressListAdapter addressListAdapter;
    private List<AddressBookModel> addressBookModelNewList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = GrocerApplication.getmInstance().getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View rootView = inflater.inflate(R.layout.fragment_select_address, container, false);
        initToolbar();
        initComponents(rootView);


        return rootView;

    }

    /**
     * SetUp Toolbar & title
     */

    public void initToolbar() {


        ((MenuActivity) activity).setUpToolbar(activity.getString(R.string.delivery), true);
    }


    /**
     * init Components
     */

    @Override
    public void initComponents(View rootView) {

        rvAddressList = rootView.findViewById(R.id.fragment_addresslist_recyclerView);
        tvTime = rootView.findViewById(R.id.fragment_addresslist_tvTime);
        tvDate = rootView.findViewById(R.id.fragment_addresslist_tvDate);
        rlContinue = rootView.findViewById(R.id.fragment_addresslist_rlContinue);
        cvSelectAddress = rootView.findViewById(R.id.fragment_addresslist_cvAddresss);
        mLayoutManager = new LinearLayoutManager(activity);
        rvAddressList.setLayoutManager(mLayoutManager);

        rlContinue.setOnClickListener(this);
        tvDate.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        cvSelectAddress.setOnClickListener(this);

        getListData();


    }

    /**
     * get address list data and setUp adapter
     */

    private void getListData()
    {
        TempListData  tempListData=new TempListData();
        addressBookModelNewList = tempListData.getAddressList();
        addressListAdapter = new AddressListAdapter(activity, addressBookModelNewList);
        addressListAdapter.setOnItemClickListener(this);
        rvAddressList.setAdapter(addressListAdapter);

    }

    /**
     *  Open DatePicker Dailog
     */

    private void openDatePicketDailog() {


        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(activity,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String selectDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        tvDate.setText(activity.getString(R.string.select_delivery_date)+" : "+selectDate);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }


    /**
     *  Open TimePicker Dailog
     */
    private void openTimePicker() {

        Calendar cal = Calendar.getInstance();

        new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                try {

                    String timeString = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                    tvTime.setText(activity.getString(R.string.select_delivery_time)+" : "+timeString);

                } catch (Exception e) {
                }


            }
        }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false).show();


    }


    /**
     *  OneClick listener
     */

    @Override
    public void onClick(View v) {
        super.onClick(v);

        if (v == tvDate) {
            openDatePicketDailog();
        } else if (v == tvTime) {
            openTimePicker();
        }
        else if(v==cvSelectAddress)
        {
            AddAddressFragment addAddressFragment=new AddAddressFragment();
            Utils.addNextFragment(activity, addAddressFragment, SelectAddressFragment.this, true);
        }
        else if(v==rlContinue)
        {
            PaymentFragment paymentFragmentNew=new PaymentFragment();
            Utils.addNextFragment(activity, paymentFragmentNew, SelectAddressFragment.this, true);
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

        if(!hidden)
        {
            initToolbar();
        }
    }
}
