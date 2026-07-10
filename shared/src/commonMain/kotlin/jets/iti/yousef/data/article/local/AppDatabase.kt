package jets.iti.yousef.data.article.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteArticleEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteArticleDao(): FavoriteArticleDao
}

// Room compiler will generate AppDatabaseImpl
internal const val dbFileName = "news_app.db"
