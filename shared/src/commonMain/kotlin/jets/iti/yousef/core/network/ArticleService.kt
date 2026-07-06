package jets.iti.yousef.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import jets.iti.yousef.core.config.AppConfig
import jets.iti.yousef.core.network.model.NetworkArticle
import jets.iti.yousef.core.network.model.ArticleResponse

class ArticleService(private val client: HttpClient) {
    private val url = "${AppConfig.BASE_URL}top-headlines?"
    private val apiKey = AppConfig.API_KEY
    private val category = "general"


    suspend fun fetchArticles(): List<NetworkArticle> {
        val result = client.get(url) {
            parameter("category", category)
            parameter("apiKey", apiKey)
        }
         when(result.status.value){
             in 200..299 -> {
                 val response = result.body<ArticleResponse>()
                 return response.articles
             }
             in 300..399 -> throw Exception("Redirection")
             in 400..499 -> throw Exception("Client Error")
             in 500..599 -> throw Exception("Server Error")
             else -> throw Exception("Unknown Error")
         }
    }
}