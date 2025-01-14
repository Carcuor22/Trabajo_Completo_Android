package com.example.dogapp.screen

import android.graphics.Color.rgb
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dogapp.model.Data
import com.example.dogapp.repositories.repositoryList


@Composable
fun PantallaDetallePerroScreen(
    perroId: Int,
    navigateToBack: () -> Unit
) {
    // Estado para almacenar la comida seleccionada
    val perroState = remember { mutableStateOf<Data?>(null) }

    // Llamada a la función suspendida para obtener la comida seleccionada
    LaunchedEffect(perroId) {
        val perro = repositoryList.getPerritoId(perroId)
        perroState.value = perro
    }


    // Comida seleccionada cargada, mostramos los detalles
    perroState.value?.let { perro ->
        //columna centrada

        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = perro.nombre,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp).padding(top = 30.dp)
                    .background(Color(rgb(233, 206, 68)),shape = RoundedCornerShape(16.dp))
                    .padding(5.dp)
            )
            // Imagen
            AsyncImage(
                model = perro.color,
                contentDescription = "Imagen de ${perro.nombre}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
            )


            Text(
                text = "genero",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
                    .padding(top = 10.dp)

                    .background(Color(rgb(233, 206, 68)),shape = RoundedCornerShape(16.dp))
                    .padding(5.dp)

            )

            Text(
                text = "Pasos a seguir",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp).padding(top = 10.dp)
                    .background(Color(rgb(233, 206, 68)),shape = RoundedCornerShape(16.dp))
                    .padding(5.dp)
            )
                     }






        }
    }


















