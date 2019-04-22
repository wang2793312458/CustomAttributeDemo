package com.example.k.customattributedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;

import java.util.HashMap;
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

    }
}
