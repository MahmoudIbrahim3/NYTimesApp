package com.nytimesapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nytimesapp.di.ViewModelFactory
import com.nytimesapp.di.ViewModelKey
import com.nytimesapp.presentation.ui.articledetails.ArticleDetailsViewModel
import com.nytimesapp.presentation.ui.articles.ArticlesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    abstract fun bindArticlesViewModel(articlesViewModel: ArticlesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArticleDetailsViewModel::class)
    abstract fun bindArticleDetailsViewModel(articleDetailsViewModel: ArticleDetailsViewModel): ViewModel
}
