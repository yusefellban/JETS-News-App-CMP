package jets.iti.yousef.data.article

import jets.iti.yousef.data.article.datasource.ArticleRemoteDataSource
import jets.iti.yousef.data.article.model.toDomain
import jets.iti.yousef.domain.model.Article
import jets.iti.yousef.domain.repository.ArticleRepository

class ArticleRepositoryImp(private val articleRemoteDataSource: ArticleRemoteDataSource): ArticleRepository {

    override suspend fun getAllGenericArticles(): List<Article> {
       return articleRemoteDataSource.fetchArticles().toDomain()
    }

}