package com.halcyon.common.lh;

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
public abstract class MultiItemRAdapter<T> extends RecyclerView.Adapter<RHolder> {
    protected MultiItemRTypeSupport<T> mSupport;
    protected List<T> mData;

    public MultiItemRAdapter(List<T> data, MultiItemRTypeSupport<T> support) {
        this.mSupport = support;
        this.mData = data;
    }

    @Override
    public RHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = -1;
        if (mSupport != null) {
            layoutId = mSupport.getLayoutId(viewType);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new RHolder(view,parent.getContext());
    }

    @Override
    public void onBindViewHolder(RHolder holder, int position) {
        convert(holder, getItem(position), position);
    }

    public abstract void convert(RHolder holder, T item, int position);


    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getItemViewType(int position) {
        return mSupport == null ? super.getItemViewType(position) : mSupport.getItemViewType(position, getItem(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
