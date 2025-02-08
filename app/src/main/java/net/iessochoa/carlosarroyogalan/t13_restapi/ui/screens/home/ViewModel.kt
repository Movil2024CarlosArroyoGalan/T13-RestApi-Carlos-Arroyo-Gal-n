package net.iessochoa.carlosarroyogalan.t13_restapi.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import net.iessochoa.carlosarroyogalan.t13_restapi.data.entities.PersonajeFavorito
import net.iessochoa.carlosarroyogalan.t13_restapi.data.model.Personaje
import net.iessochoa.carlosarroyogalan.t13_restapi.data.repository.Repository

class HomeViewModel : ViewModel() {
    //-------------Paginación----------------
    //los items por página. Creo que no tiene efecto en nuestro caso, ya que el servicio trae páginas de 20 en 20
    val MAX_ITEMS=10
    //cuando carga mas items. Si haces scroll y te quedan 3 items, carga más
    val PREFETCH_DISTANCE=3
    val personajes = Pager(
        config = PagingConfig(
            pageSize = MAX_ITEMS,
            prefetchDistance = PREFETCH_DISTANCE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            Repository.getPersonajesApiPagingSource()
        }
    )//devolvemos una flow de la paginación
        .flow
    //para mantener el estado de paginación a través de los cambios de configuración o navegación
        .cachedIn(viewModelScope)
    private val idPersonajeFavorito = MutableStateFlow<List<Int>>(emptyList())
    val idFavorito: StateFlow<List<Int>> = idPersonajeFavorito
    init {
        viewModelScope.launch {
            Repository.obtenerIdPersonajeFavorito().collect { ids ->
                idPersonajeFavorito.value = ids
            }
        }
    }
    fun activarFavorito(personaje: Personaje) {
        viewModelScope.launch {
            if (idPersonajeFavorito.value.contains(personaje.id)) {
                Repository.eliminarFavorito(personaje.toEntity())
            } else {
                Repository.añadirFavorito(personaje.toEntity())
            }
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
