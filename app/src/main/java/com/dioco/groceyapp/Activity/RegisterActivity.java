package com.dioco.groceyapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dioco.groceyapp.R;
import com.dioco.groceyapp.util.Utils;


/**
 * *************************************************************************
 *
 * @ClassdName:LoginActivity
 * @CreatedDate:
 * @ModifiedBy: not yet
 * @ModifiedDate: not yet
 * @purpose: create beautiful signup screens the right way using Material design spec with the assistance of Google’s new design support library.
 * <p/>
 * *************************************************************************
 */


public class RegisterActivity extends BaseActivity {

    private LinearLayout llContainer;
    private RelativeLayout rlSignUp;
    private EditText etPhoneNumber;
    private EditText etEmail;
    private EditText etUserName;
    private EditText etPassword;
    private EditText etConfrimPassWord;
    private TextView tvSignIn;


    private String phoneNumber;
    private String emailAdd;
    private String userName;
    private String passWord;
    private String confrimPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        initComponents();
    }

    /**
     * Init component
     */


    @Override
    public void initComponents() {

        llContainer = (LinearLayout) findViewById(R.id.activity_register_llContainer);

        rlSignUp = (RelativeLayout) findViewById(R.id.activity_register_rlSignUp);
        etPhoneNumber = (EditText) findViewById(R.id.activity_register_etPhone);
        etEmail = (EditText) findViewById(R.id.activity_register_etEmail);
        etUserName = (EditText) findViewById(R.id.activity_register_etUsername);
        etPassword = (EditText) findViewById(R.id.activity_register_etPassword);
        etConfrimPassWord = (EditText) findViewById(R.id.activity_register_etConfrimPassword);
        etPhoneNumber = (EditText) findViewById(R.id.activity_register_etPhone);
        tvSignIn = (TextView) findViewById(R.id.activity_register_tvSignIn);

        rlSignUp.setOnClickListener(this);
        tvSignIn.setOnClickListener(this);

    }


    /**
     * Validating
     */

    private void submitForm() {

        userName = etUserName.getText().toString().trim();
        emailAdd = etEmail.getText().toString().trim();
        phoneNumber = etPhoneNumber.getText().toString().trim();
        passWord = etPassword.getText().toString().trim();
        confrimPassWord = etConfrimPassWord.getText().toString().trim();


        if (Utils.isOnline(RegisterActivity.this, true)) {
            if (userName.isEmpty()) {
                Utils.snackbar(llContainer, getString(R.string.val_enter_user_name), true, RegisterActivity.this);
            } else if (emailAdd.isEmpty()) {
                Utils.snackbar(llContainer, getString(R.string.val_enter_email), true, RegisterActivity.this);
            } else if (!Utils.isValidEmail(emailAdd)) {
                Utils.snackbar(llContainer, getString(R.string.val_enter_valid_email), true, RegisterActivity.this);
            } else if (passWord.length() < 6) {
                Utils.snackbar(llContainer, getString(R.string.val_password_greter_six), true, RegisterActivity.this);
            } else if (confrimPassWord.isEmpty()) {
                Utils.snackbar(llContainer, getString(R.string.val_enter_conf_password), true, RegisterActivity.this);
            } else if (confrimPassWord.length() < 6) {
                Utils.snackbar(llContainer, getString(R.string.val_conf_password_greter_six), true, RegisterActivity.this);
            } else if (!passWord.equals(confrimPassWord)) {
                Utils.snackbar(llContainer, getString(R.string.val_password_confirm_password_not_match), true, RegisterActivity.this);

            } else if (phoneNumber.isEmpty()) {
                Utils.snackbar(llContainer, getString(R.string.val_enter_phone), true, RegisterActivity.this);

            } else {

                overridePendingTransition(R.anim.anim_left_in, R.anim.anim_right_out);
                Utils.hideKeyboard(RegisterActivity.this);
                finish();

            }
        } else {
            Utils.snackbar(llContainer, "" + getString(R.string.check_connection), true, RegisterActivity.this);

        }

    }


    @Override
    public void onClick(View v) {

        Utils.hideKeyboard(RegisterActivity.this);

        if (v == tvSignIn) {

            finish();
            Utils.hideKeyboard(RegisterActivity.this);
            overridePendingTransition(R.anim.anim_left_in, R.anim.anim_right_out);

        } else if (v == rlSignUp) {

            submitForm();
        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        overridePendingTransition(R.anim.anim_left_in, R.anim.anim_right_out);
        Utils.hideKeyboard(RegisterActivity.this);
        finish();
    }

}
