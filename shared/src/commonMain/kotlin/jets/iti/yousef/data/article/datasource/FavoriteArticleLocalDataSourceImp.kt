package jets.iti.yousef.data.article.datasource

import jets.iti.yousef.data.article.local.FavoriteArticleDao
import jets.iti.yousef.data.article.local.FavoriteArticleLocalDataSource
import jets.iti.yousef.data.article.local.toDomain
import jets.iti.yousef.data.article.local.toEntity
import jets.iti.yousef.domain.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteArticleLocalDataSourceImp(
    private val favoriteArticleDao: FavoriteArticleDao
) : FavoriteArticleLocalDataSource {

    override fun getAllFavorites(): Flow<List<Article>> {
        return favoriteArticleDao.getAllFavorites().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertFavorite(article: Article) {
        favoriteArticleDao.insertFavorite(article.toEntity())
    }

    override suspend fun removeFavorite(article: Article) {
        favoriteArticleDao.removeFavorite(article.toEntity())
    }

    override fun isFavorite(url: String): Flow<Boolean> {
        return favoriteArticleDao.isFavorite(url)
    }
}
