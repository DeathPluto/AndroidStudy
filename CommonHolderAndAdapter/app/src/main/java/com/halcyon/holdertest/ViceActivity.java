package com.halcyon.holdertest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * ©2015-2016 kcoin.inc.All Rights Reserved
 *
 * @author: L
 * Description:
 */
public class ViceActivity extends AppCompatActivity {
    private ListView mListView;
    private List<String> mDatas;
    private MultiAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) this.findViewById(R.id.listView);
        initData();
        mAdapter = new MultiAdapter(this, mDatas);
        mListView.setAdapter(mAdapter);

    }
    private void initData(){
        mDatas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mDatas.add("数据"+i);
        }

    }


    private class MultiAdapter extends MultiItemCommonAdapter<String>{

        public MultiAdapter(Context context, List<String> datas) {
            super(context, datas, new MultiItemTypeSupport<String>() {
                @Override
                public int getLayoutId(int position, String s) {
                    if(position % 5 == 0){
                        return R.layout.item_listview_other;
                    }
                    return R.layout.item_listview;
                }

                @Override
                public int getViewTypeCount() {
                    return 2;
                }

                @Override
                public int getItemViewType(int position, String s) {
                    if(position % 5 == 0){
                        return 1;
                    }
                    return 0;
                }
            });
        }

        @Override
        public void convert(ViewHolder holder, String s) {
            switch (holder.getLayoutId()){
                case R.layout.item_listview:
                    holder.setText(R.id.tv_content,s);
                    break;
                case R.layout.item_listview_other:
                    holder.setText(R.id.tv_content,"标题");
                    break;
            }
        }
    }
}
