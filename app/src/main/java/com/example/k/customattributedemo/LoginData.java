package com.example.k.customattributedemo;

import java.io.Serializable;

public class LoginData implements Serializable {
    public String status;
    public String message;
    public infoBean info;
    public class infoBean{
        public String id;
        public String username;
    }
}
