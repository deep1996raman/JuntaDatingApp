package com.skeleton.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.database.CommonData;
import com.skeleton.model.CommanResponse;
import com.skeleton.model.UserDetails;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ApiInterface;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.ValidateEditText;

import io.paperdb.Paper;

/**
 * base
 */

public class OtpActivity extends BaseActivity implements TextWatcher {

    private EditText etFirst, etSecond, etThird, etFourth;
    private Button btnVerify;
    private UserDetails userDetails;
    private String mPhone;
    private TextView tvPhoneNumber, tvResend, tvEditNumber, tvToolbarEnd;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        init();
        tvPhoneNumber.setText((CharSequence) Paper.book().read("phoneNumber"));
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (requestCode == REQ_EDIT_NUM) {
            if (resultCode == RESULT_OK) {
                mPhone = data.getStringExtra("num");
                CommonData.getData().setPhoneNo(mPhone);
                tvPhoneNumber.setText(mPhone);
            }
        }
    }


    @Override
    public void onClick(final View v) {
        int id;
        id = v.getId();
        switch (id) {
            case R.id.tvEditNumber:
                Intent intent = new Intent(OtpActivity.this, EditNumberActivity.class);
                startActivityForResult(intent, REQ_EDIT_NUM);
                break;
            case R.id.tvResendOtp:

                Toast.makeText(getBaseContext(), "some flow issue", Toast.LENGTH_LONG).show();
                ApiInterface apiInterface = RestClient.getApiInterface();
                apiInterface.reSendOtp("bearer " + CommonData.getAccessToken())
                        .enqueue(new ResponseResolver<CommanResponse>(getBaseContext(), true) {
                            @Override
                            public void success(final CommanResponse commanResponse) {
                                Log.d("Debug", "In resend otp Sucess");
                                Log.d("Debug--new Access Token", CommonData.getAccessToken());
                                Toast.makeText(getBaseContext(), "new OTP has been sent", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void failure(final APIError error) {
                                Log.d("Debug", "In resend otp failure");
                                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        });

                break;
            case R.id.btnVerify:
                setResult(RESULT_OK);
                finish();
              /*  Log.d("Debug", "Btn verified pressed");
                String mOtp;
                mOtp = etFirst.getText().toString();
                mOtp += etSecond.getText().toString();
                mOtp += etThird.getText().toString();
                mOtp += etFourth.getText().toString();
                CommonParams commonParams = new CommonParams.Builder()
                        .add("countryCode", KEY_COUNTRY_CODE)
                        .add("phoneNo", CommonData.getData().getPhoneNo())
                        .add("OTPCode", mOtp).build();
                Log.d("Debug", "Abt to hit verify call");
                RestClient.getApiInterface().verifyOtp("bearer " + CommonData.getAccessToken(), commonParams.getMap())
                        .enqueue(new ResponseResolver<CommanResponse>(OtpActivity.this, true, true) {
                            @Override
                            public void success(final CommanResponse commanResponse) {
                                Log.d("Debug", "verified");
                                Toast.makeText(OtpActivity.this, "verified", Toast.LENGTH_LONG).show();
                                setResult(RESULT_OK);
                                finish();

                            }

                            @Override
                            public void failure(final APIError error) {
                                Log.d("Debug", "Verify Failure");
                                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                                setResult(RESULT_OK);
                                finish();
                            }
                        });
               */
                break;

            default:
                break;
        }


    }


    /**
     * Used for Initialization
     */
    private void init() {

        etFirst = (EditText) findViewById(R.id.etFirst);
        tvToolbarEnd = (TextView) findViewById(R.id.tvToolBarLastBtn);
        tvToolbarEnd.setVisibility(View.GONE);
        tvPhoneNumber = (TextView) findViewById(R.id.tvphoneNumber);
        tvEditNumber = (TextView) findViewById(R.id.tvEditNumber);
        tvResend = (TextView) findViewById(R.id.tvResendOtp);
        etSecond = (EditText) findViewById(R.id.etSecond);
        etThird = (EditText) findViewById(R.id.etThird);
        etFourth = (EditText) findViewById(R.id.etFourth);
        btnVerify = (Button) findViewById(R.id.btnVerify);
        etFirst.addTextChangedListener(this);
        btnVerify.setOnClickListener(this);
        etSecond.addTextChangedListener(this);
        etThird.addTextChangedListener(this);
        etFourth.addTextChangedListener(this);
        tvEditNumber.setOnClickListener(this);
        tvResend.setOnClickListener(this);
    }


    @Override
    public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
    }

    @Override
    public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
        if (etFirst.getText().toString().length() == 1) {
            etSecond.requestFocus();
        }
        if (etSecond.getText().toString().length() == 1) {
            etThird.requestFocus();
        }
        if (etThird.getText().toString().length() == 1) {
            etFourth.requestFocus();
        }

    }

    @Override
    public void afterTextChanged(final Editable s) {

    }

    /**
     * Check if otp fields are empty
     *
     * @return true if otp is not empty else false
     */
    private boolean checkOtpFields() {
        return !(ValidateEditText.genericEmpty(etFirst) || ValidateEditText.genericEmpty(etSecond)
                || ValidateEditText.genericEmpty(etThird) || ValidateEditText.genericEmpty(etFourth));
    }
}
