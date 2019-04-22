package com.example.k.customattributedemo;

import android.content.Context;

import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HttpObserver<T> extends DisposableObserver<T> {
    private static final String TAG = "Request";
    private static Context sContext = null;
    private HttpSuccessListener<T> mSuccessListener;
    private HttpErrorListener mErrorListener;

    public HttpObserver() {
    }

    public HttpObserver(HttpSuccessListener<T> successListener) {
        if (successListener == null) {
            throw new NullPointerException("HttpSuccessListener not null");
        } else {
            this.mSuccessListener = successListener;
        }
    }

    public HttpObserver(HttpSuccessListener<T> successListener, HttpErrorListener errorListener) {
        if (successListener == null) {
            throw new NullPointerException("HttpSuccessListener not null");
        } else if (errorListener == null) {
            throw new NullPointerException("HttpErrorListener not null");
        } else {
            this.mSuccessListener = successListener;
            this.mErrorListener = errorListener;
        }
    }

    public void onError(Throwable e) {

        if (this.mErrorListener != null) {
            this.mErrorListener.error(e);
        } else if (e instanceof SocketTimeoutException) {
            throw new HttpException("请求超时");
        } else if (e instanceof ConnectException) {
            throw new HttpException("请求服务器异常");
        } else {
            Logger.e(e, "Exception-Info", new Object[]{e.getMessage()});
        }
    }

    public void onComplete() {
    }

    public void onNext(T t) {
        this.mSuccessListener.success(t);
    }


    public static <T> ObservableTransformer<T, T> schedulers() {
        return new ObservableTransformer<T, T>() {
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * @deprecated
     */
    @Deprecated
    public HttpObserver(Context context) {
        if (context == null) {
            throw new NullPointerException("Context not null");
        } else {
            WeakReference<Context> wc = new WeakReference(context);
            sContext = (Context) wc.get();
        }
    }
}
