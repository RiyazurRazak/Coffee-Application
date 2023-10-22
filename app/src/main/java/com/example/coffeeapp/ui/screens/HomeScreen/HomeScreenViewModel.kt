package com.example.coffeeapp.ui.screens.HomeScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.coffeeapp.data.model.remote.BeveragesResult
import com.example.coffeeapp.data.model.remote.Photos
import com.example.coffeeapp.data.repository.RestaurantRepository
import com.example.coffeeapp.data.utils.globalState.UserGlobalState
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel()
class HomeScreenViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
    private val userState: UserGlobalState
) : ViewModel() {

    val beveragesTypes = arrayListOf<String>(
        "Cappuccino",
        "Latte",
        "Americano",
        "Frappuccino",
        "Espresso",
        "Cold Brew",
        "Macchiato",
        "Mocha",
        "Iced Espresso",
        "Mazagran"
    )


    var selectedBeverageType by mutableStateOf<String>("Cappuccino")
        private set

    val address = userState.address.value?.substring(0, 20) + ".."


    var beverages by mutableStateOf<ArrayList<Photos>?>(null)

    var searchQuery by mutableStateOf<String>("")
        private set

    val setSearchQuery : (String) -> Unit = {searchQuery = it}

    val setSelectedBeverageType: (String) -> Unit = {
        selectedBeverageType = it
        val call = restaurantRepository.getBeverages(it)
        call.enqueue(
            object : Callback<BeveragesResult> {
                override fun onResponse(
                    call: Call<BeveragesResult>,
                    response: Response<BeveragesResult>
                ) {
                    beverages = if (response.isSuccessful) {
                        response.body()?.photos
                    } else {
                        null
                    }
                }

                override fun onFailure(call: Call<BeveragesResult>, t: Throwable) {
                    // network failure
                }

            }
        )
    }

    init {
        val call = restaurantRepository.getBeverages(selectedBeverageType)
        call.enqueue(
            object : Callback<BeveragesResult> {
                override fun onResponse(
                    call: Call<BeveragesResult>,
                    response: Response<BeveragesResult>
                ) {
                    beverages = if (response.isSuccessful) {
                        response.body()?.photos
                    } else {
                        null
                    }
                }

                override fun onFailure(call: Call<BeveragesResult>, t: Throwable) {
                    // network failure
                }

            }
        )
    }

}

