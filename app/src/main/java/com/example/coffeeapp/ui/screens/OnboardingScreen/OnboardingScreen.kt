package com.example.coffeeapp.ui.screens.OnboardingScreen


import android.Manifest
import android.Manifest.permission.*
import android.content.pm.PackageManager
import android.location.Geocoder
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.coffeeapp.R
import com.example.coffeeapp.ui.graph.Screens
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnBoardingScreen(navController: NavController, viewModel: OnboardingScreenViewModel) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val locationService = LocationServices.getFusedLocationProviderClient(context)
    val geoCoder = Geocoder(context, Locale.getDefault())

    fun fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                context, ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context, ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "Permission Request Required", Toast.LENGTH_LONG).show()
            return
        }
        locationService.lastLocation.addOnSuccessListener {
            it?.let {
                viewModel.setLocation(it)
                val locationAddress = geoCoder.getFromLocation(it.latitude, it.longitude, 1)
                locationAddress?.get(0)?.getAddressLine(0)
                    ?.let { address -> viewModel.setCurrentAddress(address) }
            }
            navController.navigate(Screens.RootScreen.HomeScreen.route){
                popUpTo(0)
            }
        }
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = { permissions ->
                val permissionsGranted = permissions.values.reduce { acc, isPermissionGranted ->
                    acc && isPermissionGranted
                }
                if (!permissionsGranted) {
                    Toast.makeText(context, "Permission Request Required", Toast.LENGTH_LONG).show()
                } else {
                    fetchLocation()
                }
            },
        )




    Column(
        Modifier
            .background(color = Color.Black)
            .verticalScroll(rememberScrollState())
    ) {

        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "background image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(520.dp)
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
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { viewModel.toggleShowBottomSheet() },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }
    }

    if (viewModel.showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.toggleShowBottomSheet() },
            sheetState = modalBottomSheetState,
            modifier = Modifier.fillMaxHeight(0.6f),
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Please Enter Your Mobile Number To Proceed",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = viewModel.name.orEmpty(),
                    onValueChange = { viewModel.setName(it) },
                    placeholder = { Text(text = "Name") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = viewModel.mobileNumber.orEmpty(),
                    onValueChange = { viewModel.setMobileNumber(it) },
                    placeholder = { Text(text = "Mobile Number") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number, imeAction = ImeAction.Done
                    ),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            async {
                                viewModel.onSubmitOnBoardingForm()
                            }.await()
                            permissionLauncher.launch(viewModel.locationPermissions)
                        }
                    }, shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Proceed",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                }

            }

        }
    }


}