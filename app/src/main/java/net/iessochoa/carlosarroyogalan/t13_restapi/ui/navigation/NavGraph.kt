package net.iessochoa.carlosarroyogalan.t13_restapi.ui.navigation

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import net.iessochoa.carlosarroyogalan.t13_restapi.data.model.Personaje
import net.iessochoa.carlosarroyogalan.t13_restapi.ui.components.BarraNavegacion
import net.iessochoa.carlosarroyogalan.t13_restapi.ui.components.NavigationItem
import net.iessochoa.carlosarroyogalan.t13_restapi.ui.components.TopAppScreenBar
import net.iessochoa.carlosarroyogalan.t13_restapi.ui.screens.detalle.DetalleScreen
import net.iessochoa.carlosarroyogalan.t13_restapi.ui.screens.favoritos.FavoritesScreen
import net.iessochoa.carlosarroyogalan.t13_restapi.ui.screens.home.HomeScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    fun listaDestinos() = listOf(
        NavigationItem(//pantalla principal
            HomeDestination.route,
            "Inicio",
            Icons.Filled.Home,
            Icons.Outlined.Home
        ),
        NavigationItem(//favoritos
            FavoritosDestination.route,
            "Favoritos",
            Icons.Filled.Favorite,
            Icons.Outlined.FavoriteBorder
        )
    )
    Scaffold(
        bottomBar = {
            BarraNavegacion(
                items = listaDestinos(),
                navController = navController
            )
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = HomeDestination.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(HomeDestination.route) { HomeScreen(onPulsarPersonaje = {personaje ->
                val personajeJson = Uri.encode(personajeToJson(personaje))
                navController.navigate("detalle/$personajeJson")
                })
            }
            composable(DetalleDestination.route) { navBackStackEntry ->
                val personajeJson = navBackStackEntry.arguments?.getString("personajeJson")
                if (personajeJson != null){
                    val decodedJson = Uri.encode(personajeJson)
                    val personaje = jsonToPersonaje(decodedJson)
                    DetalleScreen(
                        personajeJson = personaje,
                        onVolver = {navController.popBackStack()}
                    )
                }
            }
            composable(FavoritosDestination.route) { FavoritesScreen() }
        }
    }
}



fun personajeToJson(personaje: Personaje): String {
    val gson = Gson()
    return gson.toJson(personaje)
}
fun jsonToPersonaje(json: String): Personaje {
    val gson = Gson()
    return gson.fromJson(json, Personaje::class.java)
}

