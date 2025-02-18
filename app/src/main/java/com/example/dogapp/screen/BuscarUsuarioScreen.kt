package com.example.dogapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dogapp.viewModel.FirestoreViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscarUsuarioScreen(navController: NavController) {
    val viewModel: FirestoreViewModel = viewModel()

    var searchQuery by remember { mutableStateOf("") }
    var usuarios by remember { mutableStateOf<List<Map<String, Any>>>(emptyList()) }
    var mensaje by remember { mutableStateOf("") }

    // Modo oscuro y claro
    var isDarkMode by remember { mutableStateOf(false) }

    // Cambiar esquema de colores según el modo
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val cardColor = if (isDarkMode) Color(0xFF2C2C2C) else Color(0xFFF5F5F5)

    // Cargar todos los usuarios al iniciar la pantalla
    LaunchedEffect(Unit) {
        viewModel.getAllUsers { result ->
            usuarios = result ?: emptyList()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor), // Fondo cambia según el modo
        contentAlignment = Alignment.TopStart
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start // 🔹 Alinea el botón a la izquierda
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = textColor
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Título
            Text(
                "Buscar Usuario",
                fontSize = 24.sp,
                color = textColor // Color de texto según el modo
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de búsqueda
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Buscar por ID o Nombre", color = textColor) },
                textStyle = LocalTextStyle.current.copy(color = textColor),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = textColor,
                    unfocusedBorderColor = textColor,
                    focusedLabelColor = textColor,
                    unfocusedLabelColor = textColor
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de usuarios filtrada por la búsqueda
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val usuariosFiltrados = usuarios.filter {
                    it["userId"].toString().contains(searchQuery, ignoreCase = true) ||
                            it["nombre"].toString().contains(searchQuery, ignoreCase = true)
                }

                items(usuariosFiltrados) { usuario ->
                    UsuarioCard(usuario, cardColor, textColor)
                }

                if (usuariosFiltrados.isEmpty()) {
                    item {
                        Text("No se encontraron usuarios.", color = Color.Gray, fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

        }

        // Botón para alternar entre Modo Oscuro/Claro en la esquina superior derecha
        IconButton(
            onClick = { isDarkMode = !isDarkMode },
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Brightness6,
                contentDescription = "Modo Oscuro/Claro",
                tint = if (isDarkMode) Color.Yellow else Color.Gray
            )
        }
    }
}

@Composable
fun UsuarioCard(usuario: Map<String, Any>, cardColor: Color, textColor: Color) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text("📛 Nombre: ${usuario["nombre"]}", fontSize = 18.sp, color = textColor)
            Text("📅 Edad: ${usuario["edad"]} años", fontSize = 18.sp, color = textColor)
            Text("📧 Email: ${usuario["email"]}", fontSize = 18.sp, color = textColor)
        }
    }
}
