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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dogapp.viewModel.FirestoreViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarUsuarioScreen(navController: NavController) {
    val viewModel: FirestoreViewModel = viewModel()

    var userId by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    // Variable para el modo oscuro
    var isDarkMode by remember { mutableStateOf(false) }

    // Cambiar esquema de colores dependiendo del modo
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val buttonColor = if (isDarkMode) Color(0xFF4CAF50) else Color(0xFF388E3C)
    val borderColor = if (isDarkMode) Color.Gray else Color.Black

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agregar Usuario", color = textColor) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = textColor
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = backgroundColor)
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor)
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Campos de texto con color de texto según el modo
                    OutlinedTextField(
                        value = userId,
                        onValueChange = { userId = it },
                        label = { Text("ID Usuario", color = textColor) },
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
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre", color = textColor) },
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
                        label = { Text("Edad (Solo números)", color = textColor) },
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
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Correo Electrónico", color = textColor) },
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

                    // Botón estilizado para agregar usuario
                    Button(
                        onClick = {
                            val edadConvertida = edad.toIntOrNull() ?: -1

                            if (userId.isNotEmpty() && nombre.isNotEmpty() && edadConvertida >= 0 && email.isNotEmpty()) {
                                viewModel.addUser(userId, nombre, edadConvertida, email) { success ->
                                    mensaje = if (success) "Usuario agregado con éxito 🎉" else "Error al agregar usuario ❌"
                                }
                            } else {
                                mensaje = "⚠️ Completa todos los campos correctamente"
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Agregar Usuario ✅", fontSize = 18.sp, color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Mensaje de éxito o error con colores adecuados
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
    )
}

@Preview(showBackground = true)
@Composable
fun AgregarUsuarioScreenPreview() {
    AgregarUsuarioScreen(navController = rememberNavController()) // Vista previa de la pantalla
}
