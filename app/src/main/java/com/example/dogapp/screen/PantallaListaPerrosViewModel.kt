package com.example.dogapp.screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogapp.model.Data
import com.example.dogapp.repositories.repositoryList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PantallaListaPerrosViewModel : ViewModel() {

    // Flow para la lista de comidas
    private val _perros = MutableStateFlow<List<Data>>(emptyList())
    val perritod: StateFlow<List<Data>> = _perros

    // Flow para el error
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        obtenerPerritos()
    }

    private fun obtenerPerritos() {
        viewModelScope.launch {
            try {
                val listaPerros = repositoryList.getListasPerros()
                _perros.value = listaPerros
                if (listaPerros.isEmpty()) {
                    _error.value = "No se encontraron comidas."
                }
            } catch (e: Exception) {
                _error.value = "Error al obtener las comidas: ${e.localizedMessage}"
                _perros.value = emptyList() // En caso de error, vaciar lista
            }
        }
    }
}
