package com.example.k.customattributedemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.reactivestreams.Subscriber;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.observers.DisposableObserver;

public abstract class UploadSubscriber<T> extends DisposableObserver<T> {
    private Context mContext = null;
    private ProgressDialog mDialog = null;
    private static final String TAG = "Upload";

    public UploadSubscriber(Context context) {
        if (context == null) {
            throw new NullPointerException("Context not null");
        } else {
            WeakReference<Context> wf = new WeakReference(context);
            this.mContext = (Context)wf.get();
        }
    }

    public UploadSubscriber(ProgressDialog dialog) {
        if (dialog == null) {
            throw new NullPointerException("ProgressDialog not null");
        } else {
            this.mDialog = dialog;
        }
    }

    public void onStart() {
    }

    public void onCompleted() {


    }

    public void onError(Throwable e) {


        if (e instanceof SocketTimeoutException) {
//            Toast.makeText(this.mContext, string.net_request_time_out, 0).show();
        } else if (e instanceof ConnectException) {
//            Toast.makeText(this.mContext, string.net_error_tips, 0).show();
        } else {
            Toast.makeText(this.mContext, e.getMessage(), 0).show();
            Log.i("Upload", e.getMessage(), e);
        }

    }

    public void onNext(T t) {
    }
}
