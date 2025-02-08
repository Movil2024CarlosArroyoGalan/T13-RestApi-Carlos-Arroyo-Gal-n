package net.iessochoa.carlosarroyogalan.t13_restapi.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import net.iessochoa.carlosarroyogalan.t13_restapi.R
import net.iessochoa.carlosarroyogalan.t13_restapi.data.model.Personaje

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel(),
    onPulsarPersonaje: (Personaje) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val listaPersonajes = viewModel.personajes.collectAsLazyPagingItems()
    val idFavorito by viewModel.idFavorito.collectAsState(initial = emptyList())
    Column (modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.rick_y_morty),
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        )
        Box(modifier = Modifier) {
            when {
                //Estamos en la carga inicial. No tenemos datos y mostramos un CircularProgressIndicator
                listaPersonajes.loadState.refresh is LoadState.Loading && (listaPersonajes.itemCount
                        == 0) -> {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(64.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
                //Recuperamos datos y no tenemos ninguno mostramos un mensaje
                listaPersonajes.loadState.append is LoadState.NotLoading && listaPersonajes.itemCount
                        == 0 -> {
                    Text(text = stringResource(R.string.no_hay_personajes))
                }
                //Si tenemos datos pero no se han podido recuperar del error
                listaPersonajes.loadState.hasError -> {
                    Text(text = stringResource(R.string.error))
                }
                //Tenemos nuevos datos, los mostramos en la lista
                else -> {
                    //observa el Logcat los elementos que tenemos actualmente
                    Log.i("T11-REstApi", "Personajes cargados en el LazyColumn:${listaPersonajes.itemCount}")
                    LazyColumn (modifier = modifier) {
                        items(listaPersonajes.itemCount) { index ->
                            listaPersonajes[index]?.let { personaje ->
                                PersonajeItem(
                                    personaje = personaje,
                                    onItemClick = {onPulsarPersonaje(personaje)},
                                    idFavorito = idFavorito,
                                    onFavoritoClick = {viewModel.activarFavorito(personaje)}
                                )
                            }
                        }
                    }
                    //si se hace scroll y llega al final mostramos una barra de progreso.
                    // No se apreciará si la conexión es muy buena
                    if (listaPersonajes.loadState.append is LoadState.Loading) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(64.dp)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}