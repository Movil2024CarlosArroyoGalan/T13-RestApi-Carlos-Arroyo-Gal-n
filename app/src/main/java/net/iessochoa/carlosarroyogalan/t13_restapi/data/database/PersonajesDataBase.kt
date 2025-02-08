package net.iessochoa.carlosarroyogalan.t13_restapi.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import net.iessochoa.carlosarroyogalan.t13_restapi.data.dao.PersonajeDao
import net.iessochoa.carlosarroyogalan.t13_restapi.data.db.TransformaFechaSQLite
import net.iessochoa.carlosarroyogalan.t13_restapi.data.entities.PersonajeFavorito
import net.iessochoa.carlosarroyogalan.t13_restapi.data.model.Personaje

@Database(entities = arrayOf(PersonajeFavorito::class), version = 1, exportSchema = false)
@TypeConverters(TransformaFechaSQLite::class)
public abstract class PersonajesDataBase : RoomDatabase() {
    abstract fun PersonajeDao(): PersonajeDao
}