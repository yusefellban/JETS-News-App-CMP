package jets.iti.yousef.persentation.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import jets.iti.yousef.persentation.homescreen.component.ArticleCard
import jets.iti.yousef.persentation.homescreen.component.RotatingLogo
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun FavoritesScreen() {
    val viewModel = koinViewModel<FavoritesViewModel>()
    val state = viewModel.state

    // Confirmation dialog
    state.pendingDelete?.let { article ->
        AlertDialog(
            onDismissRequest = { viewModel.reduce(FavoritesActions.DismissRemoveFavorite) },
            title = { Text("Remove from Favorites") },
            text = {
                Text("Are you sure you want to remove \"${article.title}\" from your favorites?")
            },
            confirmButton = {
                TextButton(
                    onClick = { viewModel.reduce(FavoritesActions.ConfirmRemoveFavorite(article)) },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Remove")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { viewModel.reduce(FavoritesActions.DismissRemoveFavorite) }
                ) {
                    Text("Cancel")
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 48.dp)
    ) {
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    RotatingLogo()
                }
            }
            state.error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(state.error)
                }
            }
            state.articles.isEmpty() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No favorites yet")
                }
            }
            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.articles) { article ->
                        ArticleCard(
                            article = article,
                            isFavorite = true,
                            onFavoriteClick = {
                                // Trigger dialog instead of deleting immediately
                                viewModel.reduce(FavoritesActions.RequestRemoveFavorite(article))
                            }
                        )
                    }
                }
            }
        }
    }
}
