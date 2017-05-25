package com.skeleton.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.skeleton.R;
import com.skeleton.activity.HomeActivity;
import com.skeleton.adapter.RecyclerViewAdapter;
import com.skeleton.database.CommonData;
import com.skeleton.model.CommanResponse;
import com.skeleton.model.InterestCategories;
import com.skeleton.model.ProfileStep2.Data;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.CommonParams;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;
import com.skeleton.util.Log;

import java.util.ArrayList;

/**
 * Created by abc on 23/5/17.
 */

public class CompleteProfileStep2Fragment extends BaseFragment {

    private RecyclerViewAdapter adapter;
    private RecyclerView rvInterest;
    private Data interestData;
    private View vFirst, vSecond, vThird, vFourth, vFifth;
    private Button btnSave;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complete_step2, container, false);
        rvInterest = (RecyclerView) view.findViewById(R.id.rvInterest);
        vFirst = view.findViewById(R.id.vFirst);
        vSecond = view.findViewById(R.id.vSecond);
        vThird = view.findViewById(R.id.vThird);
        vFourth = view.findViewById(R.id.vFourth);
        vFifth = view.findViewById(R.id.vFifth);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        getCategories();
        return view;
    }

    @Override
    public void onClick(final View v) {
        super.onClick(v);
        final int id = v.getId();
        switch (id) {
            case R.id.btnSave:
                final ArrayList<String> categoryIds = adapter.getCategoryIds();
                final String array = new Gson().toJson(categoryIds);
                Log.d("TAG", array);
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                getActivity().startActivityForResult(intent, REQ_HOME);
                CommonParams params = new CommonParams.Builder()
                        .add(KEY_INTEREST_CAT, array)
                        .add(KEY_STEP_SECOND, VALUE_TRUE).build();
                RestClient.getApiInterface().selectCategory("bearer " + CommonData.getAccessToken(), params.getMap())
                        .enqueue(new ResponseResolver<CommanResponse>(getContext(), true) {
                            @Override
                            public void success(final CommanResponse sharedResponse) {

                            }

                            @Override
                            public void failure(final APIError error) {

                            }
                        });
                break;
            default:
                break;
        }
    }

    /**
     * Get Categories From Server
     */
    private void getCategories() {
        RestClient.getApiInterface().getCategories("bearer " + CommonData.getAccessToken(), KEY_INTEREST)
                .enqueue(new ResponseResolver<InterestCategories>(getContext(), true) {
                    @Override
                    public void success(final InterestCategories interestCategories) {
                    }

                    @Override
                    public void failure(final APIError error) {

                    }

                });
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * @param limit num of views to be filled
     */
    public void colorViews(final int limit) {
        setViewBg(limit, vFirst, vSecond, vThird, vFourth, vFifth);
    }

    /**
     * @param limit num of views to be filled
     * @param view  views to be filled
     */
    private void setViewBg(final int limit, final View... view) {
        for (int i = 0; i < view.length; i++) {
            if (i < limit) {
                view[i].setBackgroundResource(R.color.colorPrimary);
            } else {
                view[i].setBackgroundResource(R.color.gray_light);
            }
        }
    }

}