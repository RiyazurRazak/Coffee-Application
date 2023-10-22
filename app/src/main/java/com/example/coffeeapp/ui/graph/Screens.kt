package com.example.coffeeapp.ui.graph

sealed class Screens(val route: String,){

    object SplashScreen: Screens("splash_screen")
    object OnboardingScreen: Screens("onboarding")
    object RootScreen: Screens("root"){
        object HomeScreen : Screens("home")
        object CartScreen : Screens("cart")
    }
    object DeliveryScreen: Screens("delivery")
    object DetailsScreen: Screens("details/{id}")
    object OrderScreen : Screens("order/{payload}")
}