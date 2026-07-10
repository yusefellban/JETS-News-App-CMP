package jets.iti.yousef.persentation.favorites

import jets.iti.yousef.domain.model.Article

data class FavoritesState(
    val articles: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val pendingDelete: Article? = null   // non-null → dialog is showing
)

sealed class FavoritesActions {
    object LoadFavorites : FavoritesActions()
    /** User tapped the heart → show confirmation dialog */
    data class RequestRemoveFavorite(val article: Article) : FavoritesActions()
    /** User confirmed deletion in the dialog */
    data class ConfirmRemoveFavorite(val article: Article) : FavoritesActions()
    /** User dismissed the dialog */
    object DismissRemoveFavorite : FavoritesActions()
}

