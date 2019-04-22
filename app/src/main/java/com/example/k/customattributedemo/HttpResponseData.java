package com.example.k.customattributedemo;

public class HttpResponseData<T> {
    private int state;
    private String msg;
    private T data;

    public HttpResponseData() {
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public String toString() {
        return "\n   [code: " + this.state + "\n     msg: " + this.msg + "\n    data: " + this.data + "\n";
    }
}
