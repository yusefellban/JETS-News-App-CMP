package jets.iti.yousef.data.article.repository

import jets.iti.yousef.data.article.local.FavoriteArticleLocalDataSource
import jets.iti.yousef.domain.model.Article
import jets.iti.yousef.domain.repository.FavoriteArticleRepository
import kotlinx.coroutines.flow.Flow

class FavoriteArticleRepositoryImp(
    private val localDataSource: FavoriteArticleLocalDataSource
) : FavoriteArticleRepository {
    override fun getFavoriteArticles(): Flow<List<Article>> = localDataSource.getAllFavorites()

    override suspend fun addFavorite(article: Article) {
        localDataSource.insertFavorite(article)
    }

    override suspend fun removeFavorite(article: Article) {
        localDataSource.removeFavorite(article)
    }

    override fun isFavorite(url: String): Flow<Boolean> = localDataSource.isFavorite(url)
}
