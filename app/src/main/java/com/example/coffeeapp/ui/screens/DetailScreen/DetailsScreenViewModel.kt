package com.example.coffeeapp.ui.screens.DetailScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.data.model.graph.Order
import com.example.coffeeapp.data.model.remote.Photos
import com.example.coffeeapp.data.remote.RestaurantsService
import com.example.coffeeapp.data.repository.RestaurantRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class DetailsScreenViewModel @Inject constructor(private val restaurentRepository: RestaurantRepository) :
    ViewModel() {


    var productId by mutableStateOf<String>("")
        private set

    var data by mutableStateOf<Photos?>(null)
        private set

    var cupSize by mutableStateOf<String>("S")
        private set

    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val jsonAdapter = moshi.adapter(Order::class.java).lenient()


    fun getDetails(id: String) {

        productId = id
        val call = restaurentRepository.getBeverageById(id)
        call.enqueue(
            object : Callback<Photos> {
                override fun onResponse(call: Call<Photos>, response: Response<Photos>) {

                    if (response.isSuccessful) {
                        data = response.body()
                    } else {
                        // crash logs
                        data = null
                    }
                }

                override fun onFailure(call: Call<Photos>, t: Throwable) {
                    // network failure

                }

            }
        )
    }

    fun prepareOrder(): String {
        val order = Order(
            imgUrl = data?.src?.tiny.orEmpty(),
            name = data?.photographer.orEmpty(),
            size = cupSize
        )
        return jsonAdapter.toJson(order)
    }

    val setCupSize: (String) -> Unit = { cupSize = it }
}