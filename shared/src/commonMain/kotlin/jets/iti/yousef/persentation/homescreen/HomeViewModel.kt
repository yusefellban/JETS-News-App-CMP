package jets.iti.yousef.persentation.homescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jets.iti.yousef.data.article.ArticleRepositoryImp

import jets.iti.yousef.domain.usecase.GetAllGenericArticles
import jets.iti.yousef.persentation.homescreen.intent.HomeScreenActions
import jets.iti.yousef.persentation.homescreen.state.AllGenericArticleState
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getArticlesUseCase: GetAllGenericArticles
) : ViewModel() {

    var allGenericArticles by mutableStateOf(AllGenericArticleState())
        private set

    init {
        getAllGenericArticles()
    }
   public fun reduce(action: HomeScreenActions) {
        when (action) {
            HomeScreenActions.AllArticles -> getAllGenericArticles()
        }
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
