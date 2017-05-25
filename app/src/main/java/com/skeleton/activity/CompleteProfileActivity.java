package com.skeleton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.adapter.PagerAdapter;
import com.skeleton.database.CommonData;
import com.skeleton.fragment.CompleteProfileStep1Fragment;
import com.skeleton.fragment.CompleteProfileStep2Fragment;
import com.skeleton.model.Profile.ProfileConstants;
import com.skeleton.model.UserDetails;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.customview.CustomViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * profile
 */
public class CompleteProfileActivity extends BaseActivity {
    private CustomViewPager viewPager;
    private List<Fragment> fragmentList;
    private TextView tvSkip, tvToolbarMiddle;
    private ImageView imageView;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
        init();
        tvToolbarMiddle.setText("Profile completeness");

        UserDetails userDetails = getIntent().getParcelableExtra(SHARED_OBJ);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);
        viewPager.setOffscreenPageLimit(2);
        if (userDetails != null) {

            if (userDetails.getStep1CompleteOrSkip()) {
                viewPager.setCurrentItem(1);
            } else {
                viewPager.setCurrentItem(0);
            }

        } else {
            Log.d("Debug", "userDetails object null");
        }
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                int pageNumr = viewPager.getCurrentItem() + 1;
                RestClient.getApiInterface().skipStep("bearer " + CommonData.getAccessToken(), pageNumr)
                        .enqueue(new ResponseResolver<ProfileConstants>(getBaseContext(), true, true) {
                            @Override
                            public void success(final ProfileConstants profileConstants) {
                                if (viewPager.getCurrentItem() == 0) {
                                    viewPager.setCurrentItem(1);
                                } else {
                                    Intent intent = new Intent(CompleteProfileActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();

                                }
                            }

                            @Override
                            public void failure(final APIError error) {

                            }
                        });

            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (viewPager.getCurrentItem() != 0) {
                    viewPager.setCurrentItem(0);
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() != 0) {
            viewPager.setCurrentItem(0);
        } else {
            finish();
        }
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (requestCode == REQ_HOME && resultCode == RESULT_OK) {
            finish();

        }
    }

    /**
     * init
     */
    public void init() {
        viewPager = (CustomViewPager) findViewById(R.id.customViewPager);
        imageView = (ImageView) findViewById(R.id.ivToolbarStrt);
        tvToolbarMiddle = (TextView) findViewById(R.id.tvToolbarMiddle);
        tvSkip = (TextView) findViewById(R.id.tvToolBarLastBtn);
        fragmentList = new ArrayList<>();
        fragmentList.add(new CompleteProfileStep1Fragment());
        fragmentList.add(new CompleteProfileStep2Fragment());

    }

    /**
     * set fragmet
     *
     * @param fragmentNumber interger value
     */
    public void setFragment(final int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }

}
