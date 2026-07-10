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

val appModule = module {
    single { httpClient }
    single { ArticleService(get()) }
    single { ArticleRemoteDataSource(get()) }
    single<ArticleRepository> { ArticleRepositoryImp(get()) }
    factoryOf(::GetAllGenericArticles)
    viewModelOf(::HomeViewModel)
}
