package com.halcyon.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.halcyon.R;
import com.halcyon.base.BaseFragment;
import com.halcyon.canvas.CanvasView;

import java.util.Random;

/**
 * ©2015-2016 kcoin.inc.All Rights Reserved
 *
 * @author: L
 * Description:
 */
public class CanvasFragment1 extends BaseFragment {
    LinearLayout mLinearLayout;
    private CanvasView mCanvasView;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_canvas1);

        mLinearLayout = getViewById(R.id.ll);
        getViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                mCanvasView.setDrawType(r.nextInt(mCanvasView.getTypeCount()));
                mCanvasView.invalidate();
            }
        });
        init();
    }

    private void init() {
        mCanvasView = new CanvasView(getActivity());
        addView(mCanvasView);
    }

    private void addView(View view){
        mLinearLayout.addView(view);
    }
}
