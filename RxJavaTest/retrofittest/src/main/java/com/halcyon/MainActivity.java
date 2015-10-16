package com.halcyon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.halcyon.API.KcoinService;
import com.halcyon.API.Urls;
import com.halcyon.model.request.TradeListRequest;
import com.halcyon.model.response.TradeListResponse;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Urls.BASE_URL).build();
                KcoinService kcoinService = adapter.create(KcoinService.class);
                kcoinService.tradeList(new TradeListRequest(), new Callback<TradeListResponse>() {
                    @Override
                    public void success(TradeListResponse tradeListResponse, Response response) {
                        Log.e("success",tradeListResponse.content.size()+"");
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e("failure",error.getMessage()+"");
                    }
                });
            }
        });
    }


}
