package com.halcyon.API;

/**
 * ©2015-2016 kcoin.inc.All Rights Reserved
 *
 * @author: L
 * Description:
 */
public interface Urls {


    //测试的地址
    String BASE_URL = "http://192.168.1.123:4466/Handlers";

    /**
     * 获得交易中心下拉列表
     */
    String URL_GET_TRADELIST =  "/MobileAPIHandler.ashx?type=gettradelist";//中心
}
