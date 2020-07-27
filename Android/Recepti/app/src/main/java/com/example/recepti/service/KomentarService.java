package com.example.recepti.service;

import com.example.recepti.model.Komentar;
import com.example.recepti.model.KomentarDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface KomentarService {

    @Headers({
            "User-Agent: Mobile-Android",
            "Content-Type:application/json"
    })
    @GET(ServiceUtils.KOMENTARI)
    Call<List<Komentar>> getKomentare();

    @GET(ServiceUtils.KOMENTARID)
    Call<Komentar> getKomentar(@Path("id") int id);

    @DELETE(ServiceUtils.KOMENTARDELETE)
    Call<Komentar> deleteKomentar(@Path("id") int id);

    @POST(ServiceUtils.KOMENTARADD)
    Call<Komentar> addKomentar(@Body Komentar komentar);

    @POST(ServiceUtils.KOMENTARADD)
    Call<Komentar> addKomentarDTO(@Body KomentarDTO komentar);


    @GET(ServiceUtils.KOMENTARRECEPT)
    Call<List<Komentar>>getKomentarRecept(@Path("id") int id);


}
