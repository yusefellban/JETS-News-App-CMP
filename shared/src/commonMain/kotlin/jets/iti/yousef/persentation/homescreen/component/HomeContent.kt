package jets.iti.yousef.persentation.homescreen.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import jets.iti.yousef.persentation.homescreen.state.AllGenericArticleState


@Composable
fun HomeContent(
    state: AllGenericArticleState,
    onFavoriteClick: (jets.iti.yousef.domain.model.Article, Boolean) -> Unit
){
    LazyColumn{
        items(state.articles){ article ->
            val isFav = state.favoriteUrls.contains(article.url)
            ArticleCard(
                article = article,
                isFavorite = isFav,
                onFavoriteClick = { onFavoriteClick(article, isFav) }
            )
        }
    }

}
