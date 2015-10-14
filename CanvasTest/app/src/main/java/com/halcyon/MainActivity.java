package com.halcyon;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.halcyon.adapter.FragmentAdapter;
import com.halcyon.fragment.BasicCanvasFragment;
import com.rey.material.widget.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager mViewPager;
    TabPageIndicator mIndicator;
    List<Fragment> mFragmentList = new ArrayList<>();
    List<String> mLabelList = new ArrayList<>();
    FragmentAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mIndicator = getViewById(R.id.indicator);
        mViewPager = getViewById(R.id.viewPager);

        init();
    }

    private void init() {
        mFragmentList.add(new BasicCanvasFragment());


        mViewPager.setAdapter(mAdapter = new FragmentAdapter(getSupportFragmentManager(),mFragmentList));
        mIndicator.setViewPager(mViewPager);
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
        return (VT) this.findViewById(id);
    }

}
