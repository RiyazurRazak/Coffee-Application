package com.example.coffeeapp.ui.screens.SplashScreen

import android.Manifest
import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coffeeapp.data.dao.UserDao
import com.example.coffeeapp.data.repository.RestaurantRepository
import com.example.coffeeapp.data.repository.UserRepository
import com.example.coffeeapp.data.utils.globalState.UserGlobalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userState: UserGlobalState,
) : ViewModel() {

    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    var isNewUser by mutableStateOf<Boolean?>(null)
        private set

    fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            async {
                val user = userRepository.getUser()
                if (user == null) {
                    isNewUser = true
                } else {
                    isNewUser = false
                    userState.setName(user.name)
                }
            }.await()
        }
    }

    val setAddress: (String) -> Unit = { userState.setAddress(it) }
    val setLocation: (Location) -> Unit = { userState.setLocation(it) }

}