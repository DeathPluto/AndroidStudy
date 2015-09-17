package com.halcyon.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.halcyon.common.lh.MultiItemRAdapter;
import com.halcyon.common.lh.MultiItemRTypeSupport;
import com.halcyon.common.lh.RHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_SPECIAL = 1;
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
        MultiItemRTypeSupport<String> support = new MultiItemRTypeSupport<String>() {
            @Override
            public int getLayoutId(int type) {
                if(type == TYPE_NORMAL){
                    return R.layout.item_listview;
                }
                return R.layout.item_listview_other;
            }

            @Override
            public int getItemViewType(int position, String s) {
                if(position % 5 == 0){
                    return TYPE_SPECIAL;
                }
                return TYPE_NORMAL;
            }
        };
        MultiItemRAdapter<String> adapter = new MultiItemRAdapter<String>(mDatas,support) {
            @Override
            public void convert(RHolder holder, String item, int position) {
                switch (getItemViewType(position)){
                    case TYPE_NORMAL:
                        holder.setText(R.id.tv_content,item);
                        break;
                    case TYPE_SPECIAL:
                        holder.setText(R.id.tv_content,"标题");
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getApplication(),ViceActivity.class));
                            }
                        });
                }

            }
        };

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
    }


}
