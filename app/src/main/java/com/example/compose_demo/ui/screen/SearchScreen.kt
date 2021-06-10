package com.example.compose_demo.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_demo.PhoneNum
import com.example.compose_demo.ui.theme.*

@Composable
fun SearchScreen(
    result: List<PhoneNum>,
    search: (String) -> Unit,
    back: () -> Unit,
    toCall: (data: PhoneNum) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchView(search, back)
        Divider(
            Modifier
                .fillMaxWidth()
                .height(1.dp), color = DDD
        )
        PhoneNumList(phoneNumList = result, toCall = toCall)
    }
}

@Composable
fun SearchView(search: (String) -> Unit, back: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .height(64.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(Modifier.width(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .height(48.dp)
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var name by remember { mutableStateOf("") }
            val focusRequester = FocusRequester()
            BasicTextField(
                value = name,
                onValueChange = {
                    name = it
                    search(it)
                },
                textStyle = TextStyle(fontSize = 13.sp, color = Text333),
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Box {
                        if (name.isEmpty()) {
                            Text( // placeholder
                                text = "搜索联系人",
                                fontSize = 13.sp, color = Text999
                            )
                        }
                        innerTextField()  //<-- 很关键
                    }
                }
            )
            DisposableEffect(Unit) {
                focusRequester.requestFocus()
                onDispose {}
            }
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = "Clear",
                modifier = Modifier
                    .size(12.dp)
            )
            Spacer(Modifier.size(16.dp))
            Divider(Modifier.size(1.dp, 10.dp))
            Spacer(Modifier.size(16.dp))
            Text(text = "取消", fontSize = 13.sp,
                modifier = Modifier.clickable { back() })
            Spacer(Modifier.width(20.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_demoTheme {
        SearchScreen(List(10) { PhoneNum("aaa", "ccc") }, {}, {}, {})
    }
}