package jets.iti.yousef

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import jets.iti.yousef.domain.model.Article
import jets.iti.yousef.persentation.homescreen.HomeViewModel
import jets.iti.yousef.persentation.navigation.AppNavigation
import jets.iti.yousef.persentation.theme.NewsScopeTheme
import jets.iti.yousef.persentation.splashSceen.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun App() {
    NewsScopeTheme {
        AppNavigation()
    }
}