package com.skeleton.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.skeleton.R;
import com.skeleton.database.CommonData;
import com.skeleton.model.CommanResponse;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.CommonParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;
import com.skeleton.util.ValidateEditText;
import com.skeleton.util.customview.MaterialEditText;

import io.paperdb.Paper;

/**
 * Created by abc on 20/5/17.
 */

public class SignInFragment extends BaseFragment {
    private MaterialEditText etEmail, etPassword;
    private Button btnSignIn;
    private CheckBox cbRememberMe;
    private CommanResponse response;
    private boolean isRememberMe;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_signin, container, false);
        init(view);
        return view;
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * @param view object of the inflated view
     */
    private void init(final View view) {
        Paper.init(getContext());
        if (Paper.book().read(KEY_EMAIL) != null && Paper.book().read(KEY_PASSWORD) != null) {
            etEmail.setText(Paper.book().read(KEY_EMAIL).toString());
            etPassword.setText(Paper.book().read(KEY_PASSWORD).toString());
            cbRememberMe.setChecked(true);
        }
        etEmail = (MaterialEditText) view.findViewById(R.id.etLoginName);
        etPassword = (MaterialEditText) view.findViewById(R.id.etLoginPassword);
        btnSignIn = (Button) view.findViewById(R.id.btnLogin);
        cbRememberMe = (CheckBox) view.findViewById(R.id.cbRememberMe);
        cbRememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    isRememberMe = true;
                } else {
                    isRememberMe = false;
                }
            }
        });
        btnSignIn.setOnClickListener(this);
    }

    /**
     * @return true if all is valid else false
     */
    private boolean validateForm() {
        if (!(ValidateEditText.checkEmail(etEmail))) {
            return false;
        } else if (!ValidateEditText.checkPassword(etPassword, false)) {
            return false;
        }
        return true;
    }

    @Override
    public void onClick(final View v) {
        super.onClick(v);
        if (validateForm()) {
            CommonParams params = new CommonParams.Builder()
                    .add(KEY_EMAIL, etEmail.getText().toString().trim())
                    .add(KEY_PASSWORD, etPassword.getText().toString().trim())
                    .add(KEY_DEVICE_TYPE, "ANDROID")
                    .add(KEY_LANG, "EN")
                    .add(KEY_DEVICE_TOKEN, "XYZ")
                    .add(KEY_FLUSH_PREVIOUS_SESSION, true)
                    .add(KEY_APP_VERSION, "101")
                    .build();
            RestClient.getApiInterface().userLogin(params.getMap())
                    .enqueue(new ResponseResolver<CommanResponse>(getContext(), true, true) {
                        @Override
                        public void success(final CommanResponse sharedResponse) {
                            Log.d("Tag", sharedResponse.getData().getAccessToken());
                            response = sharedResponse;
                            CommonData.saveAccessToken(response.getData().getAccessToken());
                            if (isRememberMe) {
                                Paper.book().write(USER_EMAIL, response.getData().getUserDetails().getEmail());
                                Paper.book().write(USER_PASSWORD, etPassword.getText().toString().trim());

                            }
                            getActivity().setResult(Activity.RESULT_OK);
                            getActivity().finish();
                        }

                        @Override
                        public void failure(final APIError error) {

                        }
                    });
        }
    }


}