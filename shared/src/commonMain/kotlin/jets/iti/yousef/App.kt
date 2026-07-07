package jets.iti.yousef

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import jets.iti.yousef.core.theme.NewsScopeTheme
import jets.iti.yousef.persentation.splash.SplashScreen
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

import newsscope.shared.generated.resources.Res
import newsscope.shared.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    NewsScopeTheme {
        var isSplashScreenVisible by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            delay(1000) // 3 seconds splash
            isSplashScreenVisible = false
        }

        Crossfade(
            targetState = isSplashScreenVisible,
            animationSpec = tween(durationMillis = 1000)
        ) { visible ->
            if (visible) {
                SplashScreen()
            } else {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    var showContent by remember { mutableStateOf(false) }
    val greeting = remember { Greeting().greet() }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { showContent = !showContent }) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
    }
}
