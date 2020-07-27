package com.example.recepti.service;

import android.graphics.Bitmap;

import com.example.recepti.util.ImageSerialization;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceUtils {

    public static final String SERVICE_API_PATH = "http://192.168.0.13:8080/api/";
    public static final String LOGIN = "users/{username}/{password}";
    public static final String USERNAME = "users/{username}";
    public static final String USERS = "users/all";
    public static final String KOMENTARI = "komentari/all";
    public static final String KOMENTARID  = "komentari/{id}";
    public static final String KOMENTARDELETE = "komentari/delete/{id}";
    public static final String KOMENTARADD = "komentari/add";
    public static final String RECEPTI = "recepti/all";
    public static final String RECEPTID  = "recepti/{id}";
    public static final String RECEPTDELETE = "recepti/delete/{id}";
    public static final String RECEPTADD = "recepti/add";
    public static final String RECEPTUPDATE = "recepti/edit";
    public static final String KOMENTARRECEPT = "komentari//getFor/{id}";
    public static final String SORTRECEPTIASC = "recepti/orderTezinaAsc";
    public static final String SORTRECEPTIDESC = "recepti/orderTezinaDesc";

    public static OkHttpClient test(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build();
        return client;
    }


    static Gson gson = new GsonBuilder().registerTypeAdapter(Bitmap.class, ImageSerialization
            .getBitmapTypeAdapter())
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create();

    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SERVICE_API_PATH)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(test())
            .build();

    public static UserService userService = retrofit.create(UserService.class);
    public static KomentarService komentarService = retrofit.create(KomentarService.class);
    public static ReceptService receptService = retrofit.create(ReceptService.class);

}
