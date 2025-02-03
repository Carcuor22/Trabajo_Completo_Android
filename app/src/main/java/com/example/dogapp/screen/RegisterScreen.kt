package com.example.dogapp.screen

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

@Composable
fun RegisterScreen(
    viewModel: AuthViewModel,
    navegarALogin: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) } // Para mostrar errores

    Box(modifier = Modifier.fillMaxSize()) {
        // Imagen de fondo
        AsyncImage(
            model = "https://images.pexels.com/photos/59523/pexels-photo-59523.jpeg",
            contentDescription = "Fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Degradado
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
            Text("📝 Registro", fontSize = 28.sp, color = Color.White)

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

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirmar Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de registro con validación
            Button(
                onClick = {
                    if (password == confirmPassword) {
                        viewModel.register(email, password) { success, error ->
                            if (!success) {
                                errorMessage = error
                            } else {
                                errorMessage = null // Registro exitoso
                            }
                        }
                    } else {
                        errorMessage = "Las contraseñas no coinciden"
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF43A047)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Registrarse 🐶", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            errorMessage?.let {
                Text(it, color = Color.Red, fontSize = 14.sp) // Mostrar error si lo hay
            }

            TextButton(onClick = { navegarALogin() }) {
                Text("¿Ya tienes cuenta? Inicia sesión aquí", color = Color.White)
            }
        }
    }
}
