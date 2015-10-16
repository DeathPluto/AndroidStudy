package com.halcyon.API;

import com.halcyon.model.request.TradeListRequest;
import com.halcyon.model.response.TradeListResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * ©2015-2016 kcoin.inc.All Rights Reserved
 *
 * @author: L
 * Description:
 */
public interface KcoinService {

    @POST(Urls.URL_GET_TRADELIST)
    void tradeList(@Body TradeListRequest request, Callback<TradeListResponse> cb);
}
