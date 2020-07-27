package com.example.recepti.service;

import com.example.recepti.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface UserService {
    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })

    @GET(ServiceUtils.LOGIN)
    Call<User> login(@Path("username") String username, @Path("password") String password);

    @GET(ServiceUtils.USERNAME)
    Call<User> getByUsername(@Path("username") String username);

    @GET(ServiceUtils.USERS)
    Call<List<User>> getUsers();

}
