package com.example.coffeeapp.ui.core

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coffeeapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(value: String, onChange: (String) -> Unit, placeholder: String) {

    Row(
        verticalAlignment = Alignment.CenterVertically ,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF313131), shape = RoundedCornerShape(16.dp))
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "ico",
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color(0xFF989898),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
            textStyle = TextStyle(color = Color.White),
            modifier = Modifier.fillMaxWidth(0.8f).testTag("search input field"),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )
        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.background(color= MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(12.dp))) {
            Image(painter = painterResource(id = R.drawable.furnitur), contentDescription = "filter icon", modifier = Modifier.width(20.dp).height(20.dp) )
        }

    }
}