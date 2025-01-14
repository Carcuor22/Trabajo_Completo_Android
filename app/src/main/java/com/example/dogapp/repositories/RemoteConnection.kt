package  com.example.dogapp.repositories


import com.example.dogapp.model.Data
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RemoteConnection {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://huachitos.cl/api/") // URL base correcta
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val remoteService: RemoteService = retrofit.create(RemoteService::class.java)


    suspend fun RecibirPerritoPorId(perroId: Int): Data? {
        return try {
            val response = remoteService.getPerrosId(perroId.toString())
            response
        } catch (e: Exception) {
            null
        }
    }
}
