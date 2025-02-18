package com.example.dogapp.screen

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.dogapp.viewModel.AuthViewModel

import android.util.Log

@Composable
fun LoginScreen(
    viewModel: AuthViewModel,
    navegarARegistro: () -> Unit,
    navegarAResetPassword: () -> Unit,
    navegarAHome: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Launcher para el resultado de Google Sign-In
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d("LoginScreen", "Result code: ${result.resultCode}")  // Log para ver el código de resultado
        if (result.resultCode == Activity.RESULT_OK) {
            viewModel.handleGoogleSignIn(result.data) { success, error ->
                if (success) {
                    Log.d("LoginScreen", "Google sign-in successful")
                    navegarAHome() // Navegar a la pantalla de inicio
                } else {
                    Log.e("LoginScreen", "Google sign-in error: $error")
                    errorMessage = error
                }
            }
        } else {
            Log.e("LoginScreen", "Google sign-in failed with result code: ${result.resultCode}")
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        AsyncImage(
            model = "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg",
            contentDescription = "Fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Degradado para mejorar visibilidad
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000))
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("🔑 Iniciar Sesión", fontSize = 28.sp, color = Color.White)

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    Log.d("LoginScreen", "Attempting login with email: $email")  // Log cuando el usuario hace clic en "Login"
                    viewModel.login(email, password) { success, _ ->
                        if (success) {
                            Log.d("LoginScreen", "Login successful")
                            navegarAHome()
                        } else {
                            Log.e("LoginScreen", "Login failed")
                            errorMessage = "Login failed. Please try again."
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Entrar 🚀", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de Google
            Button(
                onClick = {
                    val requestCode = 1 // El valor del requestCode
                    Log.d("LoginScreen", "Attempting Google sign-in with request code: $requestCode")
                    val signInIntent = viewModel.signInWithGoogle(requestCode) // Llama a la función pasando el requestCode
                    googleSignInLauncher.launch(signInIntent)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Iniciar con Google", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = { navegarARegistro() }) {
                Text("¿No tienes cuenta? Regístrate aquí", color = Color.White)
            }

            TextButton(onClick = { navegarAResetPassword() }) {
                Text("¿Olvidaste tu contraseña?", color = Color.White)
            }

            // Mostrar errores
            errorMessage?.let {
                Log.e("LoginScreen", "Error: $it")  // Log para mostrar el error
                Text(it, color = Color.Red)
            }
        }
    }
}
