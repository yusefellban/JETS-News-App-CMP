package jets.iti.yousef.domain.usecase

import jets.iti.yousef.domain.model.Article
import jets.iti.yousef.domain.repository.FavoriteArticleRepository

class RemoveFavoriteArticleUseCase(private val repository: FavoriteArticleRepository) {
    suspend operator fun invoke(article: Article) = repository.removeFavorite(article)
}
