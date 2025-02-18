package com.example.dogapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dogapp.viewModel.FirestoreViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarMascotaScreen(navController: NavController) {
    val viewModel: FirestoreViewModel = viewModel()

    var mascotaId by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    // Variable para el modo oscuro
    var isDarkMode by remember { mutableStateOf(false) }

    // Cambiar esquema de colores dependiendo del modo
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val buttonColor = if (isDarkMode) Color(0xFFFFA726) else Color(0xFFFFA726)
    val borderColor = if (isDarkMode) Color.Gray else Color.Black

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
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
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Editar Mascota",
                fontSize = 24.sp,
                color = textColor // Texto oscuro para mayor visibilidad
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Campo para ingresar el ID de la mascota a editar
            OutlinedTextField(
                value = mascotaId,
                onValueChange = { mascotaId = it },
                label = { Text("ID Mascota", color = textColor) },
                textStyle = LocalTextStyle.current.copy(color = textColor),
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    focusedLabelColor = textColor,
                    unfocusedLabelColor = textColor
                )
            )

            Button(
                onClick = {
                    if (mascotaId.isNotEmpty()) {
                        viewModel.getMascota(mascotaId) {
                            if (it != null) {
                                nombre = it["nombre"] as String
                                edad = (it["edad"] as Long).toString()
                                color = it["color"] as String
                                mensaje = "Mascota encontrada ✅"
                            } else {
                                mensaje = "⚠️ Mascota no encontrada"
                            }
                        }
                    } else {
                        mensaje = "⚠️ Introduce un ID válido"
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)), // Azul
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Buscar Mascota 🔍", fontSize = 18.sp, color = Color.White)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Solo se muestra si la mascota fue encontrada
            if (nombre.isNotEmpty()) {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nuevo Nombre", color = textColor) },
                    textStyle = LocalTextStyle.current.copy(color = textColor),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = borderColor,
                        focusedLabelColor = textColor,
                        unfocusedLabelColor = textColor
                    )
                )

                OutlinedTextField(
                    value = edad,
                    onValueChange = { edad = it },
                    label = { Text("Nueva Edad (Solo números)", color = textColor) },
                    textStyle = LocalTextStyle.current.copy(color = textColor),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = borderColor,
                        focusedLabelColor = textColor,
                        unfocusedLabelColor = textColor
                    )
                )

                OutlinedTextField(
                    value = color,
                    onValueChange = { color = it },
                    label = { Text("Nuevo Color", color = textColor) },
                    textStyle = LocalTextStyle.current.copy(color = textColor),
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = borderColor,
                        focusedLabelColor = textColor,
                        unfocusedLabelColor = textColor
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón para actualizar mascota
                Button(
                    onClick = {
                        val edadConvertida = edad.toIntOrNull() ?: -1

                        if (nombre.isNotEmpty() && edadConvertida >= 0 && color.isNotEmpty()) {
                            viewModel.updateMascota(mascotaId, nombre, edadConvertida, color) { success ->
                                mensaje = if (success) "Mascota actualizada con éxito 📝" else "Error al actualizar mascota ⚠️"
                            }
                        } else {
                            mensaje = "⚠️ Completa todos los campos correctamente"
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor), // Naranja
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Actualizar Mascota ✏️", fontSize = 18.sp, color = Color.White)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mensaje de éxito o error
            Text(
                mensaje,
                color = if (mensaje.contains("éxito")) Color(0xFF1B5E20) else Color(0xFFD32F2F),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Interruptor para cambiar entre modo oscuro y claro
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Modo Oscuro/Claro", color = textColor, fontSize = 18.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { isDarkMode = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = Color(0xFF4CAF50), uncheckedThumbColor = Color.Gray)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditarMascotaScreenPreview() {
    EditarMascotaScreen(navController = rememberNavController()) // Vista previa de la pantalla
}
