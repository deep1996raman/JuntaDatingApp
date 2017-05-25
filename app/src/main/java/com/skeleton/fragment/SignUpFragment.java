package com.skeleton.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.skeleton.R;
import com.skeleton.database.CommonData;
import com.skeleton.model.CommanResponse;
import com.skeleton.model.UserDetails;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.MultipartParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.ValidateEditText;
import com.skeleton.util.customview.MaterialEditText;
import com.skeleton.util.imagepicker.ImageChooser;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import io.paperdb.Paper;


/**
 * Created by abc on 20/5/17.
 */

public class SignUpFragment extends BaseFragment {

    private MaterialEditText etFirstName, etPhoneNum, etEmail, etPassword, etConfirmPassword, etDob;
    private TextView tvCountryCode;
    private Button btnSignUp;
    private String mFirstName, mPhoneNum, mEmail, mPassword;
    private int mSelectedGender, mGender;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    private CheckBox cbTos;
    private String mDOB;
    private File mProfilePic;
    private ImageView ivProfilePic;
    private ImageChooser chooser;
    private UserDetails userDetails;


    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_signup, container, false);
        init(view);
        return view;
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        chooser.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        chooser.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(final View v) {
        super.onClick(v);
        final int id = v.getId();
        switch (id) {
            case R.id.btnSignUp:
                registerUser();


                break;
            case R.id.ivProfile:
                chooser = new ImageChooser.Builder(SignUpFragment.this).build();
                chooser.selectImage(new ImageChooser.OnImageSelectListener() {
                    @Override
                    public void loadImage(final List<ChosenImage> list) {
                        mProfilePic = new File(list.get(0).getOriginalPath());
                        Glide.with(getContext()).load(mProfilePic).into(ivProfilePic);
                    }

                    @Override
                    public void croppedImage(final File mCroppedImage) {

                    }
                });
                break;

            default:
                break;
        }
    }


    /**
     * Called when clicked on Sign up Button
     */
    private void registerUser() {
        if (checkValidity()) {
            extractData();

            MultipartParams params = new MultipartParams.Builder()
                    .add(KEY_FIRST_NAME, mFirstName)
                    .add(KEY_DOB, mDOB)
                    .add(KEY_COUNTRY_CODE, "+91")
                    .add(KEY_PHONE_NUM, mPhoneNum)
                    .add(KEY_EMAIL, mEmail)
                    .add(KEY_PASSWORD, mPassword)
                    .add(KEY_GENDER, "0")
                    .add(KEY_DEVICE_TOKEN, "XYZ")
                    .add(KEY_APP_VERSION, "101")
                    .add(KEY_LANG, "EN")
                    .add(KEY_DEVICE_TYPE, "ANDROID")
                    .addFile(KEY_PROFILE_PIC, mProfilePic).build();
            RestClient.getApiInterface().userRegister(params.getMap()).enqueue(new ResponseResolver<CommanResponse>(getContext()
                    , true, true) {
                @Override
                public void success(final CommanResponse sharedResponse) {
                    CommonData.saveAccessToken(sharedResponse.getData().getAccessToken());
                    userDetails = sharedResponse.getData().getUserDetails();
                    CommonData.saveData(userDetails);
                    getActivity().setResult(Activity.RESULT_OK);
                    getActivity().finish();

                   /* Intent intent = new Intent(getActivity(), OtpActivity.class);
                    intent.putExtra(SHARED_OBJ, sharedResponse.getData().getUserDetails());
                    intent.putExtra(KEY_MODE, REQ_SIGN_UP);
                    getActivity().startActivityForResult(intent, REQ_OTP);
                    */
                }

                @Override
                public void failure(final APIError error) {
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                }
            });
        }
    }

    /**
     * initiating views and other variables
     *
     * @param view view
     */
    private void init(final View view) {
        etFirstName = (MaterialEditText) view.findViewById(R.id.etName);
        etPhoneNum = (MaterialEditText) view.findViewById(R.id.etPhoneNo);
        etEmail = (MaterialEditText) view.findViewById(R.id.etEmailAddr);
        etPassword = (MaterialEditText) view.findViewById(R.id.etPassword);
        etConfirmPassword = (MaterialEditText) view.findViewById(R.id.etConfirmPassword);
        rgGender = (RadioGroup) view.findViewById(R.id.rgGender);
        etDob = (MaterialEditText) view.findViewById(R.id.etDOB);
        ivProfilePic = (ImageView) view.findViewById(R.id.ivProfile);
        rbMale = (RadioButton) view.findViewById(R.id.rbMale);
        rbFemale = (RadioButton) view.findViewById(R.id.rbFemale);
        cbTos = (CheckBox) view.findViewById(R.id.cbTermsAndCondi);
        btnSignUp = (Button) view.findViewById(R.id.btnSignUp);
        tvCountryCode = (TextView) view.findViewById(R.id.dummyNumber);
        tvCountryCode.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        ivProfilePic.setOnClickListener(this);
        etDob.setOnClickListener(this);
    }

    /**
     * Check's whether the form filled by user is valid or not
     *
     * @return true if all fields are valid else false
     */
    private boolean checkValidity() {
        mSelectedGender = rgGender.getCheckedRadioButtonId();
        if (!(ValidateEditText.checkName(etFirstName, true))) {
            return false;
        } else if (!ValidateEditText.checkPhoneNumber(etPhoneNum)) {
            return false;
        } else if (!(ValidateEditText.checkEmail(etEmail))) {
            return false;
        } else if (!ValidateEditText.checkPassword(etPassword, false)) {
            return false;
        } else if (!ValidateEditText.checkPassword(etConfirmPassword, true)) {
            return false;
        } else if (!ValidateEditText.comparePassword(etPassword, etConfirmPassword)) {
            return false;
        } else if (!cbTos.isChecked()) {
            Toast.makeText(getContext(), R.string.err_tos, Toast.LENGTH_SHORT).show();
            return false;
        } else if (mSelectedGender == -1) {
            Toast.makeText(getContext(), R.string.err_gender, Toast.LENGTH_SHORT).show();
            return false;
        } else if (!checkDOB(etDob)) {
            return false;
        } else if (mProfilePic == null) {
            Toast.makeText(getContext(), R.string.err_profile_pic, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * Extract Data from EditText
     */
    private void extractData() {
        mFirstName = etFirstName.getText().toString().trim();
        mEmail = etEmail.getText().toString().trim();
        mPassword = etPassword.getText().toString().trim();
        mPhoneNum = etPhoneNum.getText().toString().trim();
        Paper.init(getContext());
        Paper.book().write("phoneNumber", mPhoneNum);
        mDOB = etDob.getText().toString().trim();
        if (rbMale.isChecked()) {
            mGender = 0;
        } else {
            mGender = 1;
        }
    }


    /**
     * Checks if date is in valid format
     *
     * @param editText : editTextDOB containing date
     * @return : true if valid, else returns false
     */
    private boolean checkDOB(final EditText editText) {
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = editText.getText().toString();
        try {
            mDOB = df.parse(s).toString();
            return true;
        } catch (final ParseException e) {
            editText.setError(getString(R.string.error_invalid_data));
            return false;
        }
    }

}