package com.halcyon.common.lh;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * ©2015-2016 kcoin.inc.All Rights Reserved
 *
 * @author: L
 * Description:
 */
public abstract class SimpleRAdapter<T> extends RecyclerView.Adapter<RHolder> {
    private int mLayoutId;
    private List<T> mData;

    public SimpleRAdapter(@LayoutRes int layoutId, List<T> data) {
        this.mLayoutId = layoutId;
        this.mData = data;
    }

    @Override
    public RHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new RHolder(view,parent.getContext());
    }

    @Override
    public void onBindViewHolder(RHolder holder, int position) {
        convert(holder, getItem(position), position);
    }

    public abstract void convert(RHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public T getItem(int position) {
        return position >= mData.size() ? null : mData.get(position);
    }
}
