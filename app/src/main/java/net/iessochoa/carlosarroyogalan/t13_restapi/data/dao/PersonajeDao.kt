package net.iessochoa.carlosarroyogalan.t13_restapi.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.iessochoa.carlosarroyogalan.t13_restapi.data.entities.PersonajeFavorito
import net.iessochoa.carlosarroyogalan.t13_restapi.data.model.Personaje

@Dao
interface PersonajeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun añadirPersonaje(personaje: PersonajeFavorito)
    @Delete
    suspend fun borrarPersonaje(personaje: PersonajeFavorito)
    @Query("SELECT * FROM personajes_favoritos")
    fun getAllFavoritos(): Flow<List<PersonajeFavorito>>
    @Query("SELECT id FROM personajes_favoritos")
    fun getIdPersonajeFavorito(): Flow<List<Int>>
}