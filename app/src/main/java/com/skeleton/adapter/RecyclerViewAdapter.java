package com.skeleton.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.skeleton.R;
import com.skeleton.fragment.CompleteProfileStep2Fragment;
import com.skeleton.model.ProfileStep2.Categories;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abc on 23/5/17.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static int sCounter = 0;
    private Context mContext;
    private List<Categories> categories;
    private CompleteProfileStep2Fragment profileFragment;
    private ArrayList<String> strings = new ArrayList<>();


    /**
     * @param mContext   context of the calling fragment
     * @param categories list of the categories in Interest Data model
     * @param fragment   object of the second Fragment
     */
    public RecyclerViewAdapter(final Context mContext, final List<Categories> categories,
                               final CompleteProfileStep2Fragment fragment) {
        this.mContext = mContext;
        this.categories = categories;
        this.profileFragment = fragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.complete_step_2_recycler_item_view, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final GridViewHolder viewHolder = (GridViewHolder) holder;
        final Categories list = categories.get(position);
        viewHolder.tvInterest.setText(list.getName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    /**
     * @return Json array of category ids
     */
    public ArrayList<String> getCategoryIds() {
        return strings;
    }

    /**
     * Custom View Holder class for Adapter
     */
    private final class GridViewHolder extends RecyclerView.ViewHolder {
        private TextView tvInterest;

        /**
         * @param itemView casted itemView into GridView view object
         */
        private GridViewHolder(final View itemView) {
            super(itemView);
            tvInterest = (TextView) itemView.findViewById(R.id.tvInterest);

        }
    }

}
