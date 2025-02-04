package com.example.dogapp.repositories
import com.example.dogapp.model.Data
import com.example.dogapp.model.dogapp
import retrofit2.http.GET

interface ApiService {
    @GET("animales") // Asegúrate de que el endpoint es correcto
    suspend fun getAnimales(): dogapp
}
