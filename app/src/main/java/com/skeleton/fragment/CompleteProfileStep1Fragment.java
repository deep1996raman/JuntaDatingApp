package com.skeleton.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.activity.CompleteProfileActivity;
import com.skeleton.database.CommonData;
import com.skeleton.model.CommanResponse;
import com.skeleton.model.Profile.ProfileConstants;
import com.skeleton.model.Profile.ProfileConstantsData;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.MultipartParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.ValidateEditText;
import com.skeleton.util.customview.MaterialEditText;
import com.skeleton.util.dialog.CustomDialogFragment;

import java.util.List;

/**
 * Created by abc on 23/5/17.
 */

public class CompleteProfileStep1Fragment extends BaseFragment {
    private EditText etRelation, etEthncit, etReligion, etHeigth, etBody, etSmoking, etDrinking, etOrientation;
    private TextView view1, view2, view3, view4, view5, view6, view7;
    private Button btnNext;
    private ProfileConstantsData constantsData;
    private ProfileConstants profileConstants1;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complete_step1, container, false);
        init(view);
        RestClient.getApiInterface().profileConstant().enqueue(new ResponseResolver<ProfileConstants>(getContext(), true, true) {
            @Override
            public void success(final ProfileConstants profileConstants) {
                profileConstants1 = profileConstants;

            }

            @Override
            public void failure(final APIError error) {

            }
        });


        return view;
    }

    /**
     * @param view view
     */
    private void init(final View view) {
        etRelation = (EditText) view.findViewById(R.id.etRelationShip);
        btnNext = (Button) view.findViewById(R.id.btnNext);
        view1 = (TextView) view.findViewById(R.id.view1);
        view2 = (TextView) view.findViewById(R.id.view2);
        view3 = (TextView) view.findViewById(R.id.view3);
        view4 = (TextView) view.findViewById(R.id.view4);
        view5 = (TextView) view.findViewById(R.id.view5);
        view6 = (TextView) view.findViewById(R.id.view6);
        view7 = (TextView) view.findViewById(R.id.view7);
        etEthncit = (EditText) view.findViewById(R.id.etEthnicity);
        etReligion = (EditText) view.findViewById(R.id.etReligon);
        etHeigth = (EditText) view.findViewById(R.id.etHeight);
        etBody = (EditText) view.findViewById(R.id.etBodyType);
        etSmoking = (EditText) view.findViewById(R.id.etSmoking);
        etOrientation = (EditText) view.findViewById(R.id.etOrientation);
        etRelation.setOnClickListener(this);
        etEthncit.setOnClickListener(this);
        etReligion.setOnClickListener(this);
        etHeigth.setOnClickListener(this);
        etOrientation.setOnClickListener(this);
        etBody.setOnClickListener(this);
        etSmoking.setOnClickListener(this);
        btnNext.setOnClickListener(this);

    }

    @Override
    public void onClick(final View v) {
        super.onClick(v);
        int id;
        id = v.getId();
        switch (id) {
            case R.id.etRelationShip:
                setDialog(R.string.relationship_history, profileConstants1.getConstantsData().getRelationshipHistory(), v);
                break;
            case R.id.etReligon:
                setDialog(R.string.religion, profileConstants1.getConstantsData().getReligion(), v);
                break;
            case R.id.etBodyType:
                setDialog(R.string.body_type, profileConstants1.getConstantsData().getBodyType(), v);
                break;
            case R.id.etHeight:
                setDialog(R.string.height, profileConstants1.getConstantsData().getHeight(), v);
                break;
            case R.id.etSmoking:
                setDialog(R.string.smoking, profileConstants1.getConstantsData().getSmoking(), v);
                break;
            case R.id.etOrientation:
                setDialog(R.string.orientation, profileConstants1.getConstantsData().getOrientation(), v);
                break;
            case R.id.etEthnicity:
                setDialog(R.string.ethnicity, profileConstants1.getConstantsData().getEthnicity(), v);
                break;
            case R.id.btnNext:
                if (validator()) {
                    updateProfile();
                } else {
                    Toast.makeText(getContext(), "Fill All feilds", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }


    }

    /**
     * update
     */
    private void updateProfile() {
        MultipartParams multipartParams = new MultipartParams.Builder().
                add(KEY_RELATIONSHIP, etRelation.getText().toString().trim())
                .add(KEY_ETHNICITY, etEthncit.getText().toString().trim())
                .add(KEY_RELIGION, etReligion.getText().toString().trim())
                .add(KEY_HEIGHT, etHeigth.getText().toString().trim())
                .add(KEY_ORIENTATION, etOrientation.getText().toString().trim())
                .add(KEY_BODY_TYPE, etBody.getText().toString().trim())
                .add(KEY_SMOKING, etSmoking.getText().toString().trim())
                .build();
        RestClient.getApiInterface().updateProfile("bearer " + CommonData.getAccessToken(), multipartParams.getMap())
                .enqueue(new ResponseResolver<CommanResponse>(getContext()) {
                    @Override
                    public void success(final CommanResponse commanResponse) {
                        CommonData.saveAccessToken(commanResponse.getData().getAccessToken());
                        Toast.makeText(getContext(), commanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        ((CompleteProfileActivity) getContext()).setFragment(1);


                    }

                    @Override
                    public void failure(final APIError error) {
                        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
    }


    /**
     * @param title title of the dialog window
     * @param list  list to be infalted
     * @param v     view
     */
    private void setDialog(final int title, final List<String> list, final View v) {
        FragmentManager fm = getChildFragmentManager();
        CustomDialogFragment.newInstance(getString(title),
                list, new CustomDialogFragment.ItemClicked() {
                    @Override
                    public void sendText(final String text, final int viewId) {
                        ((MaterialEditText) v).setText(text);
                        setBgToHrLine(((MaterialEditText) v).getId());
                    }
                }).show(fm, "dialog");
    }

    /**
     * Change color of the view above categories
     *
     * @param id of the each edit text
     */
    private void setBgToHrLine(final int id) {
        switch (id) {
            case R.id.etRelationShip:
                view1.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green_light));
                break;
            case R.id.etEthnicity:
                view2.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green_light));
                break;
            case R.id.etReligon:
                view3.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green_light));
                break;
            case R.id.etHeight:
                view4.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green_light));
                break;
            case R.id.etBodyType:
                view5.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green_light));
                break;
            case R.id.etSmoking:
                view6.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green_light));
                break;
            case R.id.etOrientation:
                view7.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green_light));
                break;
            default:
                break;
        }
    }

    /**
     * Validate fields
     *
     * @return true if all fields are not empty
     */
    private boolean validator() {
        if (ValidateEditText.genericEmpty(etRelation)) {
            return false;
        } else if (ValidateEditText.genericEmpty(etHeigth)) {
            return false;
        } else if (ValidateEditText.genericEmpty(etEthncit)) {
            return false;
        } else if (ValidateEditText.genericEmpty(etReligion)) {
            return false;
        } else if (ValidateEditText.genericEmpty(etBody)) {
            return false;
        } else if (ValidateEditText.genericEmpty(etSmoking)) {
            return false;
        } else if (ValidateEditText.genericEmpty(etOrientation)) {
            return false;
        }
        return true;
    }
}
