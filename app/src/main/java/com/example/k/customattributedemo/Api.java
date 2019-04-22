package com.example.k.customattributedemo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {
    String API_IP_HOST_TEST = "http://ysweb.diyunkeji.com/index/";

    //登录
    @POST("common/getuser")
    Observable<LoginData> getLogin(@Body Map<String, String> map); //登录

    @POST("common/sendmsgcode")  //phone
    Observable<LoginData> getCode(@Body Map<String, String> map);

    //上传图片
    @Multipart
    @POST("file/uploadImages")
    Observable<List<String>> upload(@Part MultipartBody.Part file);

    //上传文件
    @Multipart
    @POST("file/uploadImages")
    Observable<List<String>> uploads(@Part List<MultipartBody.Part> files);

}
