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
    private val _personajesFavoritos = MutableStateFlow<List<PersonajeFavorito>>(emptyList())
    val personajesFavoritos: StateFlow<List<PersonajeFavorito>> = _personajesFavoritos
    init {
        viewModelScope.launch {
            Repository.obtenerPersonajesFavoritos().collect { personajes ->
                _personajesFavoritos.value = personajes
            }
        }
    }
    fun eliminarFavorito(personaje: Personaje) {
        viewModelScope.launch {
            val personajeFavorito = personaje.toEntity()
            Repository.eliminarFavorito(personajeFavorito)
        }
    }
}
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