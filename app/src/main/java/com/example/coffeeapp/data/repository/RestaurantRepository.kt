package com.example.coffeeapp.data.repository

import com.example.coffeeapp.data.model.remote.BeveragesResult
import com.example.coffeeapp.data.model.remote.Photos
import com.example.coffeeapp.data.remote.RestaurantsService
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val restaurentService: RestaurantsService) {
    fun getBeverages(query: String): Call<BeveragesResult>{
        return restaurentService.getBeverageBasedOnName(query)
    }

     fun getBeverageById(id: String): Call<Photos> {
        return restaurentService.getSingleBeverage(id = id)
    }
}