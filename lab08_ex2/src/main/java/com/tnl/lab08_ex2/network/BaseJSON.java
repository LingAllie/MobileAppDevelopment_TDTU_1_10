package com.tnl.lab08_ex2.network;

import com.google.gson.annotations.SerializedName;

public class BaseJSON<T> {

    @SerializedName("code")
    public int code;

    @SerializedName("message")
    public String message;

    @SerializedName("data")
    public T data;

}
