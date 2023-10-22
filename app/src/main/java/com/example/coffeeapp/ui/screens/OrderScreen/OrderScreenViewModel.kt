package com.example.coffeeapp.ui.screens.OrderScreen

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.coffeeapp.data.model.graph.Order
import com.example.coffeeapp.data.utils.globalState.UserGlobalState
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OrderScreenViewModel @Inject constructor(private val userState: UserGlobalState): ViewModel() {

    val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val jsonAdapter = moshi.adapter(Order::class.java).lenient()




    var orderDetail by mutableStateOf<Order?>(null)
        private set

    var selectedSegment by mutableStateOf<String>("Deliver")
        private set

    val address: String = userState.address.value.orEmpty()

    var orderCount by mutableStateOf<Int>(1)
        private set

    var orderPrice by mutableStateOf<Double>(4.53)
        private set

    val bagPrice by derivedStateOf<Double>{
        if(selectedSegment == "Deliver"){
            return@derivedStateOf (orderPrice) + 1.0
        }else{
            return@derivedStateOf orderPrice
        }

    }

    val setSelectedSegment : (String) -> Unit = {selectedSegment = it}

    val incrementOrderCount: () -> Unit = {
        orderCount++
        orderPrice += 4.53
    }

    val decrementOrderCount : () -> Unit = {
        if(orderCount > 1){
            orderCount--
            orderPrice -= 4.53
        }
    }

    fun parseOrder(order: String){
        orderDetail = jsonAdapter.fromJson(order)
    }


//    suspend fun addToCart(){
//        cartRepository.insertOrderInCart("", "","","","")
//    }
}