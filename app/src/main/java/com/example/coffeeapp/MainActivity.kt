package com.example.coffeeapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.os.BundleCompat.getParcelable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coffeeapp.data.model.graph.Order
import com.example.coffeeapp.ui.graph.Screens
import com.example.coffeeapp.ui.screens.CartScreen.CartScreen
import com.example.coffeeapp.ui.screens.DeliveryScreen.DeliveryScreen
import com.example.coffeeapp.ui.screens.DeliveryScreen.DeliveryScreenViewModel
import com.example.coffeeapp.ui.screens.DetailScreen.DetailsScreen
import com.example.coffeeapp.ui.screens.DetailScreen.DetailsScreenViewModel
import com.example.coffeeapp.ui.screens.HomeScreen.HomeScreen
import com.example.coffeeapp.ui.screens.OnboardingScreen.OnBoardingScreen
import com.example.coffeeapp.ui.screens.OnboardingScreen.OnboardingScreenViewModel
import com.example.coffeeapp.ui.screens.OrderScreen.OrderScreen
import com.example.coffeeapp.ui.screens.OrderScreen.OrderScreenViewModel
import com.example.coffeeapp.ui.screens.SplashScreen.SplashScreen
import com.example.coffeeapp.ui.screens.SplashScreen.SplashScreenViewModel
import com.example.coffeeapp.ui.theme.CoffeeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun App() {


    val rootScreens = arrayOf(
        Screens.RootScreen.HomeScreen.route,
        Screens.RootScreen.CartScreen.route,
    )

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    Scaffold(
        bottomBar = {

            AnimatedVisibility(visible = rootScreens.contains(navBackStackEntry?.destination?.route)) {
                NavigationBar(
                    containerColor = Color(0xFFF9F9F9),
                    modifier = Modifier
                        .shadow(
                            elevation = 24.dp,
                            spotColor = Color(0x40E4E4E4),
                            ambientColor = Color(0x40E4E4E4)
                        )
                        .clip(
                            RoundedCornerShape(24.dp)
                        ),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        NavigationBarItem(
                            selected = false,
                            onClick = {
                                if (!navController.popBackStack(
                                        Screens.RootScreen.HomeScreen.route,
                                        inclusive = false
                                    )
                                ) {
                                    navController.navigate(route = Screens.RootScreen.HomeScreen.route)
                                }
                            },
                            icon = {
                                Image(
                                    painter = painterResource(id = R.drawable.home),
                                    contentDescription = "icon",
                                    modifier = Modifier
                                        .width(24.dp)
                                        .height(24.dp)
                                )
                            },
                        )
                        NavigationBarItem(
                            selected = false,
                            onClick = { /*TODO*/ },
                            icon = {
                                Image(
                                    painter = painterResource(id = R.drawable.heart),
                                    contentDescription = "icon",
                                    modifier = Modifier
                                        .width(24.dp)
                                        .height(24.dp)
                                )
                            },
                        )
                        NavigationBarItem(
                            selected = false,
                            onClick = { navController.navigate(route = Screens.RootScreen.CartScreen.route) },
                            icon = {
                                Image(
                                    painter = painterResource(id = R.drawable.bag),
                                    contentDescription = "icon",
                                    modifier = Modifier
                                        .width(24.dp)
                                        .height(24.dp)
                                )
                            },
                        )
                    }
                }
            }
        }
    ) {
        NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
            composable(route = Screens.SplashScreen.route) {
                val parentEntry = remember(it) {
                    navController.getBackStackEntry(Screens.SplashScreen.route)
                }
                val model = hiltViewModel<SplashScreenViewModel>(parentEntry)
                SplashScreen(navController, model)
            }
            composable(route = Screens.OnboardingScreen.route) {
                val parentEntry = remember(it) {
                    navController.getBackStackEntry(Screens.OnboardingScreen.route)
                }
                val model = hiltViewModel<OnboardingScreenViewModel>(parentEntry)
                OnBoardingScreen(navController, model)
            }
            navigation(
                startDestination = Screens.RootScreen.HomeScreen.route,
                route = Screens.RootScreen.route
            ) {
                composable(route = Screens.RootScreen.HomeScreen.route) {
                    HomeScreen(navController)
                }
                composable(route = Screens.RootScreen.CartScreen.route) {
                    CartScreen()
                }
            }
            composable(route = Screens.DeliveryScreen.route) {
                val viewModel = hiltViewModel<DeliveryScreenViewModel>()
                DeliveryScreen(viewModel)
            }
            composable(route = Screens.DetailsScreen.route) {
                val id = it.arguments?.getString("id").orEmpty()
                val model = hiltViewModel<DetailsScreenViewModel>()
                DetailsScreen(navController = navController, model = model, id = id)
            }

            composable(
                route = Screens.OrderScreen.route,
                arguments = listOf(navArgument("payload") {
                    type = NavType.StringType
                })
            ) {
                val payload = it.arguments?.getString("payload")
                val model = hiltViewModel<OrderScreenViewModel>()
                OrderScreen(navController, model, orderDetail = payload!!)
            }
        }

    }

}

