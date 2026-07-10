package jets.iti.yousef.data.network

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import jets.iti.yousef.data.config.AppConfig
import jets.iti.yousef.data.article.model.NetworkArticle
import jets.iti.yousef.data.article.model.ArticleResponse

class ArticleService {
    private val url = "${AppConfig.BASE_URL}top-headlines?"
    private val apiKey = AppConfig.API_KEY
    private val category = "general"
    private val client = httpClient

    suspend fun fetchArticles(): ArticleResponse {
        val result = client.get(url) {
            parameter("category", category)
            parameter("apiKey", apiKey)
        }
        when (result.status.value) {
            in 200..299 -> {
                return result.body<ArticleResponse>()
            }

            in 300..399 -> throw Exception("Redirection")
            in 400..499 -> throw Exception("Client Error")
            in 500..599 -> throw Exception("Server Error")
            else -> throw Exception("Unknown Error")
        }
    }
}