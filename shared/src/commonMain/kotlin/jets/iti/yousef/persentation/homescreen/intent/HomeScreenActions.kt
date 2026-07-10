package jets.iti.yousef.persentation.homescreen.intent

sealed class HomeScreenActions {
    object AllArticles : HomeScreenActions()
    data class AddFavorite(val article: jets.iti.yousef.domain.model.Article) : HomeScreenActions()
    data class RemoveFavorite(val article: jets.iti.yousef.domain.model.Article) : HomeScreenActions()
}