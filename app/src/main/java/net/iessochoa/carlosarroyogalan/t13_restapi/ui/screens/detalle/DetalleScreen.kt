package net.iessochoa.carlosarroyogalan.t13_restapi.ui.screens.detalle

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.google.gson.Gson
import net.iessochoa.carlosarroyogalan.t13_restapi.data.model.Personaje
import net.iessochoa.carlosarroyogalan.t13_restapi.ui.components.TopAppScreenBar
import net.iessochoa.carlosarroyogalan.t13_restapi.ui.navigation.DetalleDestination
import net.iessochoa.carlosarroyogalan.t13_restapi.ui.navigation.personajeToJson
import java.util.Date

@Composable
fun DetalleScreen(
    personajeJson: Personaje,
    onVolver: () -> Unit = {}
){
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppScreenBar(
            tituloPantallaActual = "Detalle Personaje",
            PantallaAnterior = true,
            Atras = onVolver
        )
        //Imagen
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(personajeJson.image)
                    .build(),
                contentDescription = "Imagen del personaje",
                modifier = Modifier
                        .fillMaxHeight()
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        // Datos
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = personajeJson.name,
                fontSize = 35.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = personajeJson.gender,
                    fontSize = 40.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, bottom = 20.dp)
                )
                Text(
                    text = personajeJson.species,
                    fontSize = 40.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, bottom = 20.dp)
                )
                Text(
                    text = personajeJson.status,
                    fontSize = 40.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, bottom = 20.dp)
                )
                Text(
                    text = personajeJson.created.toString(),
                    fontSize = 30.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start =     20.dp)
                )
            }
        }
    }
}