package com.example.dogapp.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun PantallaInicioScreen(navegarAPantallaListaComidas: () -> Unit) {
    // Variables para el estado de los campos de texto
    var usuario = remember { androidx.compose.runtime.mutableStateOf("") }
    var contrasena = remember { androidx.compose.runtime.mutableStateOf("") }

    // Aquí definimos el diseño de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).padding(bottom = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Organiza el contenido verticalmente
    ) {
        AsyncImage(
            model = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.freepik.es%2Ffotos-vectores-gratis%2Fperro&psig=AOvVaw3sMs1G9t2E9OU3cjHqjS5S&ust=1736881246804000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCOCGv4Kx84oDFQAAAAAdAAAAABAE",
            contentDescription = "Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .padding(8.dp)
        )
        // Título
        Text(
            text = "Tus Perritos",
            fontSize = 32.sp,
            color = Color.Black,
            style = MaterialTheme.typography.headlineLarge,
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo para el nombre de usuario
        Text("Usuario:")
        TextField(
            value = usuario.value,
            onValueChange = { usuario.value = it },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            placeholder = { Text("Introduce tu usuario")}
        )

        // Campo para la contraseña
        Text("Contraseña:")
        TextField(
            value = contrasena.value,
            onValueChange = { contrasena.value = it },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            placeholder = { Text("Introduce tu contraseña") },
            visualTransformation = androidx.compose.ui.text.input.PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para navegar a la siguiente pantalla
        Button(
            onClick = {
                // Aquí puedes agregar validación si lo necesitas
                if (usuario.value.isNotEmpty() && contrasena.value.isNotEmpty()) {
                    navegarAPantallaListaComidas()
                }
            },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text(text = "Iniciar sesión")
        }
    }
}