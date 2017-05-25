package com.skeleton.util.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.skeleton.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abc on 22/5/17.
 */

public class CustomDialogFragment extends DialogFragment implements AdapterView.OnItemClickListener {
    private ListView listView;
    private TextView tvDialogTitle;
    private ArrayList<String> mItems;
    private ItemClicked mCallback;

    /**
     * Empty Constructor
     */
    public CustomDialogFragment() {
    }

    /**
     * @param title    title of the dialog fragment
     * @param mItems   array of the items to be inflated in list view
     * @param callback callback
     * @return object of the fragment
     */
    public static CustomDialogFragment newInstance(final String title, final List<String> mItems,
                                                   final ItemClicked callback) {
        final CustomDialogFragment fragment = new CustomDialogFragment();
        fragment.setListener(callback);
        final Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putStringArrayList("list", (ArrayList<String>) mItems);
        fragment.setArguments(bundle);
        return fragment;
    }

    /**
     * @param callback value of interface object
     */
    public void setListener(final ItemClicked callback) {
        this.mCallback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.frag_dialog, container, false);
        mItems = getArguments().getStringArrayList("list");
        listView = (ListView) view.findViewById(R.id.lvDialog);
        tvDialogTitle = (TextView) view.findViewById(R.id.tvdialog);
        tvDialogTitle.setText(getArguments().getString("title"));
        return view;
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                R.layout.dialog_items_rowlayout, mItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
        dismiss();
        setValues(mItems.get(position), parent.getId());

    }


    /**
     * Sets the value of the selected item and id of the edit text from which value is selected
     *
     * @param mValue value to be sent to other fragment
     * @param viewId id of the view from which the data is sent
     */
    public void setValues(final String mValue, final int viewId) {
        mCallback.sendText(mValue, viewId);
    }


    /**
     * Interface used for communication between fragments
     */
    public interface ItemClicked {
        /**
         * @param text   value to be sent to other fragment
         * @param viewId id of the view from which the data is sent
         */
        void sendText(final String text, final int viewId);
    }


}