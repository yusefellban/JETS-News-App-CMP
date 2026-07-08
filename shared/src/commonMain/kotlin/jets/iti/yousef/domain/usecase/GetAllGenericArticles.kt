package jets.iti.yousef.domain.usecase

import jets.iti.yousef.domain.model.Article
import jets.iti.yousef.domain.repository.ArticleRepository

class GetAllGenericArticles(private val articleRepository: ArticleRepository)  {
    //before return the list cheek if it's empty or not
    suspend operator fun invoke(): List<Article> {
        if (articleRepository.getAllGenericArticles().isEmpty()) {
            throw Exception("No articles found")
        }
        return articleRepository.getAllGenericArticles()
    }
}