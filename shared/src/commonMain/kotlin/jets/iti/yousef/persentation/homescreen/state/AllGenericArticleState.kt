package jets.iti.yousef.persentation.homescreen.state

import jets.iti.yousef.domain.model.Article

data class AllGenericArticleState (
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList(),
    val favoriteUrls: Set<String> = emptySet(),
    val error: String? = null
)
