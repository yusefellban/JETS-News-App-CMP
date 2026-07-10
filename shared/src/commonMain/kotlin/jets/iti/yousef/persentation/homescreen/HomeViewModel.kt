package jets.iti.yousef.persentation.homescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jets.iti.yousef.data.article.ArticleRepositoryImp

import jets.iti.yousef.domain.usecase.GetAllGenericArticles
import jets.iti.yousef.persentation.homescreen.intent.HomeScreenActions
import jets.iti.yousef.domain.usecase.AddFavoriteArticleUseCase
import jets.iti.yousef.domain.usecase.RemoveFavoriteArticleUseCase
import jets.iti.yousef.domain.model.Article
import jets.iti.yousef.persentation.homescreen.state.AllGenericArticleState
import kotlinx.coroutines.launch

import jets.iti.yousef.domain.usecase.GetFavoriteArticlesUseCase

class HomeViewModel(
    private val getArticlesUseCase: GetAllGenericArticles,
    private val addFavoriteArticleUseCase: AddFavoriteArticleUseCase,
    private val removeFavoriteArticleUseCase: RemoveFavoriteArticleUseCase,
    private val getFavoriteArticlesUseCase: GetFavoriteArticlesUseCase
) : ViewModel() {

    var allGenericArticles by mutableStateOf(AllGenericArticleState())
        private set

    init {
        getAllGenericArticles()
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            getFavoriteArticlesUseCase().collect { favorites ->
                val urls = favorites.map { it.url }.toSet()
                allGenericArticles = allGenericArticles.copy(favoriteUrls = urls)
            }
        }
    }
   public fun reduce(action: HomeScreenActions) {
        when (action) {
            is HomeScreenActions.AllArticles -> getAllGenericArticles()
            is HomeScreenActions.AddFavorite -> addFavorite(action.article)
            is HomeScreenActions.RemoveFavorite -> removeFavorite(action.article)
        }
   }

    private fun addFavorite(article: Article) {
        viewModelScope.launch { addFavoriteArticleUseCase(article) }
    }

    private fun removeFavorite(article: Article) {
        viewModelScope.launch { removeFavoriteArticleUseCase(article) }
    }

    private fun getAllGenericArticles() {
        viewModelScope.launch {
            try {
                allGenericArticles = allGenericArticles.copy(isLoading = true, error = null)
                val articles = getArticlesUseCase()
                allGenericArticles = allGenericArticles.copy(articles = articles, isLoading = false)
            } catch (e: Exception) {
                allGenericArticles = allGenericArticles.copy(error = e.message, isLoading = false)
            }
        }
    }

}
