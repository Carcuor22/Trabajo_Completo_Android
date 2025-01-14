package com.example.dogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dogapp.model.dogapp
import com.example.dogapp.navegacion.Navegacion
import com.example.dogapp.ui.theme.DogAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogAppTheme {
                Navegacion()

            }
        }
    }
}
