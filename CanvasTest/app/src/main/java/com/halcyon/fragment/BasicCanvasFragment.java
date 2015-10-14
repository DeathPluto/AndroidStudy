package com.halcyon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.halcyon.R;
import com.halcyon.widget.CanvasView1;

/**
 * ©2015-2016 kcoin.inc.All Rights Reserved
 *
 * @author: L
 * Description:
 */
public class BasicCanvasFragment extends Fragment {
    View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_basic_canvas, container, false);
        init();
        return mRootView;
    }

    private void init() {
        LinearLayout layout = (LinearLayout) mRootView.findViewById(R.id.ll);
        final CanvasView1 view = new CanvasView1(getActivity());
        view.setMinimumHeight(500);
        view.setMinimumWidth(300);
        //通知view组件重绘
        view.invalidate();
        layout.addView(view);
        mRootView.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
