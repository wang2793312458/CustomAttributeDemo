package com.example.k.customattributedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap<>();
                map.put("id","4");
                HttpFactory.getInstance()
                        .getLogin(map)
                        .compose(HttpObserver.<LoginData>schedulers())
                        .subscribe(new HttpObserver<>(new HttpSuccessListener<LoginData>() {
                            @Override
                            public void success(LoginData var1) {
                                Log.d(TAG, "success: "+var1.message);
                            }
                        }, new HttpErrorListener() {
                            @Override
                            public void error(Throwable var1) {
                                Log.d(TAG, "error: "+var1.getMessage());
                            }
                        }));
            }
        });
String path="";
//        HttpFactory.getInstance().upload(UploadUtils.getMultipartBody(path))
//                .compose(HttpObserver.<List<String>>schedulers())
//                .subscribe(new UploadSubscriber<List<String>>() {
//                    @Override
//                    public void onNext(List<String> list) {
//                        listener.success(list);
//                    }
//                });



    }
//    private void addEvaluate() {
//        if (mList.size() < 1) {
//            addCommentEmoji("");
//        } else {
//            for (ImageItem imageItem : mList) {
//                list.add(imageItem.path);
//            }
//            HttpFactory.creatHttp(API.class, APIConstants.API_UPLOAD_IMAGE).uploads(UploadUtils.getMultipartBodysForPath(list))
//                    .compose(HttpSubscriber.<List<String>>applySchedulers())
//                    .subscribe(new UploadSubscriber<List<String>>(this) {
//                        @Override
//                        public void onNext(List<String> list) {
//                            StringBuffer buf = new StringBuffer();
//                            for (int i = 0; i < list.size(); i++) {
//                                buf.append(list.get(i) + ",");
//                            }
//                            if (buf.length() > 0) {
//                                buf.deleteCharAt(buf.length() - 1);
//                                mOrderpath = buf.toString();
//                            }
//                            addCommentEmoji(mOrderpath);
//                        }
//                    });
//        }
//    }
}
