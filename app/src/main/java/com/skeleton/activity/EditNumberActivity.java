package com.skeleton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.database.CommonData;
import com.skeleton.model.CommanResponse;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.MultipartParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.ValidateEditText;

/**
 * edit number screen
 */
public class EditNumberActivity extends BaseActivity {
    private TextView tvToolbarCenter, tvToolbarEnd;
    private ImageView ivToolbarStart;
    private EditText etNumber;
    private Button btnSave;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_number);
        init();
        ivToolbarStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (validation()) {
                    MultipartParams multipartParams = new MultipartParams.Builder()
                            .add(KEY_NEW_NUM, etNumber.getText().toString())
                            .build();
                    RestClient.getApiInterface().updateProfile("bearer " + CommonData.getAccessToken(), multipartParams.getMap())
                            .enqueue(new ResponseResolver<CommanResponse>(EditNumberActivity.this, true, true) {
                                @Override
                                public void success(final CommanResponse commanResponse) {
                                    Log.d("Debug--edit number", CommonData.getAccessToken());
                                    Intent intent = getIntent().putExtra("num", etNumber.getText().toString());
                                    setResult(RESULT_OK, intent);
                                    finish();

                                }

                                @Override
                                public void failure(final APIError error) {
                                    Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                                }
                            });

                }

            }
        });

    }

    /**
     * init
     */
    private void init() {
        etNumber = (EditText) findViewById(R.id.etNewNumber);
        tvToolbarCenter = (TextView) findViewById(R.id.tvToolbarMiddle);
        tvToolbarEnd = (TextView) findViewById(R.id.tvToolBarLastBtn);
        ivToolbarStart = (ImageView) findViewById(R.id.ivToolbarStrt);
        tvToolbarEnd.setVisibility(View.GONE);
        tvToolbarCenter.setText(R.string.changeNumber);
        ivToolbarStart.setImageResource(R.drawable.icon_back);
        btnSave = (Button) findViewById(R.id.btnSubmit);
    }

    /**
     * @return true or false
     */
    private boolean validation() {
        if (!ValidateEditText.checkPhoneNumber(etNumber)) {
            return false;
        }
        return true;
    }
}


