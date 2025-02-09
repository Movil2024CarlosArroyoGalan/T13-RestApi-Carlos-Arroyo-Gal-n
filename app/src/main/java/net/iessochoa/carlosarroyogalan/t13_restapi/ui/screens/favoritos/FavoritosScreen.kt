package net.iessochoa.carlosarroyogalan.t13_restapi.ui.screens.favoritos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import net.iessochoa.carlosarroyogalan.t13_restapi.R
import net.iessochoa.carlosarroyogalan.t13_restapi.data.model.Personaje
import net.iessochoa.carlosarroyogalan.t13_restapi.ui.screens.home.HomeViewModel

@Composable
fun FavoritesScreen(
    //ViewModel para gestionar los favoritos
    viewModel: FavoritoViewModel = viewModel(),
    //Función al clickar sobre un personaje
    onPersonajeClick: (Personaje) -> Unit = {}
) {
    //Estado del personaje obtenido desde el viewModel
    val personajeFavorito = viewModel.personajesFavoritos.collectAsState(initial = emptyList()).value
    //Contenedor principal con columna
    Column (modifier = Modifier.fillMaxSize()) {
        //Titulo
        Text(
            text = stringResource(R.string.favoritos),
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        )
        //Lista de personajes favoritos
        LazyColumn (modifier = Modifier.fillMaxSize()) {
            //Item para cada personaje diferente
            items(personajeFavorito.size) { index ->
                val personajeFavoritop = personajeFavorito[index]
                PersonajeFavoritoItem(
                    //Convierte el personaje a su modelo correspondiente
                    personaje = personajeFavoritop.toModel(),
                    //Acción al pulsar el item
                    onItemClick = {onPersonajeClick(personajeFavoritop.toModel())},
                    //Acción para eliminar el personaje de los favoritos
                    onFavoritoClick = { personaje ->
                        viewModel.eliminarFavorito(personaje)
                    }
                )
            }
        }
    }
}