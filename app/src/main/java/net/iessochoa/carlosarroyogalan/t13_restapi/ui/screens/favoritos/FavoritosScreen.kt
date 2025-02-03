package net.iessochoa.carlosarroyogalan.t13_restapi.ui.screens.favoritos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import net.iessochoa.carlosarroyogalan.t13_restapi.R

@Composable
fun FavoritesScreen() {
    Column (modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.favoritos),
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.fillMaxWidth()
        )
    }
}