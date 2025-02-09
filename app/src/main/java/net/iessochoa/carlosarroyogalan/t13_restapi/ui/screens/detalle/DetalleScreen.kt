package net.iessochoa.carlosarroyogalan.t13_restapi.ui.screens.detalle

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import net.iessochoa.carlosarroyogalan.t13_restapi.ui.components.TopAppScreenBar

@Composable
fun DetalleScreen(
    //Recibe el objeto Personaje en formato JSON
    personajeJson: Personaje,
    //Funcion para volver a la pantalla anterior
    onVolver: () -> Unit = {}
) {
    //Contenedor
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Barra superior con el titulo y boton de regreso
        TopAppScreenBar(
            tituloPantallaActual = stringResource(R.string.detalle_personaje),
            PantallaAnterior = true,
            Atras = onVolver
        )
        // Tarjeta para almacenar la informacion del personaje
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .border(2.dp, Color.Gray, RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFD6D6F5))
        ) {
            //Contenido
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Imagen del personaje ubicada en la parte superior
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(personajeJson.image)
                        .build(),
                    contentDescription = stringResource(R.string.imagen_del_personaje),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                // Nombre del personaje estilizado
                Text(
                    text = personajeJson.name.uppercase(),
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4B0082),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                //Separación
                Spacer(modifier = Modifier.height(8.dp))
                // Datos del personaje
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    //Genero
                    Text(
                        text = personajeJson.genero,
                        fontSize = 30.sp,
                        color = Color.Black)
                    //Separación
                    Spacer(modifier = Modifier.height(8.dp))
                    //Estado
                    Text(text = personajeJson.estado,
                        fontSize = 30.sp,
                        color = Color.Black)
                    Spacer(modifier = Modifier.height(8.dp))
                    //Especie
                    Text(text = personajeJson.especie,
                        fontSize = 30.sp,
                        color = Color.Black)
                    Spacer(modifier = Modifier.height(8.dp))
                    //Creación
                    Text(text = "Creado el dia: " + personajeJson.fecha.toString(),
                        fontSize = 30.sp,
                        color = Color.Black)
                }
            }
        }
    }
}
