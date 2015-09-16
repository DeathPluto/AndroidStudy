package com.halcyon.holdertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mDatas;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) this.findViewById(R.id.listView);
        initData();
        mAdapter = new MyAdapter(mDatas, R.layout.item_listview);
        mListView.setAdapter(mAdapter);
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mDatas.add("数据" + i);
        }

    }


    private class MyAdapter extends CommonAdapter<String> {

        public MyAdapter(List<String> datas, int layoutId) {
            super(datas, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, final String s) {
            holder.setText(R.id.tv_content, s);
            holder.setOnClickListener(R.id.tv_content, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ViceActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

}
