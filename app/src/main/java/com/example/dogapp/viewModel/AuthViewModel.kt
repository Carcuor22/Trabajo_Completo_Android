package com.example.dogapp.viewModel

import android.app.Application
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class AuthViewModel(activity: Application) : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val activity = activity



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
    fun signInWithGoogle(requestCode: Int): Intent {
        // Configura Google SignIn
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("168314899217-mba5r3aqrcagkchnjrs1d6trocs2rlbv.apps.googleusercontent.com") // Sustituir por tu ID de cliente
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(activity, gso)
        return googleSignInClient.signInIntent
    }

    /**
     * Función para manejar el resultado del inicio de sesión con Google.
     */
    fun handleGoogleSignIn(data: Intent?, onResult: (Boolean, String?) -> Unit) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            // Autenticación con Firebase
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        onResult(true, null) // Login exitoso con Google
                    } else {
                        onResult(false, task.exception?.message) // Error en el login
                    }
                }
        } catch (e: ApiException) {
            onResult(false, "Google sign in failed: ${e.message}")
        }
    }
}
