package net.iessochoa.carlosarroyogalan.t13_restapi.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import net.iessochoa.carlosarroyogalan.t13_restapi.R
import net.iessochoa.carlosarroyogalan.t13_restapi.data.model.Personaje

//Tarjeta de personaje
@Composable
fun PersonajeItem(
    personaje: Personaje,
    //Funcion que obtiene el id del personaje favorito seleccionado
    idFavorito: List<Int>,
    //Funcion que llama cuando hace click en la carta del personaje
    onItemClick: () -> Unit = {},
    //Funcion que llama cuando hace click en el favoritos
    onFavoritoClick: (Personaje) -> Unit = {},
    modifier: Modifier = Modifier
) {
    //Valor favorito que acreditará que el personaje a sido añadido a favoritos para añadirlo a la ventana
    val favorito = idFavorito.contains(personaje.id)
    Card(modifier = Modifier,
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(Color.Transparent),
        onClick = onItemClick) {
        Box(modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .clickable { onItemClick() }){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(personaje.image)
                        .build(),
                    //Mientras cargue mostrará esta imagen
                    placeholder = painterResource(R.drawable.ic_loading),
                    error = painterResource(R.drawable.ic_error),
                    contentDescription = personaje.name,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                ) {
                    //Nombre del personaje
                    Text(
                        text = personaje.name,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    //Especie del mismo
                    Text(
                        text = personaje.especie,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                    //Estado de este, vivo o muerto
                    Text(
                        text = personaje.estado,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Green,
                        fontSize = 14.sp,
                        textAlign = TextAlign.End
                    )
                }
            }
            //Caja que almacena el boton para mandar el personaje a favoritos
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.BottomEnd
            ){
                //Funcion del boton cuando se pulsa
                IconButton(
                    onClick =  {onFavoritoClick(personaje)},
                    modifier = Modifier.size(40.dp)
                ) {
                    //Icono del boton
                    Icon(
                        imageVector = if (favorito)
                        Icons.Default.Favorite
                        else
                        Icons.Default.FavoriteBorder,
                        contentDescription = if (favorito) stringResource(R.string.eliminar_favorito)
                        else stringResource(R.string.a_adir_favorito),
                    )
                }
            }

        }
    }
}