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
    viewModel: FavoritoViewModel = viewModel()
) {
    val personajeFavorito = viewModel.personajesFavoritos.collectAsState(initial = emptyList()).value
    Column (modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.favoritos),
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn (modifier = Modifier.fillMaxSize()) {
            items(personajeFavorito.size) { index ->
                val personajeFavoritop = personajeFavorito[index]
                PersonajeFavoritoItem(
                    personaje = personajeFavoritop.toModel(),
                    onItemClick = {},
                    onFavoritoClick = { personaje ->
                        viewModel.eliminarFavorito(personaje)
                    }
                )
            }
        }
    }
}