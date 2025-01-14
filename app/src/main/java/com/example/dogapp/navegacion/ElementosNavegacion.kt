package com.example.dogapp.navegacion

import kotlinx.serialization.Serializable

@Serializable
object VistaPerros

@Serializable
object ListaPerros

@Serializable
data class PantallaDetallePerro(val id: Int)