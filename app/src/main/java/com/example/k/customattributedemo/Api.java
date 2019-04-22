package com.example.k.customattributedemo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    String API_IP_HOST_TEST = "http://ysweb.diyunkeji.com/index/";

    //登录
    @POST("common/getuser")
    Observable<LoginData> getLogin(@Body Map<String, String> map); //登录

    @POST("common/sendmsgcode")  //phone
    Observable<LoginData> getCode(@Body Map<String, String> map);
}
