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
fun BorrarMascotaScreen(navController: NavController) {
    val viewModel: FirestoreViewModel = viewModel()

    var mascotaId by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    // Variable para el modo oscuro
    var isDarkMode by remember { mutableStateOf(false) }

    // Cambiar esquema de colores dependiendo del modo
    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black
    val buttonColor = Color(0xFFD32F2F) // Rojo para eliminar
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
                "Eliminar Mascota",
                fontSize = 24.sp,
                color = textColor // Texto que cambia con el modo oscuro
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Campo para ingresar el ID de la mascota a eliminar
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

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para eliminar mascota
            Button(
                onClick = {
                    if (mascotaId.isNotEmpty()) {
                        viewModel.deleteMascota(mascotaId) { success ->
                            mensaje = if (success) "Mascota eliminada con éxito ❌" else "Error al eliminar mascota ⚠️"
                        }
                    } else {
                        mensaje = "⚠️ Introduce un ID válido"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor), // Rojo para indicar eliminación
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Eliminar Mascota ❌", fontSize = 18.sp, color = Color.White)
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
fun BorrarMascotaScreenPreview() {
    BorrarMascotaScreen(navController = rememberNavController()) // Vista previa de la pantalla
}
