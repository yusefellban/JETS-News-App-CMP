package jets.iti.yousef.data.article.model

import jets.iti.yousef.domain.model.Article
import jets.iti.yousef.domain.model.Source
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

    val source: NetworkSource,

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
data class NetworkSource(
    val id: String?,
    val name: String
)

//mapping to domain
fun NetworkArticle.toDomain(): Article {
    return Article(
        source = source.toDomain(),
        author = author,
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        date = date,
        content = content
    )
}

fun NetworkSource.toDomain(): Source {
    return Source(
        id = id,
        name = name
    )
}
// mapping to domain list
fun List<NetworkArticle>.toDomain(): List<Article> {
    return this.map { it.toDomain() }
}