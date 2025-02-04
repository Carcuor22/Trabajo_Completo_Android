package com.example.dogapp.screen


import android.graphics.Color.rgb
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dogapp.viewModel.PantallaListaPerrosViewModel


@Composable
fun PantallaListaPerrosScreen(
    navigateToDetalle: (String) -> Unit,
    navigateToInicio: () -> Unit
) {
    // Obtenemos el estado de la lista de perros y los errores desde el ViewModel
    val viewModel: PantallaListaPerrosViewModel = viewModel()
    val perrosState by viewModel.perritod.collectAsStateWithLifecycle()
    val errorState by viewModel.error.collectAsStateWithLifecycle()

    // Estructura principal de la pantalla
    Column(modifier = Modifier.fillMaxSize()) {
        // Manejo de los estados: error, cargando o mostrando los perros
        when {
            errorState != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = errorState ?: "Error desconocido",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            perrosState.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator() // Mientras carga los datos
                }
            }
            else -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 35.dp),
                    ) {
                        // Fila con un botón para regresar a la pantalla de inicio
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, bottom = 30.dp, top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            FloatingActionButton(
                                onClick = { navigateToInicio() },
                                modifier = Modifier
                            ) {
                                Text(
                                    text = "Inicio",
                                    color = Color.Red
                                )
                            }

                            // Caja centrada con el texto "Seleccione su Perrito"
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .background(
                                            color = Color(rgb(233, 206, 68)),
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .padding(top = 16.dp, end = 16.dp, bottom = 16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "Seleccione su Perrito",
                                        color = Color.Black,
                                        modifier = Modifier.padding(start = 12.dp)
                                    )
                                }
                            }
                        }

                        // Usamos LazyVerticalGrid para mostrar los animales en formato de grid
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                                .clip(RoundedCornerShape(16.dp)),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            items(perrosState) { perro ->
                                // Card para cada animal en la lista
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                        .clickable { navigateToDetalle(perro.id.toString()) }, // Redirigir a los detalles del perro
                                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .background(Color.Green)
                                            .padding(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .aspectRatio(1f)
                                        ) {
                                            Image(
                                                painter = rememberAsyncImagePainter(perro.imagen), // Cargar la imagen del perro
                                                contentDescription = perro.nombre,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .heightIn(min = 150.dp),
                                                contentScale = ContentScale.Crop
                                            )
                                        }

                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = perro.nombre,
                                            modifier = Modifier.padding(8.dp),
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = MaterialTheme.colorScheme.onBackground
                                        )

                                        Text(
                                            text = perro.desc_personalidad.take(60) + "...", // Muestra una descripción corta
                                            modifier = Modifier.padding(8.dp),
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color.Gray
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
