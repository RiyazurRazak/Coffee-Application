package com.example.coffeeapp.ui.screens.SplashScreen

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coffeeapp.R
import com.example.coffeeapp.ui.graph.Screens
import com.example.coffeeapp.ui.screens.OnboardingScreen.OnboardingScreenViewModel
import com.google.android.gms.location.LocationServices
import java.util.Locale

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashScreenViewModel) {


    LaunchedEffect(Unit){
        viewModel.init()
    }

    val context = LocalContext.current

    val locationService = LocationServices.getFusedLocationProviderClient(context)
    val geoCoder = Geocoder(context, Locale.getDefault())

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            val permissionsGranted = permissions.values.reduce { acc, isPermissionGranted ->
                acc && isPermissionGranted
            }
            if (!permissionsGranted) {
                Toast.makeText(context, "Permission Request Required", Toast.LENGTH_LONG).show()
            }
        }
    )

    // runs once per composition : like empty dependency [] in react useEffect

    LaunchedEffect(key1 = viewModel.isNewUser) {
        println("overrrr")
        println(viewModel.isNewUser)
        if (viewModel.isNewUser != null) {
            if (viewModel.isNewUser == true) {
                navController.navigate("onboarding")
            } else {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    permissionLauncher.launch(viewModel.locationPermissions)
                    Toast.makeText(context, "Permission Request Required", Toast.LENGTH_LONG).show()

                } else {
                    locationService.lastLocation.addOnSuccessListener {
                        println(it)
                        it?.let {
                            viewModel.setLocation(it)
                            val locationAddress =
                                geoCoder.getFromLocation(it.latitude, it.longitude, 1)
                            locationAddress?.get(0)?.getAddressLine(0)
                                ?.let { address -> viewModel.setAddress(address) }
                            navController.navigate(Screens.RootScreen.HomeScreen.route) {
                                popUpTo(0)
                            }
                        }
                    }
                }
            }
        }
    }

    Column(
        Modifier
            .background(color = Color.Black)
            .verticalScroll(rememberScrollState()).height(IntrinsicSize.Max)
    ) {

        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "background image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(570.dp)
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 40.dp)
                .graphicsLayer {
                    translationY = -150f
                },
        ) {
            Text(
                text = "Coffee so good, your taste buds will love it",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(lineHeight = 40.sp),
                textAlign = TextAlign.Center,
                color = Color.White,

                )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "The best grain, the finest roast, the powerful flavor.",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                style = TextStyle(lineHeight = 18.sp),
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(120.dp))
        }
    }

}