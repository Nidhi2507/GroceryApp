package com.dioco.groceyapp.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dioco.groceyapp.GrocerApplication;
import com.dioco.groceyapp.R;
import com.dioco.groceyapp.fragment.AboutusFragment;
import com.dioco.groceyapp.fragment.AddressListFragment;
import com.dioco.groceyapp.fragment.CartListFragment;
import com.dioco.groceyapp.fragment.MainFragment;
import com.dioco.groceyapp.fragment.NotificationListFragment;
import com.dioco.groceyapp.fragment.OrderListFragment;
import com.dioco.groceyapp.fragment.ProfileFragment;
import com.dioco.groceyapp.util.Utils;

/**
 * *************************************************************************
 *
 * @ClassdName:MenuActivity
 * @CreatedDate:
 * @ModifiedBy: not yet
 * @ModifiedDate: not yet
 * @purpose:This Class is use to side navigation with menu & toolbar.
 * <p/>
 * *************************************************************************
 */


public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private TextView tvTitle;
    private Fragment mFragment = null;
    private MainFragment mainFragment;
    private RelativeLayout rlProfile;
    private NavigationView navigationView;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initView();
        initToolbar();

    }

    /**
     *  Init component
     */

    private void initView() {

        GrocerApplication.getmInstance().setActivity(MenuActivity.this);


        tvTitle = (TextView) findViewById(R.id.tvTitle);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_menu);
        rlProfile = (RelativeLayout) headerLayout.findViewById(R.id.row_header_rlProfile);
        rlProfile.setOnClickListener(this);


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);


        mainFragment = new MainFragment();
        openFragment(mainFragment);


    }


    /**
     *  Init toolbar & actionbar
     */


    private void initToolbar() {


        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);

        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main, menu);
        //this.menu = menu;
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Utils.hideKeyboard(MenuActivity.this);

            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager.getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
                //    return true;

            } else {
                if (drawerLayout != null) {
                    drawerLayout.openDrawer(GravityCompat.START);
                    //return true;
                }
            }
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *  Navigation item click
     */

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home) {

            mFragment = new MainFragment();
            openFragment(mFragment);


        } else if (id == R.id.nav_cart) {

            mFragment = new CartListFragment();
            openFragment(mFragment);

        } else if (id == R.id.nav_address) {

            mFragment = new AddressListFragment();
            openFragment(mFragment);

        } else if (id == R.id.nav_order) {

            mFragment = new OrderListFragment();
            openFragment(mFragment);

        } else if (id == R.id.nav_notification) {

            mFragment = new NotificationListFragment();
            openFragment(mFragment);

        } else if (id == R.id.nav_about_us) {

            mFragment = new AboutusFragment();
            openFragment(mFragment);

        } else if (id == R.id.nav_share) {

            Toast.makeText(getApplicationContext(), "Share", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_logout) {
            openLogoutDialog();

        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     *  SetUp toolbar & Title & HomeIndicator Image & Navigation drawer lock
     */


    public void setUpToolbar(final String title, final boolean isShowback) {

        setSupportActionBar(toolbar);
        isNavDrawerLock(isShowback ? false : true);
        getSupportActionBar().setHomeAsUpIndicator(isShowback ? R.drawable.ic_back : R.drawable.ic_menu);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        tvTitle.setText(title);

    }

    public void isNavDrawerLock(final boolean isLock) {
        drawerLayout.setDrawerLockMode(isLock ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    /**
     *  Open fragment
     */

    private void openFragment(final Fragment mFragment) {

        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.flcontainer, mFragment, mFragment.getClass().getSimpleName());
        transaction.commit();

    }


    @Override
    public void onClick(View v) {

        if (v == rlProfile) {
            drawer.closeDrawer(GravityCompat.START);
            mFragment = new ProfileFragment();
            openFragment(mFragment);
        }
    }

    /**
     *  Open Logout Dialog
     */

    private void openLogoutDialog() {


        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
        builder.setTitle(getString(R.string.app_name_new));
        builder.setMessage(getString(R.string.are_you_sure_do_you_want_to_logout));

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                        GrocerApplication.getmInstance().clearePreferenceData();
                        Intent i = new Intent(getApplicationContext(), SplashActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        overridePendingTransition(R.anim.anim_left_in, R.anim.anim_right_out);
                        finish();
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();

    }


}
