package com.example.coffeeapp.ui.screens.OrderScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.coffeeapp.R
import com.example.coffeeapp.data.model.graph.Order
import com.example.coffeeapp.ui.core.SegmentControl
import com.example.coffeeapp.ui.graph.Screens


@Composable
fun OrderScreen(navController: NavController, model: OrderScreenViewModel, orderDetail: String) {

    LaunchedEffect(key1 = orderDetail) {
        model.parseOrder(orderDetail)
    }
    Column(
        Modifier
            .padding(top = 30.dp, start = 30.dp, end = 30.dp, bottom = 8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Order",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        SegmentControl(model.selectedSegment, model.setSelectedSegment)
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Delivery Address", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(text = model.address, fontSize = 12.sp, fontWeight = FontWeight.Light)
        Spacer(modifier = Modifier.height(20.dp))
        Divider(modifier = Modifier.height(1.dp), color = Color(0xFFEAEAEA))
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = model.orderDetail?.imgUrl,
                    contentDescription = model.orderDetail?.name,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .width(74.dp)
                        .height(64.dp)
                        .padding(end = 12.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Column {
                    Text(
                        text = if (model.orderDetail?.name.orEmpty().length > 7) model.orderDetail?.name.orEmpty()
                            .substring(0, 6) else model.orderDetail?.name.orEmpty(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Size: ${model.orderDetail?.size}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedButton(
                    onClick = { model.decrementOrderCount() },
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    border = BorderStroke(1.dp, Color(0xFFEAEAEA)),
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp)
                ) {
                    Text(
                        "-",
                        fontSize = 20.sp,
                        color = Color.Black,
                        modifier = Modifier.offset(y = -3.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = model.orderCount.toString())
                Spacer(modifier = Modifier.width(12.dp))
                OutlinedButton(
                    onClick = { model.incrementOrderCount() },
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    border = BorderStroke(1.dp, Color(0xFFEAEAEA)),
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp)
                ) {
                    Icon(
                        Icons.Rounded.Add, contentDescription = "", modifier = Modifier
                            .height(16.dp)
                            .width(16.dp), tint = Color.Black
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(26.dp))
        Divider(modifier = Modifier.height(4.dp), color = Color(0xFFEAEAEA))
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(width = 1.dp, color = Color(0xFFEAEAEA))
        ) {
            Text(
                text = "Coupon is not applicable",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(10.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Icon(
                Icons.Rounded.KeyboardArrowRight, contentDescription = "", modifier = Modifier
                    .size(16.dp)
                    .padding(0.dp)
                    .offset(y = 1.dp), tint = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Payment Summary", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Price")
            Text(
                text = "$ ${String.format("%.2f", model.orderPrice)}",
                fontWeight = FontWeight.Black
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (model.selectedSegment == "Deliver") {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Delivery Fee")
                Text(text = "$ 1.00", fontWeight = FontWeight.Black)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
        Divider(modifier = Modifier.height(1.dp), color = Color(0xFFEAEAEA))
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total Payment")
            Text(text = "$ ${String.format("%.2f", model.bagPrice)}", fontWeight = FontWeight.Black)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { navController.navigate(Screens.DeliveryScreen.route){
                popUpTo(Screens.OrderScreen.route){
                    inclusive = true
                }
            } },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Order",
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
    }
}