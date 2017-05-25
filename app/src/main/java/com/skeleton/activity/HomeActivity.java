package com.skeleton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.database.CommonData;

/**
 * Home Activity
 */
public class HomeActivity extends BaseActivity {
    private TextView tvToolbarCenter, tvToolbarEnd;
    private ImageView ivToolbarStart;
    private TextView tvLogout;
    private DrawerLayout drawerLayout;

    @Override
    public void onBackPressed() {
        finish();

    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        Log.d("Debug", "In home");
        ivToolbarStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                drawerLayout.openDrawer(Gravity.START);

            }
        });
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                CommonData.saveAccessToken(null);
                Intent intent = new Intent(HomeActivity.this, SignUpLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * init
     */
    private void init() {
        tvToolbarCenter = (TextView) findViewById(R.id.tvToolbarMiddle);
        tvToolbarEnd = (TextView) findViewById(R.id.tvToolBarLastBtn);
        ivToolbarStart = (ImageView) findViewById(R.id.ivToolbarStrt);
        tvToolbarEnd.setVisibility(View.GONE);
        tvToolbarCenter.setVisibility(View.GONE);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        ivToolbarStart.setImageResource(R.drawable.ic_dehaze_black_24dp);
        tvLogout = (TextView) findViewById(R.id.tvLogout);
    }
}
