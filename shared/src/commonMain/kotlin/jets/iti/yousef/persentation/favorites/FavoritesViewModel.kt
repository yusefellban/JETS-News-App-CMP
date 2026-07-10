package jets.iti.yousef.persentation.favorites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jets.iti.yousef.domain.usecase.GetFavoriteArticlesUseCase
import jets.iti.yousef.domain.usecase.RemoveFavoriteArticleUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoriteArticlesUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteArticleUseCase
) : ViewModel() {

    var state by mutableStateOf(FavoritesState())
        private set

    init {
        reduce(FavoritesActions.LoadFavorites)
    }

    fun reduce(action: FavoritesActions) {
        when (action) {
            is FavoritesActions.LoadFavorites -> loadFavorites()
            is FavoritesActions.RequestRemoveFavorite -> state = state.copy(pendingDelete = action.article)
            is FavoritesActions.ConfirmRemoveFavorite -> {
                state = state.copy(pendingDelete = null)
                removeFavorite(action.article)
            }
            is FavoritesActions.DismissRemoveFavorite -> state = state.copy(pendingDelete = null)
        }
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            getFavoritesUseCase()
                .onStart { state = state.copy(isLoading = true, error = null) }
                .catch { e -> state = state.copy(error = e.message, isLoading = false) }
                .collect { articles ->
                    state = state.copy(articles = articles, isLoading = false)
                }
        }
    }

    private fun removeFavorite(article: jets.iti.yousef.domain.model.Article) {
        viewModelScope.launch {
            try {
                removeFavoriteUseCase(article)
            } catch (e: Exception) {
                state = state.copy(error = e.message)
            }
        }
    }
}
