package com.example.virtualclinic.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;



public class RetrofitClient {
    public static Retrofit retrofit;
    private static RetrofitClient instance = null;
    private Api myApi;

    private RetrofitClient()
    {
        if(retrofit==null){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create()).build();
        }
    }
    public static synchronized RetrofitClient getInstance()
    {
        if(instance==null)
        {
            instance= new RetrofitClient();
        }
        return instance;
    }
    public Api getMyApi()
    {

        return retrofit.create(Api.class);
    }



}
