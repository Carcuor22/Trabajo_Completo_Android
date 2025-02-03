package com.example.dogapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

object AuthScreens {

    @Composable
    fun LoginScreen(viewModel: AuthViewModel, navController: NavController) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf<String?>(null) }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.login(email, password) { success, error ->
                    if (success) {
                        navController.navigate("register") // Navega a la pantalla de registro o siguiente
                    } else {
                        errorMessage = error
                    }
                }
            }) {
                Text("Login")
            }
            errorMessage?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        }
    }

    @Composable
    fun RegisterScreen(viewModel: AuthViewModel, navController: NavController) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf<String?>(null) }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if (password == confirmPassword) {
                    viewModel.register(email, password) { success, error ->
                        if (success) {
                            navController.navigate("login") // Navega a la pantalla de inicio de sesión
                        } else {
                            errorMessage = error
                        }
                    }
                } else {
                    errorMessage = "Passwords do not match"
                }
            }) {
                Text("Register")
            }
            errorMessage?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        }
    }

    @Composable
    fun ResetPasswordScreen(viewModel: AuthViewModel, navController: NavController) {
        var email by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf<String?>(null) }

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            TextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.resetPassword(email) { success, error ->
                    if (success) {
                        errorMessage = "Password reset email sent!"
                        navController.navigate("login") // Opcional: Regresar a la pantalla de inicio de sesión
                    } else {
                        errorMessage = error
                    }
                }
            }) {
                Text("Reset Password")
            }
            errorMessage?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        }
    }
}
