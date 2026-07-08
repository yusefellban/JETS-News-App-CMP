package jets.iti.yousef.domain.model

data class Article(

    val source: Source,

    val author: String?,

    val title: String,

    val description: String?,

    val url: String,

    val imageUrl: String?,

    val date: String,

    val content: String?
)
