package com.example.coffeeapp.ui.screens.DeliveryScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.coffeeapp.R
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeliveryScreen(viewModel: DeliveryScreenViewModel) {


    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(
                viewModel.location.latitude, viewModel.location.longitude
            ), 13f
        )
    }
    val modalBottomSheetState = rememberModalBottomSheetState()

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.65f),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                compassEnabled = true,
                zoomControlsEnabled = true,
                myLocationButtonEnabled = true,
                scrollGesturesEnabledDuringRotateOrZoom = true,
                zoomGesturesEnabled = true,
                scrollGesturesEnabled = true,
                mapToolbarEnabled = true,
                rotationGesturesEnabled = true,
            ),
            properties = MapProperties(
                mapType = MapType.TERRAIN,
                isMyLocationEnabled = true,
                maxZoomPreference = 22.0f
            )
        ) {
            Marker(
                state = MarkerState(
                    position = LatLng(
                        viewModel.location.latitude, viewModel.location.longitude
                    )
                ),
                title = "Your Location",
                snippet = "Marker in Your Location",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.pin)
            )
            Marker(
                state = MarkerState(
                    position = LatLng(
                        viewModel.location.latitude + 0.018, viewModel.location.longitude + 0.018
                    )
                ),
                title = "Coffee Shop",
                snippet = "Marker in Coffee Shop",
                icon = BitmapDescriptorFactory.fromResource(R.drawable.bike)
            )
            Polyline(
                points = arrayListOf(
                    LatLng(
                        viewModel.location.latitude, viewModel.location.longitude
                    ), LatLng(
                        viewModel.location.latitude + 0.018, viewModel.location.longitude + 0.018
                    )
                ), color = MaterialTheme.colorScheme.primary
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Column(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = "10 Minutes Left",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Your Order Is Being Prepared In The Coffee Shop",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.delivery_agent_icon),
                        contentDescription = "icon",
                        modifier = Modifier
                            .width(62.dp)
                            .height(62.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(verticalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Delivered your order", fontWeight = FontWeight.Bold)
                        Text(
                            text = "We deliver your goods to you in the shortes possible time.",
                            fontSize = 10.sp,
                            lineHeight = 12.sp
                        )
                    }
                }
            }
        }

    }

    if(viewModel.isModalSheetEnabled) {
        ModalBottomSheet(
            onDismissRequest = { viewModel.toggleIsModalSheetEnabled() },
            sheetState = modalBottomSheetState,
            modifier = Modifier.fillMaxHeight(0.35f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "10 minutes left", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = "Delivery to ${if(viewModel.address?.length!! > 24) viewModel.address?.substring(0,24) + ".." else viewModel.address}", fontSize = 12.sp)
                Spacer(modifier = Modifier.height(18.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .height(3.dp)
                            .background(color = Color.Green)
                            .clip(RoundedCornerShape(12.dp)),
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.25f)
                            .height(3.dp)
                            .background(color = Color.Green)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.3f)
                            .height(3.dp)
                            .background(color = Color.Green)
                            .clip(RoundedCornerShape(12.dp))
                    )

                }
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedCard(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.delivery_agent_icon),
                            contentDescription = "icon",
                            modifier = Modifier
                                .width(62.dp)
                                .height(62.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column(verticalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "Delivered your order", fontWeight = FontWeight.Bold)
                            Text(
                                text = "We deliver your goods to you in the shortes possible time.",
                                fontSize = 10.sp,
                                lineHeight = 12.sp
                            )
                        }
                    }

                }
            }
        }
    }
}