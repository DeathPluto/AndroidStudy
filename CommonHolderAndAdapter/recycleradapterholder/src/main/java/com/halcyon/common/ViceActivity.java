package com.halcyon.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.halcyon.common.lh.RHolder;
import com.halcyon.common.lh.SimpleRAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * ©2015-2016 kcoin.inc.All Rights Reserved
 *
 * @author: L
 * Description:
 */
public class ViceActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mDatas.add("数据"+i);
        }
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new SimpleRAdapter<String>(R.layout.item_listview,mDatas) {
            @Override
            public void convert(RHolder holder, String o, int position) {
                holder.setText(R.id.tv_content,o);
            }
        });
    }
}
