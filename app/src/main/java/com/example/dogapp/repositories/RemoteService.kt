package  com.example.dogapp.repositories


import com.example.dogapp.model.Data
import com.example.dogapp.model.perros
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteService {

        // Obtener todas las comidas (con un query opcional)
        @GET("search.php")
        suspend fun getObtenerTodosPerros(@Query("s") query: String = ""): perros

        // Obtener una comida por su ID
        @GET("search.php/{id}")
        suspend fun getPerrosId(@Path("id") comidaId: String): Data
}
