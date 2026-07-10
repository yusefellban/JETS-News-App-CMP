package jets.iti.yousef.domain.repository

import jets.iti.yousef.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface FavoriteArticleRepository {
    fun getFavoriteArticles(): Flow<List<Article>>
    suspend fun addFavorite(article: Article)
    suspend fun removeFavorite(article: Article)
    fun isFavorite(url: String): Flow<Boolean>
}
