package com.example.coffeeapp.data.utils.globalState

import android.location.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserGlobalState @Inject constructor() {

    private var _name = MutableStateFlow<String?>(null)
    val name = _name.asStateFlow()

    private var _location = MutableStateFlow<Location?>(null)
    val location = _location.asStateFlow()

    private var _address = MutableStateFlow<String?>(null)
    val address = _address.asStateFlow()


    val setName: (String) -> Unit = {
        _name.value = it
    }
    val setLocation: (Location) -> Unit = { _location.value = it }
    val setAddress: (String) -> Unit = { _address.value = it }

}