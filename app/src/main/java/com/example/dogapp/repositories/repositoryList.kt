package  com.example.dogapp.repositories

import com.example.dogapp.model.Data


object repositoryList {

    suspend fun getListasPerros(): List<Data> {
        return try {
            val response = RemoteConnection.remoteService.getObtenerTodosPerros()
            response.perros

        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }




    suspend fun getPerritoId(idPerro: Int): Data? {
        return try {
            val response = getListasPerros()
            return response.find { it.id == idPerro }
        } catch (e: Exception) {
            e.printStackTrace()
            null // Devuelve null en caso de error
        }
    }
}








