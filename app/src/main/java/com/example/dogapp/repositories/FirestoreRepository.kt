package com.example.dogapp.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreRepository {
    private val db = FirebaseFirestore.getInstance()

    // Crear un usuario
    suspend fun createUser(userId: String, nombre: String, edad: Int, email: String): Boolean {
        return try {
            val user = hashMapOf(
                "userId" to userId,
                "nombre" to nombre,
                "edad" to edad,
                "email" to email
            )
            db.collection("Usuarios").document(userId).set(user).await()
            Log.d("Firestore", "✅ Usuario agregado con éxito: $user")
            true
        } catch (e: Exception) {
            Log.e("Firestore", "❌ Error al agregar usuario: ${e.message}")
            false
        }
    }

    // Buscar usuario por ID
    suspend fun getUser(userId: String): Map<String, Any>? {
        return try {
            val document = db.collection("Usuarios").document(userId).get().await()
            if (document.exists()) document.data else null
        } catch (e: Exception) {
            null
        }
    }

    // Actualizar usuario
    suspend fun updateUser(userId: String, nombre: String, edad: Int, email: String): Boolean {
        return try {
            db.collection("Usuarios").document(userId)
                .update(mapOf("nombre" to nombre, "edad" to edad, "email" to email)).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    // Eliminar usuario
    suspend fun deleteUser(userId: String): Boolean {
        return try {
            db.collection("Usuarios").document(userId).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }

    // Obtener todos los usuarios
    suspend fun getAllUsers(): List<Map<String, Any>>? {
        return try {
            val snapshot = db.collection("Usuarios").get().await()
            snapshot.documents.mapNotNull { it.data }
        } catch (e: Exception) {
            null
        }
    }

    // -------------------------------------------
    // Aquí empiezan las funciones de las mascotas
    // -------------------------------------------

    // Crear una mascota
    suspend fun createPet(petId: String, nombre: String, edad: Int, color: String): Boolean {
        return try {
            val pet = hashMapOf(
                "petId" to petId,
                "nombre" to nombre,
                "edad" to edad,
                "color" to color
            )
            db.collection("Mascotas").document(petId).set(pet).await()
            Log.d("Firestore", "✅ Mascota agregada con éxito: $pet")
            true
        } catch (e: Exception) {
            Log.e("Firestore", "❌ Error al agregar mascota: ${e.message}")
            false
        }
    }

    // Buscar mascota por ID
    suspend fun getPet(petId: String): Map<String, Any>? {
        return try {
            val document = db.collection("Mascotas").document(petId).get().await()
            if (document.exists()) document.data else null
        } catch (e: Exception) {
            null
        }
    }

    // Actualizar mascota
    suspend fun updatePet(petId: String, nombre: String, edad: Int, color: String): Boolean {
        return try {
            db.collection("Mascotas").document(petId)
                .update(mapOf("nombre" to nombre, "edad" to edad, "color" to color)).await()
            true
        } catch (e: Exception) {
            false
        }
    }

    // Eliminar mascota
    suspend fun deletePet(petId: String): Boolean {
        return try {
            db.collection("Mascotas").document(petId).delete().await()
            true
        } catch (e: Exception) {
            false
        }
    }

    // Obtener todas las mascotas
    suspend fun getAllPets(): List<Map<String, Any>>? {
        return try {
            val snapshot = db.collection("Mascotas").get().await()
            snapshot.documents.mapNotNull { it.data }
        } catch (e: Exception) {
            null
        }
    }
}
