package com.halcyon.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * ©2015-2016 kcoin.inc.All Rights Reserved
 *
 * @author: L
 * Description:
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    protected String TAG;
    protected View mContentView;
    protected Callback mCallback;

    public interface Callback{
        void showPage(int position);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        TAG = this.getClass().getSimpleName();
        if(activity instanceof Callback){
            mCallback = (Callback) activity;
        }else{
        }
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            onUserVisible();
        }
    }

    protected void setContentView(@LayoutRes int layoutResID) {
        mContentView = LayoutInflater.from(getActivity()).inflate(layoutResID, null);
    }

    /**
     * 当fragment对用户可见时，会调用该方法，可在该方法中懒加载网络数据
     */
    protected void onUserVisible() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 避免多次从xml中加载布局文件
        if (mContentView == null) {
            initView(savedInstanceState);
            setListener();
            processLogic(savedInstanceState);
            initData();
        } else {
            ViewGroup parent = (ViewGroup) mContentView.getParent();
            if (parent != null) {
                parent.removeView(mContentView);
            }
        }
        return mContentView;
    }

    /**
     * 初始化数据
     */
    public void initData() {
    }

    /**
     * 刷新数据
     */
    public void refreshData() {
    }


    /**
     * 初始化View控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 给View控件添加事件监听器
     */
    protected void setListener() {

    }

    /**
     * 处理业务逻辑，状态恢复等操作
     *
     * @param savedInstanceState
     */
    protected void processLogic(Bundle savedInstanceState) {
    }


    @Override
    public void onClick(View v) {

    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    @SuppressWarnings("unchecked")
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) mContentView.findViewById(id);
    }
}
