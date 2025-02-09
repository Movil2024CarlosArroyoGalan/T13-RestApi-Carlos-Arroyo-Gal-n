package net.iessochoa.carlosarroyogalan.t13_restapi.data.repository

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.flow.Flow
import net.iessochoa.carlosarroyogalan.t13_restapi.data.api.ApiRickMortyPagingSource
import net.iessochoa.carlosarroyogalan.t13_restapi.data.database.PersonajesDataBase
import net.iessochoa.carlosarroyogalan.t13_restapi.data.entities.PersonajeFavorito

object Repository {
    //Devuelve la fuente de paginación de la API para obtener personajes
    fun getPersonajesApiPagingSource()= ApiRickMortyPagingSource()
    //Base de datos de los personajes
    private lateinit var database: PersonajesDataBase
    //Inicialización de la base de datos usando room y la clase PersonajesDataBase
    fun iniciarDataBase(context: Context){
        database = Room.databaseBuilder(
            context,
            PersonajesDataBase::class.java,
            "personajes_database"
        ).build()
    }
    //Devuelve un flujo de la lista de personajes favoritos desde la base de datos
    fun obtenerPersonajesFavoritos(): Flow<List<PersonajeFavorito>> {
        return database.PersonajeDao().getAllFavoritos()
    }
    //Devuelve un flujo de la lista de IDs de personajes favoritos
    fun obtenerIdPersonajeFavorito(): Flow<List<Int>>{
        return database.PersonajeDao().getIdPersonajeFavorito()
    }
    //Funcion suspend que añade el personajeFavorito a la base
    suspend fun añadirFavorito(personaje: PersonajeFavorito){
        database.PersonajeDao().añadirPersonaje(personaje)
    }
    //Función suspend que elimina al personajeFavorito de la base
    suspend fun eliminarFavorito(personaje: PersonajeFavorito){
        database.PersonajeDao().borrarPersonaje(personaje)
    }

}