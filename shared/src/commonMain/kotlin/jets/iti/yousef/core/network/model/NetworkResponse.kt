package jets.iti.yousef.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(

    val status: String,

    val totalResults: Int,

    val articles: List<NetworkArticle>
)

@Serializable
data class NetworkArticle(

    val source: Source,

    val author: String?,

    val title: String,

    val description: String?,

    val url: String,

    @SerialName("urlToImage")
    val imageUrl: String?,

    @SerialName("publishedAt")
    val date: String,

    val content: String?
)

@Serializable
data class Source(
    val id: String?,
    val name: String
)