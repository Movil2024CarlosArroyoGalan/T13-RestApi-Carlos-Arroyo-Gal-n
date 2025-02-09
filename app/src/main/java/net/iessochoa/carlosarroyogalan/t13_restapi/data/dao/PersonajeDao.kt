package net.iessochoa.carlosarroyogalan.t13_restapi.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import net.iessochoa.carlosarroyogalan.t13_restapi.data.entities.PersonajeFavorito
import net.iessochoa.carlosarroyogalan.t13_restapi.data.model.Personaje
//Clase dao que define el crud
@Dao
interface PersonajeDao {
    //Insert para añadir el personaje en la FavoritosScreen
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun añadirPersonaje(personaje: PersonajeFavorito)
    //Delete para eliminar el personaje de la FavoritoScreen
    @Delete
    suspend fun borrarPersonaje(personaje: PersonajeFavorito)
    //Query para seleccionar todos los personajes que hayan en favoritos y mostrarlas
    @Query("SELECT * FROM personajes_favoritos")
    fun getAllFavoritos(): Flow<List<PersonajeFavorito>>
    //Query que nos permitirá seleccionar el id para añadirlo
    @Query("SELECT id FROM personajes_favoritos")
    fun getIdPersonajeFavorito(): Flow<List<Int>>
}