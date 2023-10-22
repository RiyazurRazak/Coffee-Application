package com.example.coffeeapp.ui.screens.DetailScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.coffeeapp.data.model.graph.Order
import com.example.coffeeapp.data.model.local.User
import com.example.coffeeapp.ui.graph.Screens
import com.squareup.moshi.Moshi
import java.net.URLEncoder


@Composable
fun DetailsScreen(navController: NavController, model: DetailsScreenViewModel, id: String) {

    LaunchedEffect(key1 = id) {
        model.getDetails(id)
    }

    Column(
        Modifier
            .padding(top = 30.dp, start = 30.dp, end = 30.dp, bottom = 8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Detail",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        AsyncImage(
            model = model.data?.src?.landscape.orEmpty(),
            contentDescription = model.data?.alt.orEmpty(),
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .height(220.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillBounds
        )
        Spacer(modifier = Modifier.height(18.dp))
        Text(
            text = model.data?.photographer.orEmpty(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = model.data?.alt.orEmpty(),
            fontSize = 12.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(20.dp))
        Divider(thickness = 2.dp)
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Description",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "A cappuccino is an approximately 150 ml (5 oz) beverage, with 25 ml of espresso coffee and 85ml of fresh milk the fo.. Read More",
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            lineHeight = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Size",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(onClick = { model.setCupSize("S") }, shape = RoundedCornerShape(12.dp)) {
                Text(text = "S", modifier = Modifier.padding(vertical = 6.dp, horizontal = 20.dp))
            }
            OutlinedButton(onClick = { model.setCupSize("M") }, shape = RoundedCornerShape(12.dp)) {
                Text(text = "M", modifier = Modifier.padding(vertical = 6.dp, horizontal = 20.dp))
            }
            OutlinedButton(onClick = { model.setCupSize("L") }, shape = RoundedCornerShape(12.dp)) {
                Text(text = "L", modifier = Modifier.padding(vertical = 6.dp, horizontal = 20.dp))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(text = "Size", fontSize = 14.sp, fontWeight = FontWeight.Light)
                Text(
                    text = model.cupSize,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Button(
                onClick = {
                    navController.navigate(
                        "order/${URLEncoder.encode(model.prepareOrder(), "utf-8")}"
                    )
                },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                Text(
                    text = "Buy Now",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }


    }
}