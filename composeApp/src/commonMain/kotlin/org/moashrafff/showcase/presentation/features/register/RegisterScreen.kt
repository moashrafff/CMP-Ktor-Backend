package org.moashrafff.showcase.presentation.features.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.moashrafff.showcase.navigation.NavRouts

@OptIn(KoinExperimentalAPI::class)
@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel = koinViewModel()) {
    Surface(modifier = Modifier.fillMaxSize()) {

        val state = viewModel.uiState.collectAsState()

        Column(
            Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            when (val registerState = state.value) {
                RegisterState.Loading -> {
                    CircularProgressIndicator()
                    Text("Loading")
                }

                is RegisterState.Error -> {
                    Text(registerState.message)
                    Button(onClick = {
                        viewModel.retry()
                    }) {
                        Text("Retry")
                    }
                }

                RegisterState.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(NavRouts.Home.route) {
                            popUpTo(NavRouts.Register.route) {
                                inclusive = true
                            }
                        }
                    }
                }

                RegisterState.Nothing -> {
                    Column(
                        Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var email by remember { mutableStateOf("") }
                        var name by remember { mutableStateOf("") }
                        var password by remember { mutableStateOf("") }
                        var confirmPassword by remember { mutableStateOf("") }

                        Text(
                            text = "Register",
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name") },
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                        )
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text("Email") },
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                        )
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text("Password") },
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            visualTransformation = PasswordVisualTransformation()
                        )
                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            label = { Text("Confirm Password") },
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            visualTransformation = PasswordVisualTransformation()
                        )

                        Button(
                            onClick = { viewModel.register(name, email, password) },
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                            enabled = email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty() && name.isNotEmpty() && password == confirmPassword
                        ) {
                            Text("Register")
                        }
                        TextButton(onClick = { navController.popBackStack() }) {
                            Text("Already have an account? Login")
                        }
                    }
                }
            }
        }


    }
}