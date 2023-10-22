package com.example.coffeeapp.ui.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SegmentControl(selected: String, onSelected: (String) -> Unit) {
    Box(
        Modifier
            .height(46.dp)
            .background(color = Color(0xFFF2f2f2), shape = RoundedCornerShape(14.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
            ) {
                Segmant(value = "Deliver", isSelect = selected == "Deliver") {
                    onSelected(it)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
            ) {
                Segmant(value = "Pickup", isSelect = selected == "Pickup") {
                        onSelected(it)
                }
            }

        }

    }
}


@Composable
fun Segmant(value: String, isSelect: Boolean, onClick: (String) -> Unit) {
    if (isSelect) {
        Button(
            onClick = { onClick(value) },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = value)
        }
    } else {
        Text(
            text = value,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onClick(value) }),
            textAlign = TextAlign.Center
        )
    }
}