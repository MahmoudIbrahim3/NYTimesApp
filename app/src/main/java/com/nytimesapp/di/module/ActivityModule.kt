package com.nytimesapp.di.module

import com.nytimesapp.presentation.ui.articledetails.ArticleDetailActivity
import com.nytimesapp.presentation.ui.articles.ArticlesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeArticleActivity(): ArticlesActivity

    @ContributesAndroidInjector
    abstract fun contributeArticleDetailActivity(): ArticleDetailActivity

}