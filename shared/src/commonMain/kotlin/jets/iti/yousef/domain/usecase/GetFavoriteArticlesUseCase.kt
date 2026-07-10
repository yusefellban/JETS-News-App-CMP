package jets.iti.yousef.domain.usecase

import jets.iti.yousef.domain.repository.FavoriteArticleRepository

class GetFavoriteArticlesUseCase(private val repository: FavoriteArticleRepository) {
    operator fun invoke() = repository.getFavoriteArticles()
}
