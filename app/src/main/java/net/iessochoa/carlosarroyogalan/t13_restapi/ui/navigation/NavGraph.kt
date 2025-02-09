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
import androidx.navigation.toRoute
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
    //Controlador para gestionar las pantallas
    val navController = rememberNavController()
    //Funcion que devuelve la lista principal para navegar por las distintas pantallas
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
    //Barra de navegación inferior que nos permite navegar entre las dos pantallas
    Scaffold(
        bottomBar = {
            BarraNavegacion(
                items = listaDestinos(),
                navController = navController
            )
        }
    ) { padding ->
        //Controlador de la navegación
        NavHost(
            navController = navController,
            startDestination = HomeDestination.route,
            modifier = Modifier.padding(padding)
        ) {
            //Pantalla de inicio
            composable(HomeDestination.route) { HomeScreen(onPulsarPersonaje = {personaje ->
                //Convertimos el personaje a formato Json
                val personajeJson = personajeToJson(personaje)
                //Navegación a la pantalla detalle desde el HomeScreen
                navController.navigate(DetallesDestination(personajeJson))
                })
            }
            //Pantalla Detalle
            composable<DetallesDestination> {navBackStackEntry ->
                //Ruta de detalles
                val detallesDestination: DetallesDestination = navBackStackEntry.toRoute()
                //Convierte el Json a Personaje
                val personaje = jsonToPersonaje(detallesDestination.personaje)
                DetalleScreen(
                    //El personaje es pasado a la Screen
                    personajeJson = personaje,
                    //La acción de vuelta a la pantalla anterior
                    onVolver = {navController.popBackStack()}
                )
            }
            //Pantalla Favoritos
            composable(FavoritosDestination.route) {
                FavoritesScreen(onPersonajeClick = { personaje ->
                    //Convertimos el personaje a formato Json
                    val personajeJson = personajeToJson(personaje)
                    //Navegación a la pantalla detalle desde FavoritosScreen
                    navController.navigate(DetallesDestination(personajeJson))
                })
            }
        }
    }
}
//Funcion para hacer un convert Personaje a JSON
fun personajeToJson(personaje: Personaje): String {
    //Instancia de Gson para convertir objetos a Json
    val gson = Gson()
    //Convierte el personaje a Json
    return gson.toJson(personaje)
}
//Funcion para convertir un Json a personaje
fun jsonToPersonaje(json: String): Personaje {
    //Instancia de Gson para convertir objetos a Json
    val gson = Gson()
    //Convierte el Json a personaje
    return gson.fromJson(json, Personaje::class.java)
}

