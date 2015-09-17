package com.halcyon.common.lh;

public interface MultiItemRTypeSupport<T> {
    int getLayoutId(int type);

    int getItemViewType(int position, T t);
}