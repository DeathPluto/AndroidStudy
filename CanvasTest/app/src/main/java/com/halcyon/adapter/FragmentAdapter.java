package com.halcyon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * ©2015-2016 kcoin.inc.All Rights Reserved
 *
 * @author: L
 * Description:
 */
public class FragmentAdapter extends FragmentStatePagerAdapter{
    List<Fragment> mFragmentList;
    List<String> mLabelList;

    public FragmentAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
    }

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> labelList) {
        super(fm);
        mFragmentList = fragmentList;
        mLabelList = labelList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mLabelList == null?mFragmentList.get(position).getClass().getSimpleName():mLabelList.get(position);
    }
}
