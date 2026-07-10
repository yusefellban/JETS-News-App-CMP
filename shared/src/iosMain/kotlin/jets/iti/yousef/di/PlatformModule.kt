package jets.iti.yousef.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import jets.iti.yousef.data.article.local.AppDatabase
import jets.iti.yousef.data.article.local.dbFileName
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual val platformModule: Module = module {
    single<AppDatabase> {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        val dbFilePath = documentDirectory?.path + "/" + dbFileName
        Room.databaseBuilder<AppDatabase>(
            name = dbFilePath,
            factory =  { AppDatabase::class.instantiateImpl() } // For room 2.7.x this is how it works on iOS
        ).setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(true)
        .build()
    }
}
