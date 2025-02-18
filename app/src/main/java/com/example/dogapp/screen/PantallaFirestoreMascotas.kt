package com.example.dogapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import com.example.dogapp.viewModel.FirestoreViewModel

@Composable
fun PantallaMascotas(navController: NavController, viewModel: FirestoreViewModel = viewModel()) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // 🔹 Imagen de fondo cubriendo toda la pantalla
        AsyncImage(
            model = "https://images.pexels.com/photos/23383655/pexels-photo-23383655/free-photo-of-jugando-hierba-cesped-perros.jpeg",
            contentDescription = "Fondo Mascotas",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // 🔹 Degradado para mejorar visibilidad del contenido
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
                "Gestión de Mascotas",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 🔹 Botón para agregar mascota
            BotonMascotas(
                text = "Agregar Mascota",
                icono = Icons.Default.Add,
                color = Color(0xFF4CAF50),
                onClick = { navController.navigate("agregar_mascota") }
            )

            // 🔹 Botón para editar mascota
            BotonMascotas(
                text = "Editar Mascota",
                icono = Icons.Default.Edit,
                color = Color(0xFFFFA726),
                onClick = { navController.navigate("editar_mascota") }
            )

            // 🔹 Botón para eliminar mascota
            BotonMascotas(
                text = "Eliminar Mascota",
                icono = Icons.Default.Delete,
                color = Color(0xFFD32F2F),
                onClick = { navController.navigate("borrar_mascota") }
            )

            // 🔹 Botón para buscar mascota
            BotonMascotas(
                text = "Buscar Mascota",
                icono = Icons.Default.Search,
                color = Color(0xFF1976D2),
                onClick = { navController.navigate("buscar_mascota") }
            )
        }
    }
}

@Composable
fun BotonMascotas(text: String, icono: androidx.compose.ui.graphics.vector.ImageVector, color: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(56.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(16.dp)
    ) {
        Icon(icono, contentDescription = text, tint = Color.White, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, color = Color.White, style = MaterialTheme.typography.titleMedium)
    }
}
