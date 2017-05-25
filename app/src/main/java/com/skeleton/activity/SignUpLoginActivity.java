package com.skeleton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.database.CommonData;
import com.skeleton.fragment.SignInFragment;
import com.skeleton.fragment.SignUpFragment;
import com.skeleton.model.CommanResponse;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Sign up and login screen
 */
public class SignUpLoginActivity extends BaseActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<Fragment> fragments;
    private String mytoken;
    private CommanResponse response;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_login);
        init();
        PagerAdapter pagerAdapter = new com.skeleton.adapter.PagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    /**
     * initialize
     */

    private void init() {
        viewPager = (ViewPager) findViewById(R.id.vpLoginSignUp);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        fragments = new ArrayList<>();
        fragments.add(new SignInFragment());
        fragments.add(new SignUpFragment());
    }

    /**
     * controller program till home screen
     */
    private void checkUserProfile() {
        mytoken = CommonData.getAccessToken();
        if (mytoken == null) {
            Log.d("Debug", "no Access Token");
            Intent intent = new Intent(this, SignUpLoginActivity.class);
            startActivityForResult(intent, REQ_SIGN_UP);
        } else {
            Log.d("Debug", "Access Token");
            Log.d("Debug", CommonData.getAccessToken());
            RestClient.getApiInterface().getUserProfile("bearer " + CommonData.getAccessToken())
                    .enqueue(new ResponseResolver<CommanResponse>(getBaseContext(), true) {
                        @Override
                        public void success(final CommanResponse commanResponse) {
                            CommonData.saveAccessToken(commanResponse.getData().getAccessToken());
                            Log.d("Debug", "Access Token--Sucess");
                            Log.d("Debug", CommonData.getAccessToken());
                            CommonData.saveData(commanResponse.getData().getUserDetails());
                            if (!commanResponse.getData().getUserDetails().getPhoneVerified()) {
                                Intent intent = new Intent(SignUpLoginActivity.this, OtpActivity.class);
                                startActivity(intent);
                            } else if (!(commanResponse.getData().getUserDetails().getStep1CompleteOrSkip()
                                    || commanResponse.getData().getUserDetails().getStep2CompleteOrSkip())) {
                                Intent intent = new Intent(SignUpLoginActivity.this, CompleteProfileActivity.class);
                                startActivityForResult(intent, REQ_COMPLETE_PROFILE);

                            } else {
                                Intent intent = new Intent(SignUpLoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void failure(final APIError error) {
                            Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }

    }
}
