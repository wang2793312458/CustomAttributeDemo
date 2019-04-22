package com.example.k.customattributedemo;

public class HttpResponseData<T> {
    public String status;
    public String message;
    private T data;

    public HttpResponseData() {
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public String toString() {
        return "\n   [code: " + this.status + "\n     msg: " + this.message + "\n    data: " + this.data + "\n";
    }
}
