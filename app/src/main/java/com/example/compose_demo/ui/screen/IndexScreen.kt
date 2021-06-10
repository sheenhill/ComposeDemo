package com.example.compose_demo.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_demo.PhoneNum
import com.example.compose_demo.ui.theme.*

@Composable
fun IndexScreen(list: List<PhoneNum>, toSearch: () -> Unit, toCall: (data:PhoneNum) -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, 0.dp)
        ) {
            MyTopBar()
            MyNumSearcher(toSearch)
            PhoneNumList(list, toCall)
        }
}

@Composable
fun MyTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 24.dp, 0.dp, 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "联系人",
            style = MaterialTheme.typography.h1.copy(fontSize = 24.sp),
            textAlign = TextAlign.Start,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun MyNumSearcher(click: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(48.dp)
            .padding(bottom = 16.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = F2)
            .clickable { click() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(8.dp))
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "",
            tint = Text999,
            modifier = Modifier
                .padding(8.dp)
                .offset(0.dp, 2.dp)
        )
        Text(
            text = "搜索联系人",
            color = Text999,
            fontSize = 13.sp
        )
    }
}

@Composable
fun PhoneNumList(phoneNumList: List<PhoneNum>, toCall: (data:PhoneNum) -> Unit) {
    LazyColumn {
        items(count = phoneNumList.size) {
            PhoneNumItem(phoneNumList[it], toCall)
            if (it != phoneNumList.size - 1) Divider(color = DDD)
        }
    }
}

@Composable
fun PhoneNumItem(data: PhoneNum, toCall: (data:PhoneNum) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(40.dp)
            .clickable { toCall(data) }) {
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = data.name,
            modifier = Modifier.width(100.dp),
            style = MaterialTheme.typography.h1.copy(fontSize = 18.sp),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = data.phone_num)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    Compose_demoTheme {
//        IndexScreen(List(100) { PhoneNum("欧阳修", "12345678901") }, {}, {})
//    }
//}