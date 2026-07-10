package jets.iti.yousef.data.article

import jets.iti.yousef.data.article.datasource.ArticleRemoteDataSource
import jets.iti.yousef.data.article.model.toDomain
import jets.iti.yousef.data.network.ArticleService
import jets.iti.yousef.domain.model.Article
import jets.iti.yousef.domain.repository.ArticleRepository

class ArticleRepositoryImp: ArticleRepository {
    private val articleRemoteDataSource= ArticleRemoteDataSource(ArticleService())
    override suspend fun getAllGenericArticles(): List<Article> {
       return articleRemoteDataSource.fetchArticles().toDomain()
    }

}