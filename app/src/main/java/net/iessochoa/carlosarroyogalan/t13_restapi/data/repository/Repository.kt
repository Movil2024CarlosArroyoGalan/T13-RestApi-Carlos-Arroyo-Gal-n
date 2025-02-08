package net.iessochoa.carlosarroyogalan.t13_restapi.data.repository

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.flow.Flow
import net.iessochoa.carlosarroyogalan.t13_restapi.data.api.ApiRickMortyPagingSource
import net.iessochoa.carlosarroyogalan.t13_restapi.data.database.PersonajesDataBase
import net.iessochoa.carlosarroyogalan.t13_restapi.data.entities.PersonajeFavorito

object Repository {
    fun getPersonajesApiPagingSource()= ApiRickMortyPagingSource()

    private lateinit var database: PersonajesDataBase

    fun iniciarDataBase(context: Context){
        database = Room.databaseBuilder(
            context,
            PersonajesDataBase::class.java,
            "personajes_database"
        ).build()
    }
    fun obtenerPersonajesFavoritos(): Flow<List<PersonajeFavorito>> {
        return database.PersonajeDao().getAllFavoritos()
    }
    fun obtenerIdPersonajeFavorito(): Flow<List<Int>>{
        return database.PersonajeDao().getIdPersonajeFavorito()
    }
    suspend fun añadirFavorito(personaje: PersonajeFavorito){
        database.PersonajeDao().añadirPersonaje(personaje)
    }
    suspend fun eliminarFavorito(personaje: PersonajeFavorito){
        database.PersonajeDao().borrarPersonaje(personaje)
    }

}