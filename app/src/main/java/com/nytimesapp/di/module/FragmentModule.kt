package com.nytimesapp.di.module

import com.nytimesapp.presentation.ui.articledetails.ArticleDetailsFragment
import com.nytimesapp.presentation.ui.articles.ArticlesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeArticlesFragment(): ArticlesFragment

    @ContributesAndroidInjector
    abstract fun contributeArticleDetailFragment(): ArticleDetailsFragment


}