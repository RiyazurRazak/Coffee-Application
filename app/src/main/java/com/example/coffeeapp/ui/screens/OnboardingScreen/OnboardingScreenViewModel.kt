package com.example.coffeeapp.ui.screens.OnboardingScreen

import android.Manifest
import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.coffeeapp.data.repository.UserRepository
import com.example.coffeeapp.data.utils.globalState.UserGlobalState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OnboardingScreenViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userState: UserGlobalState
) :
    ViewModel() {


    var showBottomSheet by mutableStateOf<Boolean>(false)
        private set

    var name by mutableStateOf<String?>(null)
        private set

    var mobileNumber by mutableStateOf<String?>(null)
        private set

    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    val setName: (String) -> Unit = { name = it }
    val setMobileNumber: (String) -> Unit = { mobileNumber = it }
    val setCurrentAddress: (String) -> Unit = { userState.setAddress(it) }
    val setLocation: (Location) -> Unit = { userState.setLocation(it) }
    val toggleShowBottomSheet: () -> Unit = { showBottomSheet = !showBottomSheet }

    suspend fun onSubmitOnBoardingForm() {
        if (name != null && mobileNumber != null) {
            try {
                userRepository.insertUser(name!!, mobileNumber!!)
                userState.setName(name!!)
                toggleShowBottomSheet()
            } catch (err: Error) {
                println(err)
            }
        }
    }
}