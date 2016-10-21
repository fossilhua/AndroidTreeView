package com.lsh.androidtreeview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by hua on 2016/10/20.
 */

public class BaseTreeAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TreeBean<T>> mDataList;
    private Context mContext;

    public BaseTreeAdapter(List<TreeBean<T>> mDataList, Context mContext) {
        this.mDataList = mDataList;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_foot, parent, false);
        return new TreeHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TreeHolder extends RecyclerView.ViewHolder {

        public TreeHolder(View itemView) {
            super(itemView);
        }
    }
}
