package com.example.dogapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage

@Composable
fun PantallaInicioPostLogin(navController: NavController) {
    // Fondo de la imagen de una mascota graciosa
    val fondoImagenUrl = "https://images.pexels.com/photos/5122180/pexels-photo-5122180.jpeg" // URL de la imagen

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), // Fondo blanco predeterminado
        contentAlignment = Alignment.Center
    ) {
        // Fondo con la imagen de la mascota
        AsyncImage(
            model = fondoImagenUrl,
            contentDescription = "Fondo Mascota",
            modifier = Modifier
                .fillMaxSize() // La imagen ocupa toda la pantalla
                .align(Alignment.Center),
            contentScale = androidx.compose.ui.layout.ContentScale.Crop // Asegura que se recorte correctamente
        )

        // Degradado para mejorar la visibilidad del texto
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000))
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Button(
                    onClick = { navController.popBackStack() }, // Regresa a la pantalla anterior
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("⬅ Volver", color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Bienvenido",
                fontSize = 28.sp,
                color = Color.White, // Texto blanco para mayor contraste
                style = TextStyle(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón de Usuarios
            Button(
                onClick = { navController.navigate("usuarios") },  // Navega a PantallaFirestore
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),  // Verde
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Gestionar Usuarios", fontSize = 18.sp, color = Color.White)
            }

            // Botón de Mascotas
            Button(
                onClick = { navController.navigate("mascotas") },  // Aquí puedes agregar la pantalla de Mascotas
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),  // Azul
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Gestionar Mascotas", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}


@Composable
fun PantallaInicioPostLoginPreview() {
    PantallaInicioPostLogin(navController = rememberNavController()) // Preview de la pantalla
}
