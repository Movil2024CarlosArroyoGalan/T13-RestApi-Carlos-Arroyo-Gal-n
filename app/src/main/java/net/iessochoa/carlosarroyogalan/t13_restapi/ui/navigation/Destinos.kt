package net.iessochoa.carlosarroyogalan.t13_restapi.ui.navigation

import kotlinx.serialization.Serializable
//Objeto para la navegación al inicio con la lista de personajes
@Serializable
object HomeDestination{
    const val route = "inicio"
}
//Objeto de nagevación para cambiar a la pantalla de favoritos
@Serializable
object FavoritosDestination{
    const val route = "favoritos"
}
//Dataclass que nos permitirá convertir los objetos json en la clase DetalleScreen
@Serializable
data class DetallesDestination(val personaje: String)