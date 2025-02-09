package net.iessochoa.carlosarroyogalan.t13_restapi.ui.screens.favoritos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.iessochoa.carlosarroyogalan.t13_restapi.data.entities.PersonajeFavorito
import net.iessochoa.carlosarroyogalan.t13_restapi.data.model.Personaje
import net.iessochoa.carlosarroyogalan.t13_restapi.data.repository.Repository
import java.util.Date
class FavoritoViewModel: ViewModel() {
    //Estado que almacena la lista de personajes favoritos
    private val _personajesFavoritos = MutableStateFlow<List<PersonajeFavorito>>(emptyList())
    val personajesFavoritos: StateFlow<List<PersonajeFavorito>> = _personajesFavoritos
    //Inicialización del ViewModel, obtiene los personajes favoritos desde el repositorio
    init {
        viewModelScope.launch {
            //Obtiene los personajes desde el repositorio
            Repository.obtenerPersonajesFavoritos().collect { personajes ->
                //Actualización del estado con la lista de personajes favoritos
                _personajesFavoritos.value = personajes
            }
        }
    }
    //Funcion para eliminar el personaje de favoritos
    fun eliminarFavorito(personaje: Personaje) {
        viewModelScope.launch {
            //Convierte el personaje en una entidad para elimnarlo de la base de datos
            val personajeFavorito = personaje.toEntity()
            //Hace llamada al repositorio para eliminarlo
            Repository.eliminarFavorito(personajeFavorito)
        }
    }
}
//Función que convierte un objeto Personaje a una entidad PersonajeFavorito
fun Personaje.toEntity(): PersonajeFavorito {
    return PersonajeFavorito(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image
    )
}
// Función que convierte una entidad PersonajeFavorito a un objeto Personaje
fun PersonajeFavorito.toModel(): Personaje {
    return Personaje(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image,
        type = "",
        created = Date()
    )
}