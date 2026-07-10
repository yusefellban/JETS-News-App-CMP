package jets.iti.yousef.data.article.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import jets.iti.yousef.domain.model.Article
import jets.iti.yousef.domain.model.Source

@Entity(tableName = "favorite_articles")
data class FavoriteArticleEntity(
    @PrimaryKey
    val url: String,
    val sourceId: String?,
    val sourceName: String?,
    val author: String?,
    val title: String,
    val description: String?,
    val imageUrl: String?,
    val date: String,
    val content: String?
)

fun FavoriteArticleEntity.toDomain() = Article(
    source = Source(id = sourceId, name = sourceName),
    author = author,
    title = title,
    description = description,
    url = url,
    imageUrl = imageUrl,
    date = date,
    content = content
)

fun Article.toEntity() = FavoriteArticleEntity(
    url = url,
    sourceId = source.id,
    sourceName = source.name,
    author = author,
    title = title,
    description = description,
    imageUrl = imageUrl,
    date = date,
    content = content
)
