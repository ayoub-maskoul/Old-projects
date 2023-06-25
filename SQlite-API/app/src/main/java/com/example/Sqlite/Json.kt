package com.example.Sqlite

import retrofit2.Call
import retrofit2.http.GET
import java.util.ArrayList


interface JsonPlaceholderApi {
    @GET("taches")
    fun getAllTache() : Call<ArrayList<Taches>>



}