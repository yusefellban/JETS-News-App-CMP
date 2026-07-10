package jets.iti.yousef.data.article.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteArticleDao {
    @Query("SELECT * FROM favorite_articles")
    fun getAllFavorites(): Flow<List<FavoriteArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(article: FavoriteArticleEntity)

    @Delete
    suspend fun removeFavorite(article: FavoriteArticleEntity)
    
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_articles WHERE url = :url LIMIT 1)")
    fun isFavorite(url: String): Flow<Boolean>
}
