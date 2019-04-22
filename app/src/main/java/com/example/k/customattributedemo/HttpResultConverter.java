package com.example.k.customattributedemo;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpResultConverter extends Factory {
    private static final String TAG = "HttpResultConverter";
    private final Gson mGson = new Gson();

    public static HttpResultConverter create() {
        return new HttpResultConverter();
    }

    private HttpResultConverter() {
    }

    public Converter<ResponseBody, ?> responseBodyConverter(Type mType, Annotation[] annotations, Retrofit retrofit) {
        return new BaseResponseBodyConverter(mType);
    }

    public Converter<?, RequestBody> requestBodyConverter(Type mType, Annotation[] parameterAnnotations, Annotation[] methodAnnotations,
                                                          Retrofit retrofit) {
        return GsonConverterFactory.create().requestBodyConverter(mType, parameterAnnotations, methodAnnotations, retrofit);
    }

    private class BaseResponseBodyConverter<T> implements Converter<ResponseBody, T> {
        private Type mType;
        private static final String SERVICE_ERROR = "请求服务器异常";
        private static final int SERVICE_STATE_SUCCESS = 1;
        private static final int REQUEST_SUCCESS = 40000;
        private static final int TOKEN_ERROR = 30000;

        public BaseResponseBodyConverter(Type mType) {
            this.mType = mType;
        }

        @Override
        public T convert(ResponseBody response) {
            Object var6;
            try {
                String strResponse = response.string();
                Log.d(TAG, "convert: "+strResponse);
                if (TextUtils.isEmpty(strResponse)) {
                    Log.d(TAG, "convert: 1111");
                    throw new HttpException("请求服务器异常");
                }else {
                    Log.d(TAG, "convert: 222");
                  var6=strResponse;
                }
//                HttpResponseData httpResponse = mGson.fromJson(strResponse, HttpResponseData.class);
//                int state = httpResponse.getState();
//                if (state != REQUEST_SUCCESS) {
//                    throw new HttpException(httpResponse.getMsg());
//                }
//                var6 = mGson.fromJson(mGson.toJson(httpResponse.getData()), this.mType);
            } catch (IOException var10) {
                throw new HttpException(var10.getMessage());
            } finally {
                response.close();
            }
            return (T) var6;
        }
    }
}
