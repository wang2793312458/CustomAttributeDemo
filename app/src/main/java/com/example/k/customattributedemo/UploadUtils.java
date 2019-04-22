package com.example.k.customattributedemo;

import android.text.TextUtils;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Part;
import okhttp3.RequestBody;

public class UploadUtils {
    private static final String FILE_NOT_NULL = "文件不能为空";
    private static final String FILE_PATH_NOT_NULL = "文件路径不能为空";

    public UploadUtils() {
    }

    public static MultipartBody.Part getMultipartBody(String path) {
        if (TextUtils.isEmpty(path)) {
            throw new NullPointerException("文件路径不能为空");
        } else {
            File file = new File(path);
            if (file.exists()) {
                RequestBody requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                return Part.createFormData("file", file.getName(), requestFile);
            } else {
                throw new NullPointerException("文件不能为空");
            }
        }
    }

    public static MultipartBody.Part getMultipartBody(File file) {
        if (file.exists()) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            return Part.createFormData("file", file.getName(), requestFile);
        } else {
            throw new NullPointerException("文件不能为空");
        }
    }

    public static List<MultipartBody.Part> getMultipartBodysForFile(List<File> files) {
        if (files.isEmpty()) {
            throw new NullPointerException("文件不能为空");
        } else {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            Iterator var2 = files.iterator();

            while (var2.hasNext()) {
                File file = (File) var2.next();
                if (!file.exists()) {
                    throw new NullPointerException("文件不能为空");
                }

                RequestBody requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                builder.addFormDataPart("file", file.getName(), requestFile);
            }

            return builder.build().parts();
        }
    }

    public static List<MultipartBody.Part> getMultipartBodysForPath(List<String> paths) {
        if (paths.isEmpty()) {
            throw new NullPointerException("文件路径不能为空");
        } else {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            Iterator var2 = paths.iterator();

            while (var2.hasNext()) {
                String path = (String) var2.next();
                File file = new File(path);
                if (!file.exists()) {
                    throw new NullPointerException("文件不能为空");
                }

                RequestBody requestFile = RequestBody.create(MediaType.parse("application/octet-stream"), file);
                builder.addFormDataPart("file", file.getName(), requestFile);
            }

            return builder.build().parts();
        }
    }
}
