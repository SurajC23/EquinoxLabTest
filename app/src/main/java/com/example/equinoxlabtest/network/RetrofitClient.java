package com.example.equinoxlabtest.network;

import android.Manifest;
import androidx.annotation.RequiresPermission;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient
{
    public static volatile Retrofit sRetrofit = null;
    public static APIRequestService apiRequestService;


    //default constructor
    public RetrofitClient()
    {
    }

    public static APIRequestService getApiService()
    {
        return initRetrofitService();
    }

    private static APIRequestService initRetrofitService()
    {
        if (apiRequestService == null) {
            synchronized (RetrofitClient.class)
            {
                if (apiRequestService == null)
                {
                    apiRequestService = getRetrofit().create(APIRequestService.class);
                }
            }
        }

        return apiRequestService;
    }

    @RequiresPermission(Manifest.permission.INTERNET)
    private synchronized static Retrofit getRetrofit()
    {
        if (sRetrofit == null)
        {
            synchronized (RetrofitClient.class)
            {
                if (sRetrofit == null)
                {
                    sRetrofit = new Retrofit.Builder()
                            .baseUrl(Url.URL_DATA)
                            .client(createClient())
                            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                                    .setLenient()
                                    .create()))
                            .build();
                }
            }
        }
        return sRetrofit;
    }

    private static OkHttpClient createClient()
    {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(createInterceptor()).build();
    }

    private static Interceptor createInterceptor()
    {
        return new Interceptor()
        {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Request request = chain.request();
                if (request.method().equals("POST"))
                {
                }
                Response response = chain.proceed(request);
                return response.newBuilder()
                        .header("Cache-Control", "public, max-age=86400")
                        .header("Pragma", "public")
                        .build();
            }
        };
    }
}