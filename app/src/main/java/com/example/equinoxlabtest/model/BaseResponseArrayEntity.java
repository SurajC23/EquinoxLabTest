package com.example.equinoxlabtest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BaseResponseArrayEntity<T> {
    @SerializedName("CODE")
    String code;

    @SerializedName("MESSAGE")
    String message;

    @SerializedName("DATA")
    ArrayList<T> data;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public ArrayList<T> getData()
    {
        return data;
    }

    public void setData(ArrayList<T> data)
    {
        this.data = data;
    }
}
