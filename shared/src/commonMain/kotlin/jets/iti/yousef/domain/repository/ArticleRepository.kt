package jets.iti.yousef.domain.repository

import jets.iti.yousef.domain.model.Article

interface ArticleRepository {
    suspend fun getAllGenericArticles(): List<Article>
}