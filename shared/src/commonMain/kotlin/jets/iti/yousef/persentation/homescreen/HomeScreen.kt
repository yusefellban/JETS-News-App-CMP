package jets.iti.yousef.persentation.homescreen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import jets.iti.yousef.persentation.homescreen.component.HomeContent
import jets.iti.yousef.persentation.homescreen.component.RotatingLogo


@Composable
fun HomeScreen() {

    val viewModel = remember {
        HomeViewModel()
    }
    val state = viewModel.allGenericArticles
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 48.dp)
    ) {
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    RotatingLogo()
                }
            }

            state.error != null -> {
                Text(state.error!!)
            }

            else -> {
                HomeContent(state)
            }

        }

    }

}
