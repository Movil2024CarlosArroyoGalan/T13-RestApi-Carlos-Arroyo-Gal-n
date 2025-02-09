package net.iessochoa.carlosarroyogalan.t13_restapi.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
// Entidad de Room que representa la tabla "personajes_favoritos" en la base de datos
@Entity(tableName = "personajes_favoritos")
data class PersonajeFavorito(
    //Primary key definida en la vase de datos, que viene a ser el id
    @PrimaryKey
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
)