package com.example.coffeeapp.ui.screens.DeliveryScreen

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.coffeeapp.data.utils.globalState.UserGlobalState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DeliveryScreenViewModel @Inject constructor(private val userState: UserGlobalState) : ViewModel() {

    val location = (userState.location.value!!)


    val address = userState.address.value


    var isModalSheetEnabled by mutableStateOf<Boolean>(true)
        private set

    val toggleIsModalSheetEnabled : () -> Unit = {isModalSheetEnabled = !isModalSheetEnabled}

}