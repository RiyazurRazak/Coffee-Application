package com.example.coffeeapp.ui.screens.CartScreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CartScreen(){
    LazyColumn(){
        item {
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Your Orders", textAlign = TextAlign.Center, fontWeight = FontWeight.Black, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}