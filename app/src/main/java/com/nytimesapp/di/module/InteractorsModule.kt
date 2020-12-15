package com.nytimesapp.di.module

import com.nytimesapp.data.Interactors
import com.nytimesapp.data.repository.ArticlesRepository
import com.nytimes.core.usecases.GetMostPopularArticles
import com.nytimesapp.data.remote.api.ArticlesApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object InteractorsModule {

    @JvmStatic
    @Provides
    @Singleton
    fun provideInteractors(
        articlesRepository: ArticlesRepository
    ) = Interactors(
        GetMostPopularArticles(articlesRepository)
    )

    @JvmStatic
    @Provides
    @Singleton
    fun bindArticleRepository(articlesApi: ArticlesApi) =
        ArticlesRepository(articlesApi)
}