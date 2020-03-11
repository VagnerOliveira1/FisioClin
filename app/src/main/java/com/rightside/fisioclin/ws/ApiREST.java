package com.rightside.fisioclin.ws;


import com.rightside.fisioclin.models.Endereco;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiREST {


    @Headers({
            "Content-Type: application/json",
            "Accept: application/json"
    })
    @GET("cep/{numeroCep}")
    Call<Endereco> endereco(@Path("numeroCep") int numeroCep);



}
