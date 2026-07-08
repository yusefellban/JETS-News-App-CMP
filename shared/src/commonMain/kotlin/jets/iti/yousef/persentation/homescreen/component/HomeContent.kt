package jets.iti.yousef.persentation.homescreen.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import jets.iti.yousef.persentation.homescreen.state.AllGenericArticleState


@Composable
fun HomeContent(
    state: AllGenericArticleState
){
    LazyColumn{
        items(state.articles){
            ArticleCard(it)
        }
    }

}
