package net.iessochoa.carlosarroyogalan.t13_restapi.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
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
    onItemClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
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
        }
    }
}