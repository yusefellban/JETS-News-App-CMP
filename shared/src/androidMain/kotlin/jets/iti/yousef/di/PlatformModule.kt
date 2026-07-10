package jets.iti.yousef.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import jets.iti.yousef.data.article.local.AppDatabase
import jets.iti.yousef.data.article.local.dbFileName
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.File

actual val platformModule: Module = module {
    single<AppDatabase> {
        val context = androidContext()
        val dbFile = context.getDatabasePath(dbFileName)
        Room.databaseBuilder<AppDatabase>(
            context = context,
            name = dbFile.absolutePath
        ).setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(true)
        .build()
    }
}
