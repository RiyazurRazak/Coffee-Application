package com.example.coffeeapp.data.remote

import com.example.coffeeapp.data.model.remote.BeveragesResult
import com.example.coffeeapp.data.model.remote.Photos
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RestaurantsService {

    @GET("search")
    fun getBeverageBasedOnName(
        @Query("query") query: String = "",
        @Header("Authorization") authHeader: String = "6Hcz93te8VAQWIwPR0lq4M5lfbgI4oh7P67hCFigumvHXpWAKNTdl5hY"
    ): Call<BeveragesResult>


    @GET("photos/{id}")
     fun getSingleBeverage(
        @Path("id") id: String = "",
        @Header("Authorization") authHeader: String = "6Hcz93te8VAQWIwPR0lq4M5lfbgI4oh7P67hCFigumvHXpWAKNTdl5hY"

    ): Call<Photos>
}