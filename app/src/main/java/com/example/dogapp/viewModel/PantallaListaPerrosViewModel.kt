package com.example.dogapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.dogapp.model.Data
import com.example.dogapp.repositories.RetrofitClient

class PantallaListaPerrosViewModel : ViewModel() {
    private val _perritod = MutableStateFlow<List<Data>>(emptyList())
    val perritod: StateFlow<List<Data>> = _perritod

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        obtenerPerritos()
    }

    private fun obtenerPerritos() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getAnimales() // Llamada a la API
                _perritod.value = response.data
            } catch (e: Exception) {
                _error.value = "Error al cargar los animales: ${e.message}"
            }
        }
    }
}

