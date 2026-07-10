package jets.iti.yousef.domain.usecase

import jets.iti.yousef.domain.repository.FavoriteArticleRepository

class IsFavoriteArticleUseCase(private val repository: FavoriteArticleRepository) {
    operator fun invoke(url: String) = repository.isFavorite(url)
}
