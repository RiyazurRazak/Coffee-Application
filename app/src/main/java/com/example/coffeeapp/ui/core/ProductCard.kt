package com.example.coffeeapp.ui.core


import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.coffeeapp.R
import com.example.coffeeapp.data.model.remote.Photos


@Composable
fun ProductCard(data: Photos, onClick: (String)-> Unit) {
    AnimatedContent(targetState = data, label = "") {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
            modifier = Modifier.width(148.dp).padding(vertical = 16.dp).clickable(
                enabled = true,
                onClick = {onClick(it.id.toString())}
            )
        ) {
            Column(Modifier.padding(8.dp)) {
                Surface {
                    Box(Modifier.background(color = Color(0xFFF9F9F9))) {
                        AsyncImage(
                            model = data.src?.small.orEmpty(),
                            contentDescription = "coffee",
                            modifier = Modifier
                                .clip(
                                    RoundedCornerShape(14.dp)
                                )
                                .width(140.dp)
                                .height(132.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = "rating",
                            modifier = Modifier
                                .width(10.dp)
                                .height(10.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = (1..4).random().toString() + "." + (0..9).random().toString(),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
                Column(Modifier.padding(12.dp)) {
                    Text(
                        text = if (it.photographer.orEmpty().length > 7) data.photographer.orEmpty()
                            .substring(0, 5) else data.photographer.orEmpty(),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp
                    )
                    Text(
                        text = if (data.alt.orEmpty().length > 34) data.alt.orEmpty()
                            .substring(0, 32) else data.alt.orEmpty(),
                        fontWeight = FontWeight.Light,
                        fontSize = 10.sp,
                        color = Color.Gray,
                        lineHeight = 11.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "\$ 4.53", fontWeight = FontWeight.ExtraBold)
                        IconButton(
                            onClick = { /*TODO*/ }, modifier = Modifier
                                .background(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .width(32.dp)
                                .height(32.dp)
                        ) {
                            Icon(
                                Icons.Rounded.Add,
                                contentDescription = "Add Icon",
                                modifier = Modifier.size(16.dp),
                                tint = Color.White
                            )
                        }
                    }

                }


            }
        }

    }
}