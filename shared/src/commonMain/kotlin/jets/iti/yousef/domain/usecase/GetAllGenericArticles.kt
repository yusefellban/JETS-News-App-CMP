package jets.iti.yousef.domain.usecase

import jets.iti.yousef.domain.model.Article
import jets.iti.yousef.domain.repository.ArticleRepository

class GetAllGenericArticles(private val articleRepository: ArticleRepository)  {
    suspend operator fun invoke(): List<Article> {
        return articleRepository.getAllGenericArticles()
    }
}