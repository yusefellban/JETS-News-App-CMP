package jets.iti.yousef.data.article.local

import jets.iti.yousef.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface FavoriteArticleLocalDataSource {
    fun getAllFavorites(): Flow<List<Article>>
    suspend fun insertFavorite(article: Article)
    suspend fun removeFavorite(article: Article)
    fun isFavorite(url: String): Flow<Boolean>
}
