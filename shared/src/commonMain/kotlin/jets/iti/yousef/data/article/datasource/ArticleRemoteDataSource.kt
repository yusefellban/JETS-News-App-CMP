package jets.iti.yousef.data.article.datasource

import jets.iti.yousef.data.article.model.NetworkArticle
import jets.iti.yousef.data.network.ArticleService


class ArticleRemoteDataSource (private val articleService: ArticleService){

    suspend fun fetchArticles(): List<NetworkArticle> {
        return articleService.fetchArticles()
    }
}
