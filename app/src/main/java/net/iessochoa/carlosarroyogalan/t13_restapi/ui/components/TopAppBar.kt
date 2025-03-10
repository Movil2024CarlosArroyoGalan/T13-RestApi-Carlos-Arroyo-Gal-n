package net.iessochoa.carlosarroyogalan.t13_restapi.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import net.iessochoa.carlosarroyogalan.t13_restapi.R

/**
 * Composable que define la barra de navegación superior de la app
 * @param tituloPantallaActual pantalla actual. Permite mostrar el titulo correspondiente.
 * @param puedeNavegarAtras indica si se puede navegar hacia atrás. La pantalla de inicio no puede tener navegación hacia atrás
 * @param navegaAtras acción de navegación hacia atrás. Lambda que se ejecuta al pulsar el botón de navegación hacia atrás
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppScreenBar(
    //Titulo de la barra
    tituloPantallaActual: String,
    //Nagevación hacia atras
    PantallaAnterior: Boolean,
    //Funcion para que pueda navegar hacia atras
    Atras: () -> Unit={},
    modifier: Modifier = Modifier
) {
    TopAppBar(
        //Recuperamos el título del enum AppScreen
        title = { Text(text = tituloPantallaActual) },
        modifier = modifier,
        //Fecha de retroceso
        navigationIcon = {
            //si es la primera pantalla no se muestra el botón de navegación
            if (PantallaAnterior) {
                //lambda que iría a la pantalla anterior
                IconButton(onClick = Atras) { //Al clickar ejecuta la opción de navegación al contrario
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        //recuerda que el texto tiene que ir en string.xml
                        contentDescription = stringResource(R.string.volver) //Descripción
                    )
                }
            }
        }
    )
}
