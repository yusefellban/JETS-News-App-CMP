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
import jets.iti.yousef.persentation.theme.NewsScopeTheme
import jets.iti.yousef.persentation.splashSceen.SplashScreen
import kotlinx.coroutines.delay

@Composable
@Preview
fun App() {
    NewsScopeTheme {

        var isSplashScreenVisible by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            delay(1000)
            isSplashScreenVisible = false
        }

        Crossfade(
            targetState = isSplashScreenVisible,
            animationSpec = tween(1000)
        ) { visible ->

            if (visible) {
                SplashScreen()
            } else {

                val viewModel = remember { HomeViewModel() }
                val state = viewModel.allGenericArticles

                when {
                    state.isLoading -> {
                        CircularProgressIndicator()
                    }

                    state.error != null -> {
                        Text(state.error!!)
                    }

                    else -> {

                        LazyColumn(
                            modifier = Modifier.fillMaxSize()
                                .background(MaterialTheme.colorScheme.background)
                            ,
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            contentPadding = PaddingValues(16.dp)

                        ) {

                            items(state.articles) { article ->
                                ArticleCard(article)
                            }

                        }
                    }
                }

            }
        }
    }
}

@Composable
fun ArticleCard(
    article: Article
) {
    Card(
        modifier = Modifier.fillMaxWidth()

    ) {

        Column {

            AsyncImage(
                model = article.imageUrl,
                contentDescription = article.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(12.dp)
                .fillMaxWidth()
            ) {

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                    , color =  MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.height(6.dp))

                Text(
                    text = "${article.source.name ?: "Unknown"} • ${article.date}",
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(Modifier.height(8.dp))

                article.description?.let {
                    Text(
                        text = it,
                        maxLines = 3,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

