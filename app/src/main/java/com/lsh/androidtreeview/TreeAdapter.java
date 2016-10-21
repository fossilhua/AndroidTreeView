package com.lsh.androidtreeview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lsh.lshrecyclerviewadapter.BaseAdapter;
import com.lsh.lshrecyclerviewadapter.BaseViewHolder;

import java.util.List;

/**
 * Created by hua on 2016/10/20.
 */

public class TreeAdapter extends BaseAdapter<TreeBean<String>> {
    private int paddingSize = 100;

    public void setPaddingSize(int paddingSize) {
        this.paddingSize = paddingSize;
    }

    public TreeAdapter(Context mContext, List<TreeBean<String>> list) {
        //
        super(mContext, list, R.layout.item_list);
    }

    @Override
    public void onBindData(BaseViewHolder holder, List<TreeBean<String>> data, int position) {
        TextView mTitle = holder.retrieveView(R.id.title);
        ImageView mImg = holder.retrieveView(R.id.iv_img);
        TreeBean<String> bean = data.get(position);
        mTitle.setText(bean.getData());
        holder.itemView.setPadding(bean.getLevel() * paddingSize, 0, 0, 0);
        if(bean.isExpand()){
            if(bean.getNextNodes()==null){
                mImg.setVisibility(View.INVISIBLE);
            }else{
                mImg.setVisibility(View.VISIBLE);
            }
        }
        if(bean.getNextNodes()==null){
            mImg.setVisibility(View.INVISIBLE);
        }else{
            mImg.setVisibility(View.VISIBLE);
            if(bean.isExpand()){
                mImg.setImageResource(R.drawable.arrow_down);
            }else{
                mImg.setImageResource(R.drawable.arrow_right);
            }
        }

    }
}
