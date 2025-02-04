package com.example.dogapp.viewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Función para iniciar sesión con correo y contraseña.
     */
    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null) // Login exitoso
                } else {
                    onResult(false, task.exception?.message) // Error en el login
                }
            }
    }

    /**
     * Función para registrar un nuevo usuario con correo y contraseña.
     */
    fun register(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null) // Registro exitoso
                } else {
                    onResult(false, task.exception?.message) // Error en el registro
                }
            }
    }

    /**
     * Función para enviar un correo de restablecimiento de contraseña.
     */
    fun resetPassword(email: String, onResult: (Boolean, String?) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, "Correo de restablecimiento enviado con éxito.") // Éxito
                } else {
                    onResult(false, task.exception?.message) // Error al enviar el correo
                }
            }
    }

    /**
     * Función para iniciar sesión con Google.
     */
    fun googleSignIn(idToken: String, onResult: (Boolean, String?) -> Unit) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null) // Inicio de sesión con Google exitoso
                } else {
                    onResult(false, task.exception?.message) // Error en el inicio con Google
                }
            }
    }
}
