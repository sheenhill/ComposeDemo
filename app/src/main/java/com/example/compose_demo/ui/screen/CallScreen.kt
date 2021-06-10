package com.example.compose_demo.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_demo.PhoneNum
import com.example.compose_demo.ui.theme.Compose_demoTheme
import com.example.compose_demo.ui.theme.Text333

@Composable
fun CallScreen(data: PhoneNum, navigate: () -> Unit) {
    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Text333)
                .padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close, contentDescription = "", tint = Color.White,
                modifier = Modifier
                    .size(30.dp, 30.dp)
                    .align(Alignment.TopEnd)
                    .clickable { navigate() }
            )
            PhoneCall(
                data,
                Modifier
                    .width(250.dp)
                    .align(Alignment.Center)
                    .offset(y = (-100).dp)
            )
            Icon(
                imageVector = Icons.Filled.Call, contentDescription = "", tint = Color.Green,
                modifier = Modifier
                    .size(60.dp, 60.dp)
                    .align(Alignment.BottomCenter)
                    .absoluteOffset(y = (-80).dp)
            )
        }
    }
}

@Composable
fun PhoneCall(data: PhoneNum, modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.Face,
            contentDescription = "",
            modifier = Modifier
                .width(150.dp)
                .height(150.dp), tint = Color.White
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = data.name, fontSize = 24.sp, color = Color.White)
        Text(text = data.phone_num, fontSize = 12.sp, color = Color.White)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    Compose_demoTheme {
//        CallScreen(PhoneNum()) {}
//    }
//}