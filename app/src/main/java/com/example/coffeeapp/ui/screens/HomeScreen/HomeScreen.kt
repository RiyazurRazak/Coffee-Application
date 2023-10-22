package com.example.coffeeapp.ui.screens.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.coffeeapp.R
import com.example.coffeeapp.ui.core.ProductCard
import com.example.coffeeapp.ui.core.SearchField
import com.example.coffeeapp.ui.graph.Screens

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(navController: NavController) {


    val viewModel = hiltViewModel<HomeScreenViewModel>()


    LazyColumn(
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(
                        Brush.linearGradient(
                            colorStops = arrayOf(
                                0.3f to Color(0xFF131313),
                                0.9f to Color(0xFF313131),
                            )
                        )
                    )
            ) {
                Column(Modifier.padding(horizontal = 30.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    ) {
                        Column() {
                            Text(text = "Location", fontSize = 12.sp, color = Color.White)
                            Text(
                                text = viewModel.address.orEmpty(),
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Image(
                            painter = painterResource(id = R.drawable.avatar),
                            contentDescription = "avatar",
                            modifier = Modifier
                                .width(44.dp)
                                .height(44.dp)
                                .clip(
                                    RoundedCornerShape(14.dp)
                                )
                        )
                    }
                    SearchField(
                        value = viewModel.searchQuery,
                        onChange = { viewModel.setSearchQuery(it) },
                        placeholder = "Search Coffee"
                    )
                    Spacer(modifier = Modifier.height(19.dp))

                }
                Image(
                    painter = painterResource(id = R.drawable.banner),
                    contentDescription = "promo banner",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .padding(horizontal = 30.dp)
                        .offset(y = 180.dp)
                )

            }
            Column(Modifier.padding(top = 70.dp)) {
                Surface(Modifier.padding(start = 30.dp)) {
                    LazyRow() {
                        items(viewModel.beveragesTypes) {
                            if (viewModel.selectedBeverageType == it) {
                                Button(
                                    onClick = { /*TODO*/ },
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                ) {
                                    Text(
                                        text = it,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(
                                            vertical = 7.dp,
                                            horizontal = 12.dp
                                        )
                                    )
                                }
                            } else {
                                OutlinedButton(
                                    onClick = { viewModel.setSelectedBeverageType(it) },
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                ) {
                                    Text(
                                        text = it,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(
                                            vertical = 7.dp,
                                            horizontal = 12.dp
                                        )
                                    )
                                }
                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.height(18.dp))
            }
        }
        item {
            FlowRow(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (viewModel.beverages.isNullOrEmpty()) {
                    CircularProgressIndicator()
                } else {
                    viewModel.beverages?.map {
                        ProductCard(it) {
                            navController.navigate("details/$it")
                        }
                    }
                }
            }


        }
    }
}