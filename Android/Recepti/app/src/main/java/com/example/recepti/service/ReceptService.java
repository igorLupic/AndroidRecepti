package com.example.recepti.service;


import com.example.recepti.model.Recept;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ReceptService {

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @GET(ServiceUtils.RECEPTI)
    Call<List<Recept>> getRecepte();

    @GET(ServiceUtils.RECEPTID)
    Call<Recept> getRecept(@Path("id") int id);

    @DELETE(ServiceUtils.RECEPTDELETE)
    Call<Recept> deleteRecept(@Path("id") int id);

    @POST(ServiceUtils.RECEPTADD)
    Call<Recept> addRecept(@Body Recept recept);

    @PUT(ServiceUtils.RECEPTUPDATE)
    Call<Recept> updateRecept(@Body Recept recept);

    @GET(ServiceUtils.SORTRECEPTIASC)
    Call<List<Recept>> sortRecepteAsc();

    @GET(ServiceUtils.SORTRECEPTIDESC)
    Call<List<Recept>> sortRecepteDesc();




}
