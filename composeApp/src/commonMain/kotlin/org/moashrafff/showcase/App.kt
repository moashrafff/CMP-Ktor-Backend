package org.moashrafff.showcase

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.koin.compose.getKoin
import org.moashrafff.showcase.data.cache.ShowcaseSession
import org.moashrafff.showcase.navigation.NavRouts
import org.moashrafff.showcase.presentation.features.login.LoginScreen
import org.moashrafff.showcase.presentation.features.register.RegisterScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val session: ShowcaseSession = getKoin().get()
        val currentScreen = remember { mutableStateOf<String?>(null) }
        val coroutineScope = rememberCoroutineScope()
        coroutineScope.launch {
            if (session.getToken() != null) {
                currentScreen.value = NavRouts.Home.route
            } else {
                currentScreen.value = NavRouts.Login.route
            }
            currentScreen.value = NavRouts.Login.route


        }
        val navController = rememberNavController()
        currentScreen.value?.let {
            NavHost(navController, startDestination = it) {
                composable(NavRouts.Login.route) {
                    LoginScreen(navController)
                }
                composable(NavRouts.Register.route) {
                    RegisterScreen(navController)
                }
                composable(NavRouts.Home.route) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Home")
                    }
                }
                composable(NavRouts.Loading.route) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                        Text("Loading...")
                    }
                }
            }
        }

    }
}