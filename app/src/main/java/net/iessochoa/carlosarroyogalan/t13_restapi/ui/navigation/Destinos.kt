package net.iessochoa.carlosarroyogalan.t13_restapi.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeDestination{
    const val route = "inicio"
}
@Serializable
object FavoritosDestination{
    const val route = "favoritos"
}
@Serializable
object DetalleDestination{
    const val route = "detalle/{personajeJson}"
}