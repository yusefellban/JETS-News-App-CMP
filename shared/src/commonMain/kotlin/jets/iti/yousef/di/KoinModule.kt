package jets.iti.yousef.di

import jets.iti.yousef.data.article.ArticleRepositoryImp
import jets.iti.yousef.data.article.datasource.ArticleRemoteDataSource
import jets.iti.yousef.data.network.ArticleService
import jets.iti.yousef.data.network.httpClient
import jets.iti.yousef.domain.repository.ArticleRepository
import jets.iti.yousef.domain.usecase.GetAllGenericArticles
import jets.iti.yousef.persentation.homescreen.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

import jets.iti.yousef.data.article.local.AppDatabase
import jets.iti.yousef.data.article.local.FavoriteArticleLocalDataSource
import jets.iti.yousef.data.article.datasource.FavoriteArticleLocalDataSourceImp
import jets.iti.yousef.data.article.repository.FavoriteArticleRepositoryImp
import jets.iti.yousef.domain.repository.FavoriteArticleRepository
import jets.iti.yousef.domain.usecase.AddFavoriteArticleUseCase
import jets.iti.yousef.domain.usecase.GetFavoriteArticlesUseCase
import jets.iti.yousef.domain.usecase.IsFavoriteArticleUseCase
import jets.iti.yousef.domain.usecase.RemoveFavoriteArticleUseCase
import jets.iti.yousef.persentation.favorites.FavoritesViewModel

val appModule = module {
    single { httpClient }
    single { ArticleService(get()) }
    single { ArticleRemoteDataSource(get()) }
    single<ArticleRepository> { ArticleRepositoryImp(get()) }
    factoryOf(::GetAllGenericArticles)
    viewModelOf(::HomeViewModel)

    // Favorites
    single { get<AppDatabase>().favoriteArticleDao() }
    single<FavoriteArticleLocalDataSource> { FavoriteArticleLocalDataSourceImp(get()) }
    single<FavoriteArticleRepository> { FavoriteArticleRepositoryImp(get()) }
    factoryOf(::AddFavoriteArticleUseCase)
    factoryOf(::RemoveFavoriteArticleUseCase)
    factoryOf(::GetFavoriteArticlesUseCase)
    factoryOf(::IsFavoriteArticleUseCase)
    viewModelOf(::FavoritesViewModel)
}
