package com.example.equinoxlabtest.network;

import com.example.equinoxlabtest.model.BaseResponseArrayEntity;
import com.example.equinoxlabtest.model.Employee;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestService
{
    @GET("getManager")
    Call<BaseResponseArrayEntity<Employee>> getAllEmployee();
}


