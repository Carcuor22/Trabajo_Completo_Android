package com.example.dogapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogapp.repositories.FirestoreRepository
import kotlinx.coroutines.launch

class FirestoreViewModel : ViewModel() {
    private val repository = FirestoreRepository()

    // 🔹 FUNCIONES PARA USUARIOS
    fun addUser(
        userId: String,
        nombre: String,
        edad: Int,
        email: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val success = repository.createUser(userId, nombre, edad, email)
            onResult(success)
        }
    }

    fun getUser(userId: String, onResult: (Map<String, Any>?) -> Unit) {
        viewModelScope.launch {
            val user = repository.getUser(userId)
            onResult(user)
        }
    }

    fun updateUser(
        userId: String,
        nombre: String,
        edad: Int,
        email: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val success = repository.updateUser(userId, nombre, edad, email)
            onResult(success)
        }
    }

    fun deleteUser(userId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = repository.deleteUser(userId)
            onResult(success)
        }
    }

    fun getAllUsers(onResult: (List<Map<String, Any>>?) -> Unit) {
        viewModelScope.launch {
            val users = repository.getAllUsers()
            onResult(users)
        }
    }

    // 🔹 FUNCIONES PARA MASCOTAS
    fun addMascota(
        mascotaId: String,
        nombre: String,
        edad: Int,
        color: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val success = repository.createPet(mascotaId, nombre, edad, color)
            onResult(success)
        }
    }

    fun getMascota(mascotaId: String, onResult: (Map<String, Any>?) -> Unit) {
        viewModelScope.launch {
            val mascota = repository.getPet(mascotaId)
            onResult(mascota)
        }
    }

    fun updateMascota(
        mascotaId: String,
        nombre: String,
        edad: Int,
        color: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val success = repository.updatePet(mascotaId, nombre, edad, color)
            onResult(success)
        }
    }

    fun deleteMascota(mascotaId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val success = repository.deletePet(mascotaId)
            onResult(success)
        }
    }

    fun getAllMascotas(onResult: (List<Map<String, Any>>?) -> Unit) {
        viewModelScope.launch {
            val mascotas = repository.getAllPets()
            onResult(mascotas)
        }
    }
}
