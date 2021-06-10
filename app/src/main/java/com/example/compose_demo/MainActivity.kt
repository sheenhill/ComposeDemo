package com.example.compose_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.material.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_demo.ui.screen.SearchScreen
import com.example.compose_demo.ui.screen.CallScreen
import com.example.compose_demo.ui.screen.IndexScreen
import com.example.compose_demo.ui.theme.Compose_demoTheme

class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm = ViewModelProvider(this)[MyViewModel::class.java]
        vm.readPhoneNumBook(this)
        setContent {
            Compose_demoTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Index.name
                    ) {
                        composable(Screen.Index.name) {
                            val list = vm.phoneNumList.observeAsState(initial = emptyList())
                            IndexScreen(
                                list.value,
                                {
                                    navController.toSearch()
                                    vm.cleanSearch()
                                },
                                { data ->
                                    navController.toCall()
                                    vm.changePhoneNum(data)
                                },
                            )
                        }
                        composable(Screen.Search.name) {
                            val list = vm.searchPNList.observeAsState(initial = emptyList())
                            SearchScreen(
                                list.value,
                                { data -> vm.findPhoneNumList(data) },
                                { vm.cleanSearch() },
                                { navController.backToIndex() },
                                { data ->
                                    navController.toCall()
                                    vm.changePhoneNum(data)
                                })
                        }
                        composable(Screen.Call.name) {
                            CallScreen(vm.phoneNum.value!!) { navController.backToIndex() }
                        }
                    }
                }
            }
        }
    }
}

fun NavHostController.toSearch() {
    this.navigate(
        Screen.Search.name
    )
}

fun NavHostController.toCall() {
    this.navigate(
        Screen.Call.name
    )
}

fun NavHostController.backToIndex() {
    this.popBackStack(Screen.Index.name, false)
}

enum class Screen {
    Index,
    Search,
    Call;
}



